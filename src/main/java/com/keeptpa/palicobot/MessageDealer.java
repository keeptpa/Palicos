package com.keeptpa.palicobot;

import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class MessageDealer {
    public static MessageDealer instance = new MessageDealer();
    public void messageDealer(MessageReceivedEvent event) {
        if(BotState.instance.autoPraiseMode){
            for (int i = 0; i < BotState.instance.autoPraiseEmojiCode.size(); i++) {
                event.getMessage().addReaction(Emoji.fromUnicode(BotState.instance.autoPraiseEmojiCode.get(i))).queue();
            }
        }
    }
}
