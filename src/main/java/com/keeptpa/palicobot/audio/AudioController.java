package com.keeptpa.palicobot.audio;

import com.keeptpa.palicobot.Chatter;
import com.keeptpa.palicobot.Configuer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;

import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.entities.channel.unions.AudioChannelUnion;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.managers.AudioManager;
import net.dv8tion.jda.api.requests.restaction.MessageCreateAction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AudioController {

    static HashMap<String, AudioController> controllers = new HashMap<>();

    public static AudioController getController(MessageChannelUnion channel){
        if(!controllers.containsKey(channel.getId())){
            controllers.put(channel.getId(), new AudioController(channel));
        }
        return controllers.get(channel.getId());
    }

    AudioManager am = null;;
    AudioPlayer player = null;
    SoundTrack track = null;
    AudioPlayerManager apm;
    LoadResultHandler resultHandler;
    MessageChannelUnion channel;
    int nowPlaying = 0;
    boolean cyclePlay = false;

    private boolean nowWaitingSelectSong = false;
    private List<AudioTrack> waitingSelectSongs = new ArrayList<>();
    public AudioController(MessageChannelUnion channel){
        apm = new DefaultAudioPlayerManager();
        dev.lavalink.youtube.YoutubeAudioSourceManager ytSourceManager = new dev.lavalink.youtube.YoutubeAudioSourceManager();
        apm.registerSourceManager(ytSourceManager);
        AudioSourceManagers.registerRemoteSources(apm);
        player = apm.createPlayer();
        track = new SoundTrack(this);
        resultHandler = new LoadResultHandler(this);
        this.channel = channel;
    }

    public void connect(AudioChannelUnion channel) {
        am = channel.getGuild().getAudioManager();
        am.setSendingHandler(new AudioPlayHandler(player));
        am.openAudioConnection(channel);
        Start();
    }

    public void disconnect(){
        am.closeAudioConnection();
    }

    public void Start(){
        player.addListener(track);
    }

    public void searchYTB(String id){
        apm.loadItem("ytsearch:" + id, resultHandler);
    }

    public void addYTBSong(String youtubeCode){
        apm.loadItem(youtubeCode, resultHandler);
    }

    public void Pause(){
        player.setPaused(true);
    }

    public void Resume(){
        player.setPaused(false);
    }

    public void togglePause(){
        if(player.isPaused())
            Resume();
        else
            Pause();
    }

    public boolean isPaused(){
        return player.isPaused();
    }

    public boolean setCyclePlay(boolean b){
        cyclePlay = b;
        return cyclePlay;
    }

    public boolean isCyclePlay(){
        return cyclePlay;
    }

    public void play(){
        if(player.getPlayingTrack() == null){
            player.playTrack(track.tracks.get(0));
            nowPlaying = 0;
            PrintSongList();
        }
    }

    public void playNext(){
        if(track.tracks.size() - 1 >= nowPlaying+1){
            player.playTrack(track.tracks.get(nowPlaying+1));
            nowPlaying++;
            PrintSongList();
        }else if(!cyclePlay){
            player.stopTrack();
            track.tracks.clear();
            nowPlaying = 0;
            Chatter.speak(channel, Configuer.localize("End_of_List"));
        }else{
            player.playTrack(track.tracks.get(0));
            nowPlaying = 0;
            PrintSongList();
        }
    }

    public void playPrev(){
        if(nowPlaying > 0){
            player.playTrack(track.tracks.get(nowPlaying-1));
            nowPlaying--;
            PrintSongList();
        }
    }

    public static boolean isChannelHasConnectedAudio(MessageChannelUnion channel){
        return controllers.containsKey(channel.getId());
    }

    public String getSongListName(){
        String totalNameList = "";
        if (track.tracks.size() == 0){
            return "No tracks";
        }
        for (int i = 0; i < track.tracks.size(); i++){
            if (nowPlaying == i){
                totalNameList += String.format("%d. %s👈🎶\n", i+1, track.tracks.get(i).getInfo().title);
            }else{
                totalNameList += String.format("%d. %s\n", i+1, track.tracks.get(i).getInfo().title);
            }
        }
        return totalNameList;
    }

    public void loadReport(LoadResult result){
        switch (result){
            case TRACK_LOADED:
                Chatter.speak(channel, Configuer.localize("Track_Loaded"));
                break;
            case PLAYLIST_LOADED:
                Chatter.speak(channel, Configuer.localize("Playlist_Loaded"));
                break;
            case NO_MATCHES:
                Chatter.speak(channel, Configuer.localize("No_Matches"));
                break;
            case LOAD_FAILED:
                Chatter.speak(channel, Configuer.localize("Load_Failed"));
                break;
        }
    }

    public void questSongSelect(List<AudioTrack> tracks){
        Chatter.speak(channel, Configuer.localize("What_You_Wanna_Listen"));
        String totalNameList = "";

        for (int i = 0; i < Math.min(10, tracks.size()); i++) {
            totalNameList += String.format("**%d**. %s\n", i, tracks.get(i).getInfo().title);
        }

        MessageCreateAction action = Chatter.SpeakWithoutQueue(channel, totalNameList.toString());
        action.queue(message -> {
            for (int i = 0; i < Math.min(10, tracks.size()); i++) {
                message.addReaction(Emoji.fromUnicode("U+003" + i + " U+20E3")).submit(false);
            }
        });
        nowWaitingSelectSong = true;
        waitingSelectSongs = tracks;
    }

    public static void PrintSongList(MessageChannelUnion channel) {
        String songList = AudioController.getController(channel).getSongListName();
        Chatter.speak(channel, songList);
    }
    public void PrintSongList() {
        PrintSongList(channel);
    }


    public boolean isNowWaitingSelectSong(){
        return nowWaitingSelectSong;
    }
    public void OnReceiveReactionSelectingSong(int songIndex){
        AudioTrack selectedTrack = waitingSelectSongs.get(songIndex);
        track.queue(selectedTrack);
        if (player.getPlayingTrack() == null) {
            player.playTrack(selectedTrack);
            PrintSongList();
        }
        nowWaitingSelectSong = false;
        waitingSelectSongs.clear();
        nowWaitingSelectSong = false;
    }

    public void release(){
        am = null;
        player = null;
        track = null;
        apm = null;
        resultHandler = null;
        channel = null;
        waitingSelectSongs = null;
    }
}
