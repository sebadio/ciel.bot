package com.sebadio.ciel.commands.misc;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class Avatar {

    public Avatar(@NotNull MessageReceivedEvent event) {
        User user = event.getAuthor();
        String avatar = user.getAvatarUrl();

        if (avatar == null || avatar.isEmpty()) {
            event.getMessage().reply("Couldn't get the user's avatar.").queue();
            return;
        }

        MessageEmbed embed = createEmbed(avatar, user.getName());

        event.getMessage()
                .replyEmbeds(embed)
                .queue();
    }

    public Avatar(@NotNull SlashCommandInteractionEvent event) {

        User user = event.getUser();
        String avatar = user.getAvatarUrl();

        if (avatar == null || avatar.isEmpty()) {
            event.reply("Couldn't get the user avatar.").queue();
            return;
        }

        MessageEmbed embed = createEmbed(avatar, user.getName());

        event.replyEmbeds(embed).queue();
    }

    @NotNull
    private MessageEmbed createEmbed(@NotNull String avatar, String name) {
        avatar = avatar.concat("?size=2048");

        EmbedBuilder mb = new EmbedBuilder();
        mb.setFooter("ciel.bot");
        mb.setImage(avatar);
        mb.setTitle(String.format("%s's avatar", name));
        mb.setColor(new Color(122, 211, 214));

        return mb.build();
    }

}
