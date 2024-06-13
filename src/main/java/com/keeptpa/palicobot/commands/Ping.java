package com.keeptpa.palicobot.commands;

import com.keeptpa.palicobot.Chatter;
import com.keeptpa.palicobot.Command;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Ping extends Command {
    @Override
    public String[] getName() {
        return new String[]{"ping"};
    }

    @Override
    public String getDescription() {
        return "Ping";
    }
    @Override
    public void Execute(MessageReceivedEvent event, String[] args) {
        Chatter.Speak(event.getChannel(), String.format("Pong <@%s>", event.getAuthor().getId()));
    }
}
