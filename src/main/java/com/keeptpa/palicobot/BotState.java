package com.keeptpa.palicobot;

import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BotState {

    private static HashMap<String, BotState> states = new HashMap<>(); // <ChannelID, BotState>
    public static BotState getBotState(MessageChannelUnion channel) {
        if (!states.containsKey(channel.getId())) {
            states.put(channel.getId(), new BotState());
        }
        return states.get(channel.getId());
    }
    public String prefix = "!";

    private boolean autoPraiseMode = false;
    public boolean isAutoPraiseMode() {
        return autoPraiseMode;
    }

    public void setAutoPraiseMode(boolean autoPraiseMode) {
        this.autoPraiseMode = autoPraiseMode;
    }

    public List<String> autoPraiseEmojiCode = new ArrayList<>();
}
