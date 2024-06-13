package com.keeptpa.palicobot;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Command {

    public String[] getName() {
        return new String[]{""};
    }

    public String getDescription() {
        return "";
    }

    public void Execute(MessageReceivedEvent event, String[] args) {

    }
}
