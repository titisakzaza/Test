package application;

import java.util.ArrayList;
import java.util.List;

public class User {
	private String username;
    private String password;
    private String role; // "user" หรือ "admin"

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getRole() { return role; }
}





