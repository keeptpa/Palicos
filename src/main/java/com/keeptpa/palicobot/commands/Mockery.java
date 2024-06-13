package com.keeptpa.palicobot.commands;

import com.keeptpa.palicobot.Chatter;
import com.keeptpa.palicobot.Command;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Mockery extends Command {

    @Override
    public String[] getName() {
        return new String[]{"mockery", "嘲笑"};
    }

    @Override
    public String getDescription() {
        return "Mock someone";
    }

    @Override
    public void Execute(MessageReceivedEvent event, String[] args) {

        event.getMessage().getMentions().getUsers().forEach(user ->
                Chatter.Speak(event.getChannel(), String.format("<@%s> 👈🤣", user.getId())
                ));
    }
}
