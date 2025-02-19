package services;

import models.User;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    private List<User> users; // Store users in a list (use DB later)

    public UserService() {
        this.users = new ArrayList<>();
        // Default admin account
        users.add(new User("admin", "admin123", "Admin"));
    }

    // Register a new user
    public boolean register(String username, String password, String role) {
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                System.out.println("Username already exists! Try another one.");
                return false;
            }
        }
        users.add(new User(username, password, role));
        System.out.println("User registered successfully!");
        return true;
    }

    // Login user
    public User login(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username) && user.checkPassword(password)) {
                System.out.println("Login successful! Welcome, " + user.getRole());
                return user;
            }
        }
        System.out.println("Invalid username or password!");
        return null;
    }
}
