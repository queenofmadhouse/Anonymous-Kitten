package org.example.javabot.configuration;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BotTexts {

    // UserMessageHandler texts
    private final String startMessage;
    private final String messageToBannedUser;
    private final String askPostId;
    private final String askName;
    private final String postDoesntExist;
    private final String askForCorrectPostId;
    private final String askForComment;
    private final String waitForPublication;
    private final String errorInSending;
    private final String fullCommentName;
    private final String fullCommentComment;

    // ChannelMessageHandler texts
    private final String channelMessage;

    // BlackListHandler texts
    private final String banUser;
    private final String unbanUser;
    private final String userAlreadyBanned;
    private final String userSuccessfullyBanned;
    private final String banMessageForUser;
    private final String userSuccessfullyUnbanned;
    private final String unbanMessageForUser;
    private final String userIsNotBanned;
    private final String newComment;
}
