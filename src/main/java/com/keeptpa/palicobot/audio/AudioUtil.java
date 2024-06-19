package com.keeptpa.palicobot.audio;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AudioUtil {

    public static String getYouTubeId(String youTubeUrl) { // Thanks Roman Smoliar@StackOverflow
        String pattern = "(?<=youtu.be/|watch\\?v=|/videos/|embed\\/)[^#\\&\\?]*";
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(youTubeUrl);
        if(matcher.find()){
            return matcher.group();
        } else {
            return "error";
        }
    }
}
