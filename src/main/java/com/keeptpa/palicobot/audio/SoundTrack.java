package com.keeptpa.palicobot.audio;

import com.sedmelluq.discord.lavaplayer.player.event.AudioEvent;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventListener;
import com.sedmelluq.discord.lavaplayer.player.event.TrackEndEvent;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;

import java.util.ArrayList;

public class SoundTrack implements AudioEventListener {
    ArrayList<AudioTrack> tracks = new ArrayList<>();
    AudioController controller;
    public SoundTrack(AudioController controller){
        this.controller = controller;
    }
    @Override
    public void onEvent(AudioEvent audioEvent) {
        if(audioEvent instanceof TrackEndEvent){
            if (((TrackEndEvent) audioEvent).endReason == AudioTrackEndReason.FINISHED)
                controller.playNext();
        }
    }

    public void queue(AudioTrack track){
        tracks.add(track);
    }

}
