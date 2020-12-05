package javaldz26.demo.miniblog26.exceptions;

public class UserDoesntExistException extends RuntimeException{
    private String username;

    public UserDoesntExistException(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
    @Override
    public String getMessage() {
        return String.format("User identified by %s couldn't be found");
    }
}
