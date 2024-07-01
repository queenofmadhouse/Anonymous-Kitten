package org.example.javabot.bot.handlers;

import lombok.RequiredArgsConstructor;
import org.example.javabot.entity.ChannelPost;
import org.example.javabot.service.ChannelPostsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

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

    public List<SendMessage> handleMessage(Update update) {

        Message message = update.getMessage();
        int replayId = message.getMessageId();
        long chatId = update.getMessage().getChatId();
        String postLink = linkToMessage + replayId;
        String messageText = "Чтобы отправить сообщение в комментарии анонимно: \n" +
                "\n" +
                "1. Перейдите в бота: " + botLink + "\n" +
                "\n" +
                "2. Введите номер этого поста: " + replayId + "\n" +
                "\n" +
                "4. Введите ник (просим вас вводить только одно имя которым вы будете пользоваться в анонимных комментариях, чтобы не случались технические накладки, запомните или запишите его). \n" +
                "\n" +
                "3. Оставьте свой комментарий и отправьте в бот.";
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
