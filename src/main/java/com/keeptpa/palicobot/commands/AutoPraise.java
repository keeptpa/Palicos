package com.keeptpa.palicobot.commands;

import com.keeptpa.palicobot.BotState;
import com.keeptpa.palicobot.Chatter;
import com.keeptpa.palicobot.Command;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class AutoPraise extends Command {
    @Override
    public String[] getName() {
        return new String[]{"ap", "auto-praise"};
    }

    @Override
    public String getDescription() {
        return "Auto reaction when someone send a message";
    }
    @Override
    public void Execute(MessageReceivedEvent event, String[] args) {
        BotState botState = BotState.getBotState(event.getChannel());
        botState.setAutoPraiseMode(!botState.isAutoPraiseMode());
        botState.autoPraiseEmojiCode.clear();
        if(args.length > 1){
            for (int i = 1; i < args.length; i++) {
                botState.autoPraiseEmojiCode.add(args[i]);
            }
        }else{
            botState.autoPraiseEmojiCode.add("U+1F928\t");
        }

        Chatter.Speak(event.getChannel(), "Auto praise mode " + (botState.isAutoPraiseMode() ? "enabled" : "disabled"));
    }
}
