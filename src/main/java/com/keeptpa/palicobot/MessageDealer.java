package com.keeptpa.palicobot;

import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class MessageDealer {
    public static MessageDealer instance = new MessageDealer();
    public void messageDealer(MessageReceivedEvent event) {
        BotState botState = BotState.getBotState(event.getChannel());
        if(botState.isAutoPraiseMode()) {
            for (int i = 0; i < botState.autoPraiseEmojiCode.size(); i++) {
                event.getMessage().addReaction(Emoji.fromUnicode(botState.autoPraiseEmojiCode.get(i))).queue();
            }
        }
    }
}
