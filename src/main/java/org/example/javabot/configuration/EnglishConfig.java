package org.example.javabot.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name = "app.constants.bot.language", havingValue = "en")
public class EnglishConfig {

    private final String startMessage = "Welcome to the bot that allows you to send anonymous comments." + "\n" +
            "\n" +
            "To send a comment: \n" +
            "\n" +
            "1. Enter the post ID indicated in the bot's comment under the post. \n" +
            "\n" +
            "2. Enter your nickname (please use only one name for anonymous comments to avoid technical issues, remember or write it down). \n" +
            "\n" +
            "3. Leave your comment and send it to the bot.";

    private final String channelMessage = "To send a message to the comments anonymously: \n" +
            "\n" +
            "1. Go to the bot: {0}\n" +
            "\n" +
            "2. Enter the number of this post: {1}\n\n" +
            "4. Enter your nickname (please use only one name for anonymous comments to avoid technical issues, remember or write it down). \n" +
            "\n" +
            "3. Leave your comment and send it to the bot.";

    private final String messageToBannedUser = "You have violated community rules and have been banned";
    private final String askPostId = "Enter post ID:";
    private final String askName = "Enter name:";
    private final String postDoesntExist = "Post with the specified ID does not exist";
    private final String askForCorrectPostId = "Please enter a valid post ID";
    private final String askForComment = "Write your comment:";
    private final String waitForPublication = "Your comment has been saved, please wait for it to be published!";
    private final String errorInSending = "There was an error saving your comment. Please start over";
    private final String fullCommentName = "Name: ";
    private final String fullCommentComment = "Comment: ";

    private final String banUser = "Ban user";
    private final String unbanUser = "Unban user";
    private final String userAlreadyBanned = "This user is already banned";
    private final String userSuccessfullyBanned = "User has been successfully banned";
    private final String banMessageForUser = "You have been banned from this bot for violating community rules";
    private final String userSuccessfullyUnbanned = "User has been successfully unbanned";
    private final String unbanMessageForUser = "You have been unbanned from this bot";
    private final String userIsNotBanned = "This user is not banned";
    private final String newComment = "New comment";

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
