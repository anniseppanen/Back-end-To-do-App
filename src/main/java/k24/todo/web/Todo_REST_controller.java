package k24.todo.web;

import org.springframework.web.bind.annotation.RestController;

import k24.todo.domain.Todo;
import k24.todo.domain.Todo_repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@RestController
public class Todo_REST_controller {
    @Autowired
    Todo_repository todo_repository;
    
    // Käytin ResponseEntityä, koska ongelmaksi koitui se, jos lista on tyhjä ja metodin tulisi kuitenkin palauttaa lista
    @GetMapping("/todo_list")
    public ResponseEntity<?> getAllTodos() {
        List<Todo> todos = (List<Todo>) todo_repository.findAll();
        if (todos.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(todos);
        }
    }

    @GetMapping("/todo_list/{id}")
    public @ResponseBody ResponseEntity<?> getTodoById(@PathVariable Long id) {
        Optional<Todo> todo_list = todo_repository.findById(id);
        return todo_list.map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping("/todo_list")
    public ResponseEntity<Todo> saveTodo(@RequestBody Todo todo) {
        Todo saved_todo = todo_repository.save(todo);
        return ResponseEntity.ok().body(saved_todo);
    }

    @PutMapping("/todo_list/{id}")
    public ResponseEntity<?> updateTodo(@PathVariable Long id, @RequestBody Todo todo_details) {
        Optional<Todo> todo = todo_repository.findById(id);
        if (!todo.isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            Todo updated_todo = todo.get();
            updated_todo.setTodo_text(todo_details.getTodo_text());
            updated_todo.setDeadline(todo_details.getDeadline());
            updated_todo.setPriority(todo_details.getPriority());
            todo_repository.save(updated_todo);
            return ResponseEntity.ok(updated_todo);
        }
    }

    @DeleteMapping("/todo_list/{id}")
    public ResponseEntity<?> deleteTodo(@PathVariable Long id) {
        Optional<Todo> todo = todo_repository.findById(id);
        if (!todo.isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            todo_repository.deleteById(id);
            return ResponseEntity.ok().build();
        }
    }
}
