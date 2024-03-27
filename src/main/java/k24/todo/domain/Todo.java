package k24.todo.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

@Entity
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "text cannot be empty")
    private String todo_text;
    @Pattern(regexp = "^\\d{4}-(0?[1-9]|1[0-2])-(0?[1-9]|[12][0-9]|3[01])$", 
            message = "deadline must be in the format yyyy-MM-dd, and the month must be between 1-12 and the day must be between 1-31")
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
