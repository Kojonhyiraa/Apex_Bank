package Model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private String password;
    private String pin;

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
