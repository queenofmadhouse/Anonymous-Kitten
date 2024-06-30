package org.example.javabot.exception;

public class TelegramRuntimeException extends RuntimeException {

    public TelegramRuntimeException() {
    }

    public TelegramRuntimeException(String message) {
        super(message);
    }

    public TelegramRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public TelegramRuntimeException(Throwable cause) {
        super(cause);
    }
}
