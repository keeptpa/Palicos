package com.keeptpa.palicobot.audio;

import com.sedmelluq.discord.lavaplayer.player.event.AudioEvent;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventListener;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

import java.util.ArrayList;

public class SoundTrack implements AudioEventListener {
    ArrayList<AudioTrack> tracks = new ArrayList<>();
    @Override
    public void onEvent(AudioEvent audioEvent) {

    }

    public void queue(AudioTrack track){
        tracks.add(track);
    }
}
