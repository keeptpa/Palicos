package com.keeptpa.palicobot.commands;

import com.keeptpa.palicobot.Chatter;
import com.keeptpa.palicobot.Command;
import com.keeptpa.palicobot.Configuer;
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
        Chatter.Speak(event.getChannel(), String.format("%s: %s, PONG! <@%s>", Configuer.localize("My_Latency"), event.getJDA().getGatewayPing(), event.getAuthor().getId()));
    }
}
