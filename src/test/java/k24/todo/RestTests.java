package k24.todo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import k24.todo.domain.Priority;
import k24.todo.domain.Priority_repository;
import k24.todo.domain.Todo;
import k24.todo.domain.Todo_repository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
// Estää Spring Boottia muuttamasta tietokannan tietoja
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class RestTests {
    @Autowired
    private WebApplicationContext webAppContext;
    private MockMvc mockMvc;
    
    @Autowired
    private Todo_repository todo_repository;
    @Autowired
    private Priority_repository priority_repository;
  
    
    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
        Priority priority = new Priority(1);
        priority_repository.save(priority);
        Todo todo = new Todo("Todo", priority, "2024-03-26");
        todo.setId(1L);
        todo_repository.save(todo);
    }

    @Test
    public void testGetTodoList() throws Exception {
        mockMvc.perform(get("/todo_list")).andExpect(status().isOk());
    }

    @Test
    public void testGetTodoById() throws Exception {
        mockMvc.perform(get("/todo_list/1")).andExpect(status().isOk());
    }

    @Test
    public void testReturnTypeIsJSON() throws Exception {
        mockMvc.perform(get("/todo_list"))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testDeleteTodo() throws Exception {
        mockMvc.perform(delete("/todo_list/1")).andExpect(status().isOk());
    }

    @Test
    public void testSaveTodo() throws Exception {
        mockMvc.perform(post("/todo_list"))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.todo_text").value("Todo"));
    }

}
