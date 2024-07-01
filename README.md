# Anonymous Kitten Bot 😺
- [English Description](#english-description)
- [Описание на русском](#описание-на-русском)
  
## English description
This bot is designed to send anonymous comments in Telegram channels. The main purpose of its creation was to provide people with the opportunity to leave anonymous comments.
Please note that this version of the bot does not have comment moderation before sending them, so it does not provide protection against spam attacks when using this version of the bot.
The latest version of the bot also allows you to block users who leave comments. All new comments are published in a separate chat, where you can block or unblock the user who left a specific comment.
However, there is no way to find out the username or other details of the user.

It uses Java 8 and Spring Boot 2.7.11, as well as Flywaydb to automatically create the necessary schema and tables. The Dockerfile is pre-configured for correct operation, however, it is necessary to specify environment variables (described below). Additionally, you need to set up constants in the application.yml (also described below).

## Setup
### Basic settings:
Create a bot in @BotFather
You need to specify through environment variables:
~~~
1) POSTGRES_DB_URL - URL for connecting to the database (in the format: jdbc:postgresql://HOST:PORT/DB_NAME)
2) POSTGRES_DB_USERNAME: Username for connecting to the database.
3) POSTGRES_DB_PASSWORD: Password for connecting to the database.
4) BOT_TOKEN: Bot token obtained from @BotFather.
5) CHANNEL_ID: ID of the Telegram channel.
6) CHAT_GROUP_ID: ID of a chat group connected to the channel.
7) COMMENTS_LIST_CHAT_ID: ID of a chat (group) created to view the list of comments and manage user bans.
8) LINK_TO_MESSAGE: Link to a message in the chat from step 6.
9) BOT_LINK: Link to the bot.
~~~
### Templates for copying
#### Docker
~~~bash
-e "POSTGRES_DB_PASSWORD=" -e "POSTGRES_DB_URL=" -e "POSTGRES_DB_USERNAME=" -e "BOT_TOKEN=" -e "CHANNEL_ID=" -e "CHAT_GROUP_ID=" -e "COMMENTS_LIST_CHAT_ID=" -e "LINK_TO_MESSAGE=" -e "BOT_LINK="
~~~
#### IDEA:
~~~
BOT_LINK=;BOT_TOKEN=;CHANNEL_ID=;CHAT_GROUP_ID=;COMMENTS_LIST_CHAT_ID=;LINK_TO_MESSAGE=;POSTGRES_DB_PASSWORD=;POSTGRES_DB_URL=;POSTGRES_DB_USERNAME=
~~~
### Changing the database:
1) In the file pom.xml, remove the dependency responsible for working with PostgreSQL and replace it with the one you need
2) In the file application.yml, change "driver-class-name" to the required one

## Описание на русском
Данный бот преднозначен для отправки анонимных комментариев в телеграмм-каналах. Основная цель его создания была в предоставлении людям возможности оставлять анонимные комментарии.
Примите во внимание, что данная версия бота не имеет модерации комментариев перед их отправкой, поэтому не предоставляет защиты от спам-атак при использовании этой версии бота. 
Так же последняя версия бота предоставляет возможность блокировать пользователей оставивших комментарии (все новые комментарии публикуются в отдельном чате, 
есть возможность заблокировать/разблокировать пользователя оставившего конкретный комментарий, но нет возможности узнать юзернейм и тд.
Ипользуется Java 8 и Spring Boot 2.7.11, а так же Flywaydb для автоматического создания необходимой для работы схемы и таблиц

## Настройка
### Основные настройки:
Создать бота в @BotFather
Необходимо указать через переменные среды:
~~~
1) POSTGRES_DB_URL: URL-адрес для подключения к базе данных (в формате: jdbc:postgresql://ХОСТ:ПОРТ/ИМЯ_БД)
2) POSTGRES_DB_USERNAME: Имя пользователя для подключения к базе данных.
3) POSTGRES_DB_PASSWORD: Пароль для подключения к базе данных.
4) BOT_TOKEN: Токен бота, полученный от @BotFather.
5) CHANNEL_ID: Идентификатор Telegram-канала.
6) CHAT_GROUP_ID: Идентификатор группового чата, связанного с каналом.
7) COMMENTS_LIST_CHAT_ID: Идентификатор чата (группы), созданного для просмотра списка комментариев и управления банами пользователей.
8) LINK_TO_MESSAGE: Ссылка на сообщение в чате из пункта 6.
9) LINK_TO_RULES: Ссылка на страницу с правилами сообщества.
10) BOT_LINK: Ссылка на бота.
~~~
### Шаблоны для копирования:
#### Docker
~~~bash
-e "POSTGRES_DB_PASSWORD=" -e "POSTGRES_DB_URL=" -e "POSTGRES_DB_USERNAME=" -e "BOT_TOKEN=" -e "CHANNEL_ID=" -e "CHAT_GROUP_ID=" -e "COMMENTS_LIST_CHAT_ID=" -e "LINK_TO_MESSAGE=" -e "BOT_LINK="
~~~
#### IDEA:
~~~
BOT_LINK=;BOT_TOKEN=;CHANNEL_ID=;CHAT_GROUP_ID=;COMMENTS_LIST_CHAT_ID=;LINK_TO_MESSAGE=;POSTGRES_DB_PASSWORD=;POSTGRES_DB_URL=;POSTGRES_DB_USERNAME=
~~~
### 
### Смена базы данных:
1) В файле pom.xml удалить зависимость отвечающую за работу с postgres на необходимую
2) В файле application.yml изменить "driver-class-name" на необходимый
