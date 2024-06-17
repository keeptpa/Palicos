package com.keeptpa.palicobot;

import com.keeptpa.palicobot.commands.*;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

import java.util.EnumSet;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Configuer.Initialize();
        BotConst.POEM_API = args[1];
        RegisterCommands();
        JDABuilder.create(args[0], EnumSet.of(GatewayIntent.GUILD_MESSAGES, GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_VOICE_STATES, GatewayIntent.GUILD_MESSAGE_REACTIONS))
                .addEventListeners(new MessageReceiveListener())
                .setMemberCachePolicy(MemberCachePolicy.VOICE)
                .build();
    }

    public static void RegisterCommands(){
        CommandDealer.instance.registerCommand(new GetPoem());
        CommandDealer.instance.registerCommand(new Ping());
        CommandDealer.instance.registerCommand(new AutoPraise());
        CommandDealer.instance.registerCommand(new Mockery());
        CommandDealer.instance.registerCommand(new MCServer());
        CommandDealer.instance.registerCommand(new Help());
        CommandDealer.instance.registerCommand(new JoinVoice());
        CommandDealer.instance.registerCommand(new Play());
        CommandDealer.instance.registerCommand(new Pause());
        CommandDealer.instance.registerCommand(new PlayControl());
        CommandDealer.instance.registerCommand(new RockPaper());
        CommandDealer.instance.registerCommand(new DisconnectVoice());
    }
}

