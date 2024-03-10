package com.sebadio.ciel.listeners;

import com.sebadio.ciel.helpers.Config;
import com.sebadio.ciel.helpers.UpdateSlashCommands;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ReadyEventListener extends ListenerAdapter {
    @Override
    public void onReady(@NotNull ReadyEvent event) {
        JDA jda = event.getJDA();
        List<SlashCommandData> commandDataList = new UpdateSlashCommands().getCommands();

        Guild devGuild = jda.getGuildById(Config.get().get("DEV_GUILD_ID"));

        if (devGuild != null) {
            devGuild.updateCommands().addCommands(commandDataList).queue(
                    s -> System.out.println("Dev guild slash commands updated successfully!"),
                    f -> System.out.println("Failed to update slash commands in dev guild.")
            );
        }


        jda.updateCommands().addCommands(commandDataList).queue(
                s -> System.out.println("Global slash commands updated successfully!"),
                f -> System.out.println("Failed to update slash commands globally.")
        );

        System.out.println("Bot Ready!");
    }
}