package com.sebadio.ciel.commands.admin;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jetbrains.annotations.NotNull;
import java.util.concurrent.TimeUnit;
import com.sebadio.ciel.helpers.CommandUserReason;

public class Ban extends CommandUserReason {

    public Ban(@NotNull MessageReceivedEvent event) {
        super(event);
        guild.ban(userSnowflake, 0, TimeUnit.DAYS)
                .reason(reason)
                .queue(
                        s -> message.reply("User banned successfully!").queue(),
                        f -> message.reply("User could not be banned").queue()
                );
    }

}
