package com.keeptpa.palicobot;

import com.keeptpa.palicobot.audio.AudioController;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;

public class ReactionDealer {
    public static ReactionDealer instance = new ReactionDealer();
    public void reactionDealer(MessageReactionAddEvent event) {
        String codePoint = event.getReaction().getEmoji().asUnicode().getAsCodepoints();
        String songIndex = String.valueOf(codePoint.charAt(3));
        if(AudioController.getController(event.getChannel()).isNowWaitingSelectSong()){
            AudioController.getController(event.getChannel()).OnReceiveReactionSelectingSong(Integer.parseInt(songIndex));
        }
    }
}
