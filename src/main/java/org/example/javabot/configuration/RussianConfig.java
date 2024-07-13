package org.example.javabot.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name = "app.constants.bot.language", havingValue = "ru")
public class RussianConfig {

    private final String startMessage = "Добро пожаловать в бот, позволяющий отправлять анонимные комментарии." + "\n" +
            "\n" +
            "Чтобы отправить комментарий: \n" +
            "\n" +
            "1. Введите номер поста, указанный в комментарии от бота под постом. \n" +
            "\n" +
            "2. Введите ник (просим вас вводить только одно имя которым вы будете пользоваться в анонимных комментариях, чтобы не случались технические накладки, запомните или запишите его). \n" +
            "\n" +
            "3. Оставьте свой комментарий и отправьте в бот.";
    private final String channelMessage = "Чтобы отправить сообщение в комментарии анонимно: \n" +
            "\n" +
            "1. Перейдите в бота: {0}\n" +
            "\n" +
            "2. Введите номер этого поста: {1}\n\n" +
            "4. Введите ник (просим вас вводить только одно имя которым вы будете пользоваться в анонимных комментариях, чтобы не случались технические накладки, запомните или запишите его). \n" +
            "\n" +
            "3. Оставьте свой комментарий и отправьте в бот.";
    private final String messageToBannedUser = "Вы нарушали правила сообщества, поэтому были заблокированы";
    private final String askPostId = "Введите ID поста:";
    private final String askName = "Введите имя:";
    private final String postDoesntExist = "Пост с указанным ID не существует";
    private final String askForCorrectPostId = "Введите корректный ID поста";
    private final String askForComment = "Напишите ваш комментарий";
    private final String waitForPublication = "Ваш комментарий сохранен, ожидайте его публикации!";
    private final String errorInSending = "Произошла ошибка при сохранении вашего комментария. Пожалуйста, начните сначала.";
    private final String fullCommentName = "Имя: ";
    private final String fullCommentComment = "Комментарий: ";

    private final String banUser = "Заблокировать пользователя";
    private final String unbanUser = "Разблокировать пользователя";
    private final String userAlreadyBanned = "Данный пользователь уже заблокирован";
    private final String userSuccessfullyBanned = "Пользователь успешно заблокирован";
    private final String banMessageForUser = "Вы были заблокированы в данном боте за нарушения правил сообщества";
    private final String userSuccessfullyUnbanned = "Пользователь успешно разблокирован";
    private final String unbanMessageForUser = "Вы были разблокированы в данном боте";
    private final String userIsNotBanned = "Данный пользователь не заблокирован";
    private final String newComment = "Новый комментарий";

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
