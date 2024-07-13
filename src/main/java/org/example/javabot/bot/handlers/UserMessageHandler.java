package org.example.javabot.bot.handlers;

import lombok.RequiredArgsConstructor;
import org.example.javabot.configuration.BotTexts;
import org.example.javabot.exception.TelegramRuntimeException;
import org.example.javabot.service.BlockedUsersService;
import org.example.javabot.service.ChannelPostsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class UserMessageHandler {

    @Value("${app.constants.bot.chat-group-id}")
    private Long chatGroupId;
    private final ChannelPostsService channelPostsService;
    private final BlockedUsersService blockedUsersService;
    private final BlackListHandler blackListHandler;
    private final BotTexts botTexts;

    private enum ChatState {
        AWAITING_POST_ID,
        AWAITING_NAME,
        AWAITING_COMMENT,
        NORMAL
    }

    private final Map<Long, ChatState> chatStates = new HashMap<>();
    private final Map<Long, Long> awaitingComments = new HashMap<>();
    private final Map<Long, String> awaitingNames = new HashMap<>();

    public List<SendMessage> handleMessage(Update update) {

        Message message = update.getMessage();
        Long chatId = message.getChatId();

        if (message.getChatId().equals(chatGroupId)) {
            return Collections.emptyList();
        }

        if (blockedUsersService.existByUserId(chatId)) {

            String messageText = botTexts.getMessageToBannedUser();

            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(chatId);
            sendMessage.setText(messageText);

            return Collections.singletonList(sendMessage);
        }

        if (!message.getChatId().equals(chatGroupId)) {

            ChatState state = chatStates.getOrDefault(chatId, ChatState.NORMAL);

            switch (state) {
                case NORMAL:

                    return handleNormalState(chatId, message);
                case AWAITING_POST_ID:

                    return handlePostIdInput(chatId, message.getText());
                case AWAITING_NAME:

                    return handleNameInput(chatId, message.getText());
                case AWAITING_COMMENT:

                    return handleCommentInput(chatId, message.getText());
            }
        }

        throw new TelegramRuntimeException("Can't handle message from user");
    }

    private List<SendMessage> handleNormalState(Long chatId, Message message) {

        List<SendMessage> sendMessageList = new ArrayList<>();

        if (!message.getChatId().equals(chatGroupId) && message.getText().equals("/start")) {

            String text = botTexts.getStartMessage();

            sendMessageList.add(SendMessage.builder()
                            .chatId(chatId)
                            .text(text)
                    .build());
        }
        if (!message.getChatId().equals(chatGroupId)) {

            String text = botTexts.getAskPostId();

            chatStates.put(chatId, ChatState.AWAITING_POST_ID);

            sendMessageList.add(SendMessage.builder()
                    .chatId(chatId)
                    .text(text)
                    .build());
        }

        return sendMessageList;
    }

    private List<SendMessage> handlePostIdInput(Long chatId, String input) {

        List<SendMessage> sendMessageList = new ArrayList<>();

        try {

            Long postId = Long.parseLong(input);

            if (channelPostsService.findByPostId(postId) != null) {

                String text = botTexts.getAskName();

                chatStates.put(chatId, ChatState.AWAITING_NAME);
                awaitingComments.put(chatId, postId);

                sendMessageList.add(SendMessage.builder()
                        .chatId(chatId)
                        .text(text)
                        .build());
            } else {

                String text = botTexts.getPostDoesntExist();

                chatStates.put(chatId, ChatState.NORMAL);

                sendMessageList.add(SendMessage.builder()
                        .chatId(chatId)
                        .text(text)
                        .build());
            }
        } catch (NumberFormatException e) {

            String text = botTexts.getAskForCorrectPostId();

            sendMessageList.add(SendMessage.builder()
                    .chatId(chatId)
                    .text(text)
                    .build());
        }

        return sendMessageList;
    }

    private List<SendMessage> handleNameInput(Long chatId, String name) {

        String text = botTexts.getAskForComment();

        chatStates.put(chatId, ChatState.AWAITING_COMMENT);
        awaitingNames.put(chatId, name);

        return Collections.singletonList(SendMessage.builder()
                .chatId(chatId)
                .text(text)
                .build());
    }


    private List<SendMessage> handleCommentInput(Long chatId, String comment) {

        List<SendMessage> sendMessageList = new ArrayList<>();

        Integer postId = Math.toIntExact(awaitingComments.get(chatId));
        String userName = awaitingNames.get(chatId);

        if (userName != null) {

            String fullComment = botTexts.getFullCommentName() + userName + "\n" + botTexts.getFullCommentComment() + comment;
            String textToUser = botTexts.getWaitForPublication();

            sendMessageList.add(SendMessage.builder()
                    .chatId(chatId)
                    .text(textToUser)
                    .build());

            sendMessageList.add(SendMessage.builder()
                    .chatId(chatGroupId)
                    .text(fullComment)
                    .replyToMessageId(postId)
                    .build());

            sendMessageList.add(blackListHandler.sendCommentToAdminChat(chatId, fullComment));

            chatStates.put(chatId, ChatState.NORMAL);
            awaitingComments.remove(chatId);
            awaitingNames.remove(chatId);
        } else {

            String text = botTexts.getErrorInSending();

            sendMessageList.add(SendMessage.builder()
                    .chatId(chatId)
                    .text(text)
                    .build());

            chatStates.put(chatId, ChatState.NORMAL);
        }

        return sendMessageList;
    }
}
