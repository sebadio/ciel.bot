package com.sebadio.ciel.commands.admin;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.UserSnowflake;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jetbrains.annotations.NotNull;
import java.util.concurrent.TimeUnit;
import com.sebadio.ciel.helpers.CommandUserReason;

public class Ban extends CommandUserReason {

    private final String successMessage = "User banned successfully!";
    private final String errorMessage = "User could not be banned";

    public Ban(@NotNull MessageReceivedEvent event) {
        super(event);
        if(userSnowflake == null || guild == null) return;

        if(!guild.getSelfMember().hasPermission(Permission.BAN_MEMBERS)){
            event.getMessage().reply("I don't have permissions to ban members in this server!").queue();
            return;
        }

        boolean userHasPermission = event.getMember().hasPermission(Permission.BAN_MEMBERS);
        if(!userHasPermission) {
            event.getMessage().reply("You don't have permissions to do that.").queue();
            return;
        }


        guild.ban(userSnowflake, 0, TimeUnit.DAYS)
                .reason(reason)
                .queue(
                        s -> message.reply(successMessage).queue(),
                        f -> message.reply(errorMessage).queue()
                );
    }

    public Ban(@NotNull SlashCommandInteractionEvent event) {
        super(event);
        if(userSnowflake == null || guild == null) return;

        if(!guild.getSelfMember().hasPermission(Permission.BAN_MEMBERS)){
            event.reply("I don't have permissions to ban members in this server!").queue();
            return;
        }

        guild.ban(userSnowflake, 0, TimeUnit.DAYS)
                .reason(reason)
                .queue(
                        s -> event.reply(successMessage).queue(),
                        f -> event.reply(errorMessage).queue()
                );
    }

}
