package k24.todo.domain;

import jakarta.persistence.*;

@Entity
public class Todo_user {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;
    private String username;
    private String password_hash;
    private String role;

    public Todo_user() {}

    public Todo_user(String username, String password_hash, String role) {
        super();
        this.username = username;
        this.password_hash = password_hash;
        this.role = role;
    }

    public Long getId() {
        return user_id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword_hash() {
        return password_hash;
    }

    public String getRole() {
        return role;
    }

    public void setId(Long user_id) {
        this.user_id = user_id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword_hash(String password_hash) {
        this.password_hash = password_hash;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "todo_user [username=" + username + ", password_hash=" + password_hash + ", role=" + role + "]";
    }
    
}
