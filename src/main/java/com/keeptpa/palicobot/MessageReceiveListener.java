package com.keeptpa.palicobot;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class MessageReceiveListener extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String message = event.getMessage().getContentDisplay();
        String author = event.getAuthor().getName();
        System.out.printf("[%s] %#s: %s\n",
                event.getChannel(),
                event.getAuthor(),
                event.getMessage().getContentDisplay());

        if(message.startsWith(BotState.getBotState(event.getChannel()).prefix)) {
            CommandDealer.instance.commandDistribution(event);
        }else{
            MessageDealer.instance.messageDealer(event);
        }
    }

    @Override
    public void onMessageReactionAdd(@NotNull MessageReactionAddEvent event) {
        if(Objects.requireNonNull(Objects.requireNonNull(event.getMember()).getUser()).isBot()) return;
        ReactionDealer.instance.reactionDealer(event);
    }
}
