package lk.ijse.SpringBootNoteTaker.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {}
    public UserNotFoundException(String message) {}
    public UserNotFoundException(String message, Throwable cause) {}
}
