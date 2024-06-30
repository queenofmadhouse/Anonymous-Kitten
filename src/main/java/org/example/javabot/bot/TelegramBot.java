package org.example.javabot.bot;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.javabot.bot.handlers.UpdateHandler;
import org.example.javabot.exception.TelegramRuntimeException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Getter
@RequiredArgsConstructor
@Component
public class TelegramBot extends TelegramLongPollingBot {

    @Value("${app.constants.bot.bot-name}")
    private String botName;
    @Value("${app.constants.bot.bot-token}")
    private String botAPI;
    @Value("${app.constants.bot.bot-link}")
    private String botLink;
    private final UpdateHandler updateHandler;

    @Override
    public void onUpdateReceived(Update update) {

        List<SendMessage> messages = updateHandler.handleUpdate(update);

        for (SendMessage message : messages) {
            executeMessageWithRetry(message);
        }
    }

    private void executeMessageWithRetry(SendMessage message) {

        try {
            execute(message);
        } catch (TelegramApiException e) {
            if (e.getMessage().contains("[429]")) {
                Pattern pattern = Pattern.compile("retry after (\\d+)");
                Matcher matcher = pattern.matcher(e.getMessage());

                if (matcher.find()) {
                    long delay = Integer.parseInt(matcher.group(1)) * 1000L;
                    try {
                        Thread.sleep(delay);
                    } catch (InterruptedException ex) {
                        throw new TelegramRuntimeException(e);
                    }
                }
            }
        }
    }

    @Override
    public String getBotUsername() {

        return botName;
    }

    @Override
    public String getBotToken() {

        return botAPI;
    }
}
