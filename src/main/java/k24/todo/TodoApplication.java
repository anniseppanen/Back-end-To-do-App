package k24.todo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import k24.todo.domain.Todo_user;
import k24.todo.domain.Todo_user_repository;

@SpringBootApplication
public class TodoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoApplication.class, args);
	}

	/*@Bean
	public CommandLineRunner demo(Todo_user_repository todo_user_repository) {
		return (args) -> {
			Todo_user user = new Todo_user("user", "$2a$12$usVKZCf8sy181z.oxwno6ubp6Vnp6ketU4C9u1y6mQFXBTmkp8szG", "USER");
			Todo_user admin = new Todo_user("admin", "$2a$12$moydUO8UiNAilmXQVDXZbOBB3pwGNcx4IizV1EHMMKnxj2SAAViey", "ADMIN");
			
			todo_user_repository.save(user);
			todo_user_repository.save(admin);
		};
	}*/

}
