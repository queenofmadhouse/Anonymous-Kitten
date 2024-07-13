package org.example.javabot.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name = "app.constants.bot.language", havingValue = "uk")
public class UkrainianConfig {

    private final String startMessage = "Ласкаво просимо до бота, який дозволяє надсилати анонімні коментарі." + "\n" +
            "\n" +
            "Щоб надіслати коментар: \n" +
            "\n" +
            "1. Введіть номер посту, вказаний в коментарі від бота під постом. \n" +
            "\n" +
            "2. Введіть нік (просимо вас вводити лише одне ім'я, яким ви будете користуватися в анонімних коментарях, щоб уникнути технічних накладок, запам'ятайте або запишіть його). \n" +
            "\n" +
            "3. Залиште свій коментар та надішліть його боту.";

    private final String channelMessage = "Щоб надіслати повідомлення в коментарі анонімно: \n" +
            "\n" +
            "1. Перейдіть до бота: {0}\n" +
            "\n" +
            "2. Введіть номер цього посту: {1}\n\n" +
            "4. Введіть нік (просимо вас вводити лише одне ім'я, яким ви будете користуватися в анонімних коментарях, щоб уникнути технічних накладок, запам'ятайте або запишіть його). \n" +
            "\n" +
            "3. Залиште свій коментар та надішліть його боту.";

    private final String messageToBannedUser = "Ви порушували правила спільноти, тому були заблоковані";
    private final String askPostId = "Введіть ID посту:";
    private final String askName = "Введіть ім'я:";
    private final String postDoesntExist = "Пост із зазначеним ID не існує";
    private final String askForCorrectPostId = "Введіть коректний ID посту";
    private final String askForComment = "Напишіть ваш коментар";
    private final String waitForPublication = "Ваш коментар збережено, очікуйте на його публікацію!";
    private final String errorInSending = "Сталася помилка під час збереження вашого коментаря. Будь ласка, почніть спочатку.";
    private final String fullCommentName = "Ім'я: ";
    private final String fullCommentComment = "Коментар: ";


    private final String banUser = "Заблокувати користувача";
    private final String unbanUser = "Розблокувати користувача";
    private final String userAlreadyBanned = "Цей користувач вже заблокований";
    private final String userSuccessfullyBanned = "Користувача успішно заблоковано";
    private final String banMessageForUser = "Ви були заблоковані в цьому боті за порушення правил спільноти";
    private final String userSuccessfullyUnbanned = "Користувача успішно розблоковано";
    private final String unbanMessageForUser = "Ви були розблоковані в цьому боті";
    private final String userIsNotBanned = "Цей користувач не заблокований";
    private final String newComment = "Новий коментар";


    @Bean
    public BotTexts botTexts() {
        return BotTexts.builder()
                .startMessage(startMessage)
                .channelMessage(channelMessage)
                .messageToBannedUser(messageToBannedUser)
                .askPostId(askPostId)
                .askName(askName)
                .postDoesntExist(postDoesntExist)
                .askForCorrectPostId(askForCorrectPostId)
                .askForComment(askForComment)
                .waitForPublication(waitForPublication)
                .errorInSending(errorInSending)
                .fullCommentName(fullCommentName)
                .fullCommentComment(fullCommentComment)
                .banUser(banUser)
                .unbanUser(unbanUser)
                .userAlreadyBanned(userAlreadyBanned)
                .userSuccessfullyBanned(userSuccessfullyBanned)
                .banMessageForUser(banMessageForUser)
                .userSuccessfullyUnbanned(userSuccessfullyUnbanned)
                .unbanMessageForUser(unbanMessageForUser)
                .userIsNotBanned(userIsNotBanned)
                .newComment(newComment)
                .build();
    }
}
