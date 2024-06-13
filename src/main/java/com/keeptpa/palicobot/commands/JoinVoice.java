package com.keeptpa.palicobot.commands;

import com.keeptpa.palicobot.audio.AudioController;
import com.keeptpa.palicobot.Chatter;
import com.keeptpa.palicobot.Command;
import net.dv8tion.jda.api.entities.channel.unions.AudioChannelUnion;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class JoinVoice extends Command {
    @Override
    public String[] getName() {
        return new String[]{"joinvoice"};
    }

    @Override
    public String getDescription() {
        return "Join voice channel";
    }

    @Override
    public void Execute(MessageReceivedEvent event, String[] args) {
        if(event.getMember().getVoiceState() == null) {
            Chatter.Speak(event.getChannel(), "You are not in voice channel");
            return;
        }

        AudioChannelUnion channel = event.getMember().getVoiceState().getChannel();
        event.getGuild().getAudioManager().openAudioConnection(channel);
        AudioController.instance.connect(channel);
    }
}
