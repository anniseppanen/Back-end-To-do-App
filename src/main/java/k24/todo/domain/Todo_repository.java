package k24.todo.domain;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

// Todo_repository perii CRUD-toiminteita
public interface Todo_repository extends CrudRepository<Todo, Long> {
    //List<Todo> findAllByOrderByDeadlineAsc();
    // ChatGPT:llä luotu SQL-lause, jonka avulla saan järjestettyä to do:t prioriteetin mukaan
    //@Query("SELECT t FROM Todo t JOIN FETCH t.priority ORDER BY t.priority ASC")
    //List<Todo> findAllByOrderByPriorityAsc();
}
