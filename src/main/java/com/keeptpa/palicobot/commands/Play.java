package com.keeptpa.palicobot.commands;

import com.keeptpa.palicobot.Chatter;
import com.keeptpa.palicobot.Command;
import com.keeptpa.palicobot.audio.AudioController;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Play extends Command {
    @Override
    public String[] getName() {
        return new String[]{"play"};
    }

    @Override
    public String getDescription() {
        return "Play audio";
    }

    @Override
    public void Execute(MessageReceivedEvent event, String[] args) {
        Chatter.Speak(event.getChannel(), "Wait a minute...");
        AudioController.instance.AddTrack(args[1]);
    }
}
