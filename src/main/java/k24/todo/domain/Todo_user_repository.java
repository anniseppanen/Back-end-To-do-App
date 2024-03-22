package k24.todo.domain;

import org.springframework.data.repository.CrudRepository;
import java.util.List;


public interface Todo_user_repository extends CrudRepository<Todo_user, Long> {
    Todo_user findByUsername(String username);
}
