package com.sebadio.ciel.commands.misc;

import com.sebadio.ciel.helpers.CommandUser;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class Avatar extends CommandUser {

    public Avatar(@NotNull MessageReceivedEvent event) {
        super(event);
        if(userSnowflake == null || guild == null) return;

        event.getJDA().retrieveUserById(userSnowflake.getId()).queue(
                user ->{
                    String avatar = user.getAvatarUrl();

                    if (avatar == null || avatar.isEmpty()) {
                        event.getMessage().reply("Couldn't get the user's avatar.").queue();
                        return;
                    }

                    MessageEmbed embed = createEmbed(avatar, user.getName());

                    event.getMessage()
                            .replyEmbeds(embed)
                            .queue();
                },
                f -> event.getMessage().reply("Couldn't get the user's avatar.").queue()
        );

    }

    public Avatar(@NotNull SlashCommandInteractionEvent event) {

        User user = event.getOption("user").getAsUser();
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
