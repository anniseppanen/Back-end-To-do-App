package k24.todo;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;


import k24.todo.domain.Priority;
import k24.todo.domain.Priority_repository;
import k24.todo.domain.Todo;
import k24.todo.domain.Todo_repository;
import k24.todo.domain.Todo_user;
import k24.todo.domain.Todo_user_repository;

@DataJpaTest
// Estää Spring Boottia muuttamasta tietokannan tietoja
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class RepositoryTests {
    private Todo_repository todo_repository;
    private Priority_repository priority_repository;
    private Todo_user_repository user_repository;

    @Autowired
    public RepositoryTests(Todo_repository todo_repository, Priority_repository priority_repository, Todo_user_repository user_repository) {
        this.todo_repository = todo_repository;
        this.priority_repository = priority_repository;
        this.user_repository = user_repository;
    }

    @Test
    public void testFindAllByOrderByDeadlineAsc() {
        Priority priority = new Priority(1);
        priority_repository.save(priority);
        Todo todo1 = new Todo("text", priority, "2024-03-26");
        Todo todo2 = new Todo("text", priority, "2024-04-26");
        Todo todo3 = new Todo("text", priority, "2025-03-26");
        todo_repository.save(todo1);
        todo_repository.save(todo2);
        todo_repository.save(todo3);
        List<Todo> todo_asc = todo_repository.findAllByOrderByDeadlineAsc();
        String deadline = todo_asc.get(0).getDeadline();
        String minimum_deadline = "2024-03-26";
        assertEquals(deadline, minimum_deadline);
    }

    @Test
    public void testFindAllByOrderByPriorityAsc() {
        Priority priority = new Priority(1);
        Priority priority2 = new Priority(2);
        Priority priority3 = new Priority(3);
        priority_repository.save(priority);
        priority_repository.save(priority2);
        priority_repository.save(priority3);
        Todo todo1 = new Todo("text", priority, "2024-03-26");
        Todo todo2 = new Todo("text", priority2, "2024-03-26");
        Todo todo3 = new Todo("text", priority3, "2024-03-26");
        todo_repository.save(todo1);
        todo_repository.save(todo2);
        todo_repository.save(todo3);
        List<Todo> todo_asc = todo_repository.findAllByOrderByPriorityAsc();
        Priority priority_asc = todo_asc.get(0).getPriority();
        assertEquals(priority_asc.getPriority(), priority.getPriority());
    }

    @Test
    public void testFindByUsername() {
        Todo_user user = user_repository.findByUsername("user");
        assertEquals(user.getUsername(), "user");
    }
}