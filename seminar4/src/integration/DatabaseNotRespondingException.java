package integration;

public class DatabaseNotRespondingException extends Exception {
    public DatabaseNotRespondingException(String errorMessage) {
        super(errorMessage);
    }
}
