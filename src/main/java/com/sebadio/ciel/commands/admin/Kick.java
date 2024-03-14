package com.sebadio.ciel.commands.admin;

import com.sebadio.ciel.helpers.CommandUserReason;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jetbrains.annotations.NotNull;

public class Kick extends CommandUserReason {

    private final String successMessage = "User kicked successfully!";
    private final String errorMessage = "User could not be kicked.";

    public Kick(@NotNull MessageReceivedEvent event) {
        super(event);
        if(userSnowflake == null || guild == null) return;

        if(!guild.getSelfMember().hasPermission(Permission.KICK_MEMBERS)){
            event.getMessage().reply("I don't have permissions to ban members in this server!").queue();
            return;
        }

        boolean userHasPermission = event.getMember().hasPermission(Permission.KICK_MEMBERS);
        if(!userHasPermission) {
            event.getMessage().reply("You don't have permissions to do that.").queue();
            return;
        }

        guild.kick(userSnowflake)
                .reason(reason)
                .queue(
                        s -> message.reply(successMessage).queue(),
                        f -> message.reply(errorMessage).queue()
                );
    }

    public Kick(@NotNull SlashCommandInteractionEvent event) {
        super(event);
        if(userSnowflake == null || guild == null) return;

        if(!guild.getSelfMember().hasPermission(Permission.KICK_MEMBERS)){
            event.reply("I don't have permissions to ban members in this server!").queue();
            return;
        }

        guild.kick(userSnowflake)
                .reason(reason)
                .queue(
                        s -> event.reply(successMessage).queue(),
                        f -> event.reply(errorMessage).queue()
                );
    }
}
