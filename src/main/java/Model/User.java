package Model;

public class User {
    private final String username;
    private final String password;
    private final String pin;

    public User(String username, String password, String pin) {
        this.username = username;
        this.password = password;
        this.pin = pin;
    }

    public String getUsername() {
        return username;
    }

    public String getPin() {
        return pin;
    }

}
