package com.sebadio.ciel.commands.admin;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jetbrains.annotations.NotNull;

import java.time.OffsetDateTime;
import java.util.List;

public class Purge {

    private final String successMessage = "Successfully erased %d messages from existence!";
    private final String errorMessage = "An error occurred while erasing the messages.";

    public Purge(@NotNull MessageReceivedEvent event) {
        Message message = event.getMessage();

        String[] args = message.getContentRaw().split(" ");

        if (args.length < 1) {
            message.reply("You need to provide a quantity of messages you want to purge!").queue();
            return;
        }

        int msgQuantity;
        try {
            msgQuantity = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            message.reply("Please provide a valid quantity of messages to erase.").queue();
            return;
        }

        if (msgQuantity > 100 || msgQuantity <= 0) {
            message.reply("I can only erase between 1 and 100 messages at a time.").queue();
            return;
        }

        TextChannel channel = event.getChannel().asTextChannel();
        channel.getHistory().retrievePast(Math.min(msgQuantity, 100) + 1)
                .queue(
                        messages -> processMessages(event, messages, channel),
                        failure -> channel.sendMessage(errorMessage).queue()
                );
    }

    public Purge(@NotNull SlashCommandInteractionEvent event) {
        int quantity = event.getOption("quantity").getAsInt();

        if (quantity > 100 || quantity < 1) {
            event.reply("I can only remove between 1 and 100 messages at a time!.").setEphemeral(true).queue();
            return;
        }

        TextChannel channel = event.getChannel().asTextChannel();
        channel.getHistory().retrievePast(Math.min(quantity, 100))
                .queue(
                        messages -> processMessages(event, messages, channel),
                        failure -> event.reply(errorMessage).queue()
                );

    }


    private void processMessages(@NotNull MessageReceivedEvent event, @NotNull List<Message> messages, @NotNull TextChannel channel) {
        List<Message> messagesToDelete = filterDeletableMessages(messages);
        if (messagesToDelete.size() > 1) messagesToDelete.remove(0);


        int finalMsgQuantity = messagesToDelete.size();
        channel.deleteMessages(messagesToDelete).queue(
                success -> channel.sendMessage(String.format(successMessage, finalMsgQuantity)).queue(),
                failure -> channel.sendMessage(errorMessage).queue()
        );
    }

    private void processMessages(@NotNull SlashCommandInteractionEvent event, @NotNull List<Message> messages, @NotNull TextChannel channel) {
        List<Message> messagesToDelete = filterDeletableMessages(messages);
        int finalMsgQuantity = messagesToDelete.size();
        channel.deleteMessages(messagesToDelete).queue(
                success -> event.reply(String.format(successMessage, finalMsgQuantity)).queue(),
                failure -> event.reply(errorMessage).queue()
        );
    }


    private List<Message> filterDeletableMessages(@NotNull List<Message> messages) {
        return new java.util.ArrayList<>(messages.stream()
                .filter(m -> m.getTimeCreated().isAfter(OffsetDateTime.now().minusWeeks(2)))
                .toList());
    }

}
