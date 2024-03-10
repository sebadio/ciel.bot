package com.sebadio.ciel.commands.admin;

import com.sebadio.ciel.helpers.CommandUserReason;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Unban extends CommandUserReason {

    private final String successMessage = "Unbanned successfully!";
    private final String errorMessage = "Failed to unban user";

    public Unban(@NotNull MessageReceivedEvent event) {
        super(event);

        guild.unban(userSnowflake)
                .reason(reason)
                .queue(
                        s -> event.getMessage().reply(successMessage).queue(),
                        f -> event.getMessage().reply(errorMessage).queue()
                );
    }

    public  Unban(@NotNull SlashCommandInteractionEvent event){
        super(event);

        guild.unban(userSnowflake)
                .reason(reason)
                .queue(
                        s -> event.reply(successMessage).queue(),
                        f -> event.reply(errorMessage).queue()
                );
    }

}
