package main.java.ru.clevertec.check.exception;

public class InternakServerErrorException extends Exception{

    public InternakServerErrorException() {
    }

    public InternakServerErrorException(String message) {
        super(message);
    }
}
