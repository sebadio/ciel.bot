package com.sebadio.ciel.commands.admin;

import com.sebadio.ciel.helpers.CommandUserReason;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jetbrains.annotations.NotNull;

public class Kick extends CommandUserReason {

    public Kick(@NotNull MessageReceivedEvent event){
        super(event);

        if(message == null) return;

        guild.kick(userSnowflake)
                .reason(reason)
                .queue(
                        s -> message.reply("User kicked successfully!").queue(),
                        f -> message.reply("User could not be kicked.").queue()
                );
    }
}
