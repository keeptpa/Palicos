package com.keeptpa.palicobot;

import java.util.ArrayList;
import java.util.List;

public class BotState {
    public static BotState instance = new BotState();

    public boolean autoPraiseMode = false;
    public List<String> autoPraiseEmojiCode = new ArrayList<>();

    public String poemAPI = "";
}
