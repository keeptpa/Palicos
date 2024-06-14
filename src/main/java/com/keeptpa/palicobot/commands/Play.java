package com.keeptpa.palicobot.commands;

import com.keeptpa.palicobot.Chatter;
import com.keeptpa.palicobot.Command;
import com.keeptpa.palicobot.audio.AudioController;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

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
            Chatter.Speak(event.getChannel(), "Not enough args");
            return;
        }

        if(!AudioController.isChannelHasConnectedAudio(event.getChannel())){
            Chatter.Speak(event.getChannel(), "Not connected");
            return;
        }
        Chatter.Speak(event.getChannel(), String.format("Searching [%s] ....👀", songName));
        AudioController.getController(event.getChannel()).AddTrack(songName);
    }
}
