CREATE SCHEMA IF NOT EXISTS bot;

CREATE TABLE bot.channel_posts
(
    id        BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    post_id   BIGINT,
    post_link VARCHAR(256),
    CONSTRAINT pk_channel_post PRIMARY KEY (id)
);

CREATE TABLE bot.blocked_users
(
    id      BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    user_id BIGINT,
    CONSTRAINT pk_blocked_users PRIMARY KEY (id)
);
