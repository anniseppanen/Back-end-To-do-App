package k24.todo.web;

import org.springframework.web.bind.annotation.RestController;

import k24.todo.domain.Todo_user;
import k24.todo.domain.Todo_user_repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@RestController
public class Todo_REST_controller {
    @Autowired
    Todo_user_repository todo_user_repository;
    
    // Käytin ResponseEntityä, koska ongelmaksi koitui se, jos lista on tyhjä ja metodin tulisi kuitenkin palauttaa lista
    @GetMapping("/user_list")
    public ResponseEntity<?> getAllUsers() {
        List<Todo_user> todo_users = (List<Todo_user>) todo_user_repository.findAll();
        if (todo_users.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(todo_users);
        }
    }

    @GetMapping("/user_list/{id}")
    public @ResponseBody ResponseEntity<?> getUserById(@PathVariable Long id) {
        Optional<Todo_user> todo_users = todo_user_repository.findById(id);
        return todo_users.map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping("/save_user")
    Todo_user newUser(@RequestBody Todo_user user) {
        return todo_user_repository.save(user);
    }

    @PutMapping("/user_list/{id}")
    Todo_user editUser(@RequestBody Todo_user editedUser, @PathVariable Long id) {
        editedUser.setId(id);
        return todo_user_repository.save(editedUser);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        Optional<Todo_user> todo_user = todo_user_repository.findById(id);
        if (!todo_user.isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            todo_user_repository.deleteById(id);
            return ResponseEntity.ok().build();
        }
    }
}
