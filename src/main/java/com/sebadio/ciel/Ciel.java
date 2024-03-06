package com.sebadio.ciel;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

import javax.security.auth.login.LoginException;

public class Ciel {

    private final Dotenv config;

    private final ShardManager shardManager;
    public Ciel() throws LoginException {

        config = Dotenv.configure().load();

        String TOKEN = config.get("TOKEN");
        String STATUS = config.get("STATUS");
        String ACTIVITY = config.get("ACTIVITY");
        String OWNER_ID = config.get("OWNER_ID");

        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(TOKEN);
        builder.setStatus(OnlineStatus.valueOf(STATUS));
        builder.setActivity(Activity.playing(ACTIVITY));
        shardManager = builder.build();
    }

    public ShardManager getShardManager() {
        return shardManager;
    }

    public static void main(String[] args){
        try {
            Ciel bot = new Ciel();
        }catch (LoginException e){
            System.out.println("ERROR");
            System.out.println(e);
        }
    }

}
