package k24.todo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import k24.todo.domain.Priority_repository;
import k24.todo.domain.Todo;
import k24.todo.domain.Todo_repository;


@Controller
public class Todo_controller {
    @Autowired
    Todo_repository todo_repository;
    @Autowired
    Priority_repository priority_repository;
    
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/todos")
    public String showTodos(Model model, @RequestParam(name="sort", required=false) String sort) {
        if("deadline".equals(sort)) {
            model.addAttribute("todo_list", todo_repository.findAllByOrderByDeadlineAsc());
        } else if("priority".equals(sort)) {
            model.addAttribute("todo_list", todo_repository.findAllByOrderByPriorityAsc());
        } else {
        model.addAttribute("todo_list", todo_repository.findAll());
        }
        return "todos";
    }

    @GetMapping("/add")
    public String addTodo(Model model) {
        model.addAttribute("todo", new Todo());
        model.addAttribute("priority_list", priority_repository.findAll());
        return "add";
    }

    @PostMapping("/save_todo")
    public String saveTodo(@Valid @ModelAttribute("todo") Todo todo, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("todo", todo);
            model.addAttribute("priority_list", priority_repository.findAll());
            return "add";
        }
        todo_repository.save(todo);
        return "redirect:todos";
    }

    @GetMapping("delete/{id}")
    public String deleteTodo(@PathVariable("id") Long id, Model model) {
        todo_repository.deleteById(id);
        return "redirect:../todos";
    }
    
    @GetMapping("edit/{id}")
    public String editTodo(@PathVariable("id") Long id, Model model) {
        Todo todo = todo_repository.findById(id).orElse(null);
        model.addAttribute("todo", todo);
        model.addAttribute("priority_list", priority_repository.findAll());
        return "edit";
    }
}

