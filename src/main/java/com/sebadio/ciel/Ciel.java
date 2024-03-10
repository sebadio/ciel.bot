package com.sebadio.ciel;

import com.sebadio.ciel.helpers.Config;
import com.sebadio.ciel.listeners.PrefixEventListener;
import com.sebadio.ciel.listeners.ReadyEventListener;
import com.sebadio.ciel.listeners.SlashCommandEventListener;
import io.github.cdimascio.dotenv.Dotenv;
import io.github.cdimascio.dotenv.DotenvException;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

import javax.security.auth.login.LoginException;

public class Ciel {
    private final Dotenv config;
    private final ShardManager shardManager;
    public ShardManager getShardManager() {
        return shardManager;
    }
    public Dotenv getConfig(){return config;}

    public Ciel() throws LoginException, DotenvException {

        config = Config.get();

        String TOKEN = config.get("TOKEN");
        String STATUS = config.get("STATUS");
        String ACTIVITY = config.get("ACTIVITY");
        String OWNER_ID = config.get("OWNER_ID");

        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(TOKEN);
        builder.enableIntents(
                GatewayIntent.GUILD_MESSAGES,
                GatewayIntent.MESSAGE_CONTENT,
                GatewayIntent.GUILD_MEMBERS
        );
        builder.setStatus(OnlineStatus.valueOf(STATUS));
        builder.setActivity(Activity.playing(ACTIVITY));
        shardManager = builder.build();

        // Listener registration
        shardManager.addEventListener(new ReadyEventListener());
        shardManager.addEventListener(new PrefixEventListener());
        shardManager.addEventListener(new SlashCommandEventListener());
    }

    public static void main(String[] args){
        try {
            Ciel bot = new Ciel();
        }catch (LoginException e){
            System.out.println("ERROR: TOKEN was not provided.");
        }catch (DotenvException e){
            System.out.println("ERROR: .env File either not present or invalid data was provided.");
        }
    }

}
