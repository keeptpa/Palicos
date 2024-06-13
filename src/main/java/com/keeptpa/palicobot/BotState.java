package com.keeptpa.palicobot;

import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;

import java.util.ArrayList;
import java.util.List;

public class BotState {
    public static BotState instance = new BotState();

    public String prefix = "!";
    public boolean autoPraiseMode = false;
    public List<String> autoPraiseEmojiCode = new ArrayList<>();

    public String poemAPI = "";
}
