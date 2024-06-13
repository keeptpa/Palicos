package com.keeptpa.palicobot.audio;

import com.keeptpa.palicobot.Chatter;
import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

public class LoadResultHandler implements AudioLoadResultHandler {
    @Override
    public void trackLoaded(AudioTrack audioTrack) {
        System.out.println("Track loaded: " + audioTrack.getInfo().title);
        AudioController.instance.track.queue(audioTrack);
        AudioController.instance.player.playTrack(audioTrack);
    }

    @Override
    public void playlistLoaded(AudioPlaylist audioPlaylist) {

    }

    @Override
    public void noMatches() {

    }

    @Override
    public void loadFailed(FriendlyException e) {

    }
}
