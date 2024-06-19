package com.keeptpa.palicobot;

import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;
import net.dv8tion.jda.api.requests.restaction.MessageCreateAction;

public class Chatter {
    public static MessageCreateAction speak(MessageChannelUnion channel, String message) {
        MessageCreateAction action = channel.sendMessage(message);
        action.queue();
        return action;
    }

    public static MessageCreateAction SpeakWithoutQueue(MessageChannelUnion channel, String message) {
        return channel.sendMessage(message);
    }
}
