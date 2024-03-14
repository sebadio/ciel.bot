package com.sebadio.ciel.commands.misc;

import com.sebadio.ciel.helpers.Config;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import org.jetbrains.annotations.NotNull;

public class Invite {

    private final String inviteLink = "https://discord.com/oauth2/authorize?client_id=%s&permissions=18703474748519&scope=bot+applications.commands";
    private final String inviteMessage = "You can invite me by clicking the button";
    private final String buttonMessage = "Invite Me!";

    public Invite(@NotNull MessageReceivedEvent event) {
        String CLIENT_ID  = Config.get().get("CLIENT_ID");
        String formattedInviteLink = String.format(inviteLink, CLIENT_ID);

        event.getMessage()
                .reply(inviteMessage)
                .addActionRow(
                        Button.link(formattedInviteLink, buttonMessage))
                .queue();
    }

    public Invite(@NotNull SlashCommandInteractionEvent event) {
        String CLIENT_ID = Config.get().get("CLIENT_ID");
        String formattedInviteLink = String.format(inviteLink, CLIENT_ID);

        event.reply(inviteMessage)
                .addActionRow(
                        Button.link(formattedInviteLink, buttonMessage)
                ).queue();
    }

}
