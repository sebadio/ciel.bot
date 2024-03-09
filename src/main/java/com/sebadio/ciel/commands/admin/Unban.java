package com.sebadio.ciel.commands.admin;

import com.sebadio.ciel.helpers.CommandUserReason;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Unban extends CommandUserReason {

    public Unban(@NotNull MessageReceivedEvent event) {
        super(event);

        guild.unban(userSnowflake)
                .reason(reason)
                .queue(
                        s -> event.getMessage().reply("Unbanned successfully!").queue(),
                        f -> event.getMessage().reply("Failed to unban user").queue()
                );
    }

}
