package com.keeptpa.palicobot.commands;

import com.keeptpa.palicobot.Chatter;
import com.keeptpa.palicobot.Command;
import com.keeptpa.palicobot.Configuer;
import com.keeptpa.palicobot.HttpClient;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.util.ArrayList;

public class MCServer extends Command {
    @Override
    public String[] getName() {
        return new String[]{"mcserver"};
    }

    @Override
    public String getDescription() {
        return "Get a minecraft server info";
    }

    @Override
    public void Execute(MessageReceivedEvent event, String[] args) {
        Chatter.Speak(event.getChannel(), Configuer.localize("Wait_For_It"));

        String result = HttpClient.get("https://api.mcsrvstat.us/3/" + args[1], null);

        JSONObject obj = (JSONObject) JSONValue.parse(result);
        JSONObject players = (JSONObject)obj.get("players");
        JSONArray list = (JSONArray)players.get("list");

        StringBuilder allPlayers = new StringBuilder();
        for(int i = 0; i < list.size(); i++){
            allPlayers.append(((JSONObject) list.get(i)).get("name")).append(", ");
        }
        //Chatter.Speak(event.getChannel(), allPlayers);
        Chatter.Speak(event.getChannel(), "服务器中玩家：" + allPlayers);
    }
}
