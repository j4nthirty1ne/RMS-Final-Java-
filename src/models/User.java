package models;

public class User {


    private String username;
    private String password; // This should be hashed in real applications
    private String role; // "Customer", "Staff", "Admin"

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getRole() { return role; }

    public boolean checkPassword(String inputPassword) {
        return this.password.equals(inputPassword); // For security, use hashing (e.g., BCrypt)
    }
}
