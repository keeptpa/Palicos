package com.keeptpa.palicobot.audio;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;

import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import net.dv8tion.jda.api.entities.channel.unions.AudioChannelUnion;
import net.dv8tion.jda.api.managers.AudioManager;

public class AudioController {
    public static AudioController instance = new AudioController();
    AudioManager am = null;;
    AudioPlayer player = null;
    SoundTrack track = null;
    AudioPlayerManager apm;
    public AudioController(){
        apm = new DefaultAudioPlayerManager();
        dev.lavalink.youtube.YoutubeAudioSourceManager ytSourceManager = new dev.lavalink.youtube.YoutubeAudioSourceManager();
        apm.registerSourceManager(ytSourceManager);
        AudioSourceManagers.registerRemoteSources(apm);
        player = apm.createPlayer();
        track = new SoundTrack();
    }

    public void connect(AudioChannelUnion channel) {
        am = channel.getGuild().getAudioManager();
        am.setSendingHandler(new AudioPlayHandler(player));
        am.openAudioConnection(channel);
        Start();
    }

    public void Start(){
        player.addListener(track);
    }

    public void AddTrack(String id){
        apm.loadItem(id, new LoadResultHandler());
    }
}
