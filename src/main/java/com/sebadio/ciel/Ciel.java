package com.sebadio.ciel;

import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

import javax.security.auth.login.LoginException;

public class Ciel {

    private final ShardManager shardManager;
    public Ciel() throws LoginException {
        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault("");
        builder.setStatus(OnlineStatus.ONLINE);
        builder.setActivity(Activity.playing("testing"));
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
