package org.example.javabot.bot.handlers;

import lombok.RequiredArgsConstructor;
import org.example.javabot.configuration.BotTexts;
import org.example.javabot.exception.TelegramRuntimeException;
import org.example.javabot.service.BlockedUsersService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BlackListHandler {

    @Value("${app.constants.bot.comments-list-chat-id}")
    private Long commentsListChatId;
    private final String BLOCK_USER_BUTTON = "block_user_id_";
    private final String UNBLOCK_USER_BUTTON = "unblock_user_id_";
    private final BlockedUsersService blockedUsersService;
    private final BotTexts botTexts;

    public List<SendMessage> handleButtons(Update update) {
        String data = update.getCallbackQuery().getData();

        if (data.startsWith(BLOCK_USER_BUTTON)) {

            Long chatId = Long.parseLong(data.substring(BLOCK_USER_BUTTON.length()));
            List<SendMessage> sendMessageList = new ArrayList<>();

            if (blockedUsersService.existByUserId(chatId)) {

                InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
                List<List<InlineKeyboardButton>> inlineKeyboardRows = new ArrayList<>();
                List<InlineKeyboardButton> unbanUserRow = new ArrayList<>();
                InlineKeyboardButton unbanUserBtn = new InlineKeyboardButton();
                unbanUserBtn.setText(botTexts.getUnbanUser());
                unbanUserBtn.setCallbackData(UNBLOCK_USER_BUTTON + chatId);
                unbanUserRow.add(unbanUserBtn);
                inlineKeyboardRows.add(unbanUserRow);
                inlineKeyboardMarkup.setKeyboard(inlineKeyboardRows);

                SendMessage messageToList = new SendMessage();
                messageToList.setChatId(commentsListChatId);
                messageToList.setText(botTexts.getUserAlreadyBanned());
                messageToList.setReplyToMessageId(update.getCallbackQuery().getMessage().getMessageId());
                messageToList.setReplyMarkup(inlineKeyboardMarkup);
                messageToList.enableMarkdown(true);

                sendMessageList.add(messageToList);
            } else {
                SendMessage messageToList = new SendMessage();
                messageToList.setChatId(commentsListChatId);
                messageToList.setText(botTexts.getUserSuccessfullyBanned());
                messageToList.setReplyToMessageId(update.getCallbackQuery().getMessage().getMessageId());

                SendMessage messageToUser = new SendMessage();
                messageToUser.setChatId(chatId);
                messageToUser.setText(botTexts.getBanMessageForUser());

                sendMessageList.add(messageToList);
                sendMessageList.add(messageToUser);

                blockedUsersService.save(chatId);
            }

            return sendMessageList;
        }

        if (data.startsWith(UNBLOCK_USER_BUTTON)) {
            Long chatId = Long.parseLong(data.substring(UNBLOCK_USER_BUTTON.length()));

            List<SendMessage> sendMessageList = new ArrayList<>();

            if (blockedUsersService.existByUserId(chatId)) {
                SendMessage messageToList = new SendMessage();
                messageToList.setChatId(commentsListChatId);
                messageToList.setText(botTexts.getUserSuccessfullyUnbanned());
                messageToList.setReplyToMessageId(update.getCallbackQuery().getMessage().getMessageId());

                SendMessage messageToUser = new SendMessage();
                messageToUser.setChatId(chatId);
                messageToUser.setText(botTexts.getUnbanMessageForUser());

                sendMessageList.add(messageToList);
                sendMessageList.add(messageToUser);

                blockedUsersService.deleteByUserId(chatId);
            } else {

                InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
                List<List<InlineKeyboardButton>> inlineKeyboardRows = new ArrayList<>();
                List<InlineKeyboardButton> banUserRow = new ArrayList<>();
                InlineKeyboardButton banUserBtn = new InlineKeyboardButton();
                banUserBtn.setText(botTexts.getBanUser());
                banUserBtn.setCallbackData(BLOCK_USER_BUTTON + chatId);
                banUserRow.add(banUserBtn);
                inlineKeyboardRows.add(banUserRow);
                inlineKeyboardMarkup.setKeyboard(inlineKeyboardRows);

                SendMessage messageToList = new SendMessage();
                messageToList.setChatId(commentsListChatId);
                messageToList.setText(botTexts.getUserIsNotBanned());
                messageToList.setReplyToMessageId(update.getCallbackQuery().getMessage().getMessageId());
                messageToList.setReplyMarkup(inlineKeyboardMarkup);
                messageToList.enableMarkdown(true);

                sendMessageList.add(messageToList);
            }

            return sendMessageList;
        }

        throw new TelegramRuntimeException("Can't handle ban buttons");
    }

    public SendMessage sendCommentToAdminChat(Long chatId, String text) {
        SendMessage sendMessage = new SendMessage();

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> inlineKeyboardRows = new ArrayList<>();
        List<InlineKeyboardButton> banUserRow = new ArrayList<>();
        InlineKeyboardButton banUserBtn = new InlineKeyboardButton();
        banUserBtn.setText(botTexts.getBanUser());
        banUserBtn.setCallbackData(BLOCK_USER_BUTTON + chatId);
        banUserRow.add(banUserBtn);
        inlineKeyboardRows.add(banUserRow);
        inlineKeyboardMarkup.setKeyboard(inlineKeyboardRows);

        sendMessage.enableMarkdown(true);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        sendMessage.setChatId(commentsListChatId);
        sendMessage.setText(botTexts.getNewComment() + "\n\n" +
                text);

        return sendMessage;
    }
}
