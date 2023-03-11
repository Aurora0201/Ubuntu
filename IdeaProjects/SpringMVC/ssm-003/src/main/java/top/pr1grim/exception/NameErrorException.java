package top.pr1grim.exception;

public class NameErrorException extends UserException{
    public NameErrorException() {
    }

    public NameErrorException(String message) {
        super(message);
    }
}
