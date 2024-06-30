package org.example.javabot.bot.handlers;

import lombok.RequiredArgsConstructor;
import org.example.javabot.cache.LRUSet;
import org.example.javabot.exception.TelegramRuntimeException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UpdateHandler {

    @Value("${app.constants.bot.channel-id}")
    private Long channelId;
    @Value("${app.constants.bot.chat-group-id}")
    private Long chatGroupId;
    @Value("${app.constants.bot.comments-list-chat-id}")
    private Long commentsListChatId;
    private final BlackListHandler blackListHandler;
    private final ChannelMessageHandler channelMessageHandler;
    private final UserMessageHandler userMessageHandler;
    private final LRUSet<String> processedMediaGroups = new LRUSet<>(50);

    public List<SendMessage> handleUpdate(Update update) {

        if (update.hasCallbackQuery()) {
            return blackListHandler.handleButtons(update);
        }

        if (update.hasMessage()) {

            Message message = update.getMessage();

            String mediaGroupId = message.getMediaGroupId();
            if (mediaGroupId != null && (!processedMediaGroups.add(mediaGroupId))) {
                return Collections.emptyList();
            }

            Long senderId;
            if (message.getSenderChat() != null) {
                senderId = message.getSenderChat().getId();
            } else {
                senderId = message.getFrom().getId();
            }

            if (senderId.equals(channelId)) {
                return channelMessageHandler.handleMessage(update);
            }

            if (!message.getChatId().equals(chatGroupId)
                    && !message.getChatId().equals(commentsListChatId)) {
                return userMessageHandler.handleMessage(update);
            } else {
                return Collections.emptyList();
            }
        }

        throw new TelegramRuntimeException("Can't handle update");
    }
}
