package k24.todo.domain;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
public class Priority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long priority_id;
    private int priority;

    // Tämä estää loputtoman loopin tapahtumisen
    @JsonIgnore
    // CascadeType.ALL viittaa siihen, että jos todo, jolla on jokin prioriteetti poistetaan, poistetaan myös tämän priority-taulun tiedot
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "priority")
    private List<Todo> todos;

    public Priority() {}
    
    public Priority(int priority) {
        super();
        this.priority = priority;
    }

    public Long getPriority_id() {
        return priority_id;
    }

    public void setPriority_id(Long priority_id) {
        this.priority_id = priority_id;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public List<Todo> getTodos() {
        return todos;
    }

    public void setTodos(List<Todo> todos) {
        this.todos = todos;
    }
}
