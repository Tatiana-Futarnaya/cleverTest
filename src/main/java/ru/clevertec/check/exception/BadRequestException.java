package main.java.ru.clevertec.check.exception;

public class BadRequestException extends Exception{

    public BadRequestException() {
    }

    public BadRequestException(String message) {
        super(message);
    }
}
