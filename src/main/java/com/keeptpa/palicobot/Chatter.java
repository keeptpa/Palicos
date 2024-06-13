package com.keeptpa.palicobot;

import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;

public class Chatter {
    public static void Speak(MessageChannelUnion channel, String message) {
        channel.sendMessage(message).queue();
    }
}
