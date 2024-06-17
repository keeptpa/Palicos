package com.keeptpa.palicobot;

import java.util.Map;

public class Configuer {
    private static Map<String, String> languageSet;
    public static void Initialize(){
        LoadLanguage();
    }

    private static void LoadLanguage(){
        languageSet = BotConst.getLanguagePair();
    }

    public static String localize(String key){
        return languageSet.get(key);
    }
}
