package com.keeptpa.palicobot;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MessageReceiveListener extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String message = event.getMessage().getContentDisplay();
        String author = event.getAuthor().getName();
        System.out.printf("[%s] %#s: %s\n",
                event.getChannel(),
                event.getAuthor(),
                event.getMessage().getContentDisplay());

        if(message.startsWith("!")) {
            CommandDealer.instance.commandDistribution(event);
        }else{
            MessageDealer.instance.messageDealer(event);
        }
    }

    @Override
    public void onMessageReactionAdd(MessageReactionAddEvent event){

    }
}
