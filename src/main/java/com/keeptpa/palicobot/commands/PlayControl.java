package com.keeptpa.palicobot.commands;

import com.keeptpa.palicobot.Chatter;
import com.keeptpa.palicobot.Command;
import com.keeptpa.palicobot.audio.AudioController;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class PlayControl extends Command {
    @Override
    public String[] getName() {
        return new String[]{"pc", "play-control"};
    }

    @Override
    public String getDescription() {
        return "Control play, next song, last song....";
    }

    @Override
    public void Execute(MessageReceivedEvent event, String[] args) {
        String command = args[1];
        switch (command) {
            case "pause":
                AudioController.getController(event.getChannel()).Pause();
                break;
            case "next":
                AudioController.getController(event.getChannel()).playNext();
                break;
            case "last":
                AudioController.getController(event.getChannel()).playPrev();
                break;
            case "list":
                AudioController.PrintSongList(event.getChannel());
                break;
        }
    }



}
