package com.keeptpa.palicobot.audio;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

enum LoadResult {
    TRACK_LOADED,
    PLAYLIST_LOADED,
    PLAYLIST_NOTHING,
    NO_MATCHES,
    LOAD_FAILED
}

public class LoadResultHandler implements AudioLoadResultHandler {
    AudioController controller;
    public LoadResultHandler(AudioController controller){
        this.controller = controller;
    }
    @Override
    public void trackLoaded(AudioTrack audioTrack) {
        System.out.println("Track loaded: " + audioTrack.getInfo().title);
        //AudioController.getController(controller.track.queue(audioTrack));
        //AudioController.getController(controller.player.playTrack(audioTrack));
        controller.track.queue(audioTrack);
        if(controller.player.getPlayingTrack() == null){
            controller.play();
        }
        controller.loadReport(LoadResult.TRACK_LOADED);
    }

    @Override
    public void playlistLoaded(AudioPlaylist audioPlaylist) {
        if (audioPlaylist.getTracks().size() > 1){
            controller.questSongSelect(audioPlaylist.getTracks());
        }else{
            controller.track.queue(audioPlaylist.getTracks().get(0));
            if(controller.player.getPlayingTrack() == null){
                controller.play();
            }
        }
        controller.loadReport(LoadResult.PLAYLIST_LOADED);
    }

    @Override
    public void noMatches() {
        controller.loadReport(LoadResult.NO_MATCHES);
    }

    @Override
    public void loadFailed(FriendlyException e) {
        controller.loadReport(LoadResult.LOAD_FAILED);
    }
}
