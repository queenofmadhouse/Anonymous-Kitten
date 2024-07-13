package org.example.javabot.bot.handlers;

import lombok.RequiredArgsConstructor;
import org.example.javabot.configuration.BotTexts;
import org.example.javabot.entity.ChannelPost;
import org.example.javabot.service.ChannelPostsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.text.MessageFormat;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ChannelMessageHandler {

    @Value("${app.constants.bot.bot-link}")
    private String botLink;
    @Value("${app.constants.bot.link-to-message}")
    private String linkToMessage;
    private final ChannelPostsService channelPostsService;
    private final BotTexts botTexts;

    public List<SendMessage> handleMessage(Update update) {

        Message message = update.getMessage();
        int replayId = message.getMessageId();
        long chatId = update.getMessage().getChatId();
        String postLink = linkToMessage + replayId;
        String messageText = MessageFormat.format(botTexts.getChannelMessage(), botLink, replayId);

        SendMessage sendMessage = new SendMessage();

        sendMessage.setChatId(chatId);
        sendMessage.setText(messageText);
        sendMessage.setReplyToMessageId(replayId);
        sendMessage.enableHtml(true);

        channelPostsService.save(ChannelPost.builder()
                .postId((long) replayId)
                .postLink(postLink)
                .build());

        return Collections.singletonList(sendMessage);
    }
}
