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
        BotState.instance.autoPraiseMode = !BotState.instance.autoPraiseMode;
        BotState.instance.autoPraiseEmojiCode.clear();
        if(args.length > 1){
            for (int i = 1; i < args.length; i++) {
                BotState.instance.autoPraiseEmojiCode.add(args[i]);
            }
        }else{
            BotState.instance.autoPraiseEmojiCode.add("U+1F928\t");
        }

        Chatter.Speak(event.getChannel(), "Auto praise mode " + (BotState.instance.autoPraiseMode ? "enabled" : "disabled"));
    }
}
