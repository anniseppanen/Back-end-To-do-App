package k24.todo.domain;
import java.util.Date;

import jakarta.persistence.*;

@Entity
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String todo_text;
    private String deadline;

    @ManyToOne
    @JoinColumn(name = "priority_id")
    private Priority priority;

    public Todo() {
    }

    public Todo(String todo_text, Priority priority, String deadline) {
        super();
        this.todo_text = todo_text;
        this.priority = priority;
        this.deadline = deadline;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTodo_text() {
        return todo_text;
    }

    public void setTodo_text(String todo_text) {
        this.todo_text = todo_text;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }
}
