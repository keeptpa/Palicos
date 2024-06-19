package com.keeptpa.palicobot.commands;

import com.keeptpa.palicobot.Chatter;
import com.keeptpa.palicobot.Command;
import com.keeptpa.palicobot.Configuer;
import com.keeptpa.palicobot.audio.AudioController;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Play extends Command {
    @Override
    public String[] getName() {
        return new String[]{"play"};
    }

    @Override
    public String getDescription() {
        return "Play audio";
    }

    @Override
    public void Execute(MessageReceivedEvent event, String[] args) { //let arg above 1 be a song's name
        String songName = "";
        for(int i = 1; i < args.length; i++){
            songName += args[i] + " ";
        }
        if(args.length < 1){
            Chatter.speak(event.getChannel(), Configuer.localize("Not_Enough_Args"));
            return;
        }

        if(!AudioController.isChannelHasConnectedAudio(event.getChannel())){
            Chatter.speak(event.getChannel(), Configuer.localize("Not_Connect_Yet"));
            return;
        }
        Chatter.speak(event.getChannel(), String.format(Configuer.localize("Searching_Music"), songName));

        if(songName.contains("youtube.com")){
            String videoId = getYouTubeId(songName);
            AudioController.getController(event.getChannel()).addYTBSong(videoId);
        }else{
            AudioController.getController(event.getChannel()).searchYTB(songName);
        }
    }

    //Actually I think this is a built-in function in lavaplayer.
    private String getYouTubeId (String youTubeUrl) { // Thanks Roman Smoliar@StackOverflow
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
