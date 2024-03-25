package it.contrader;

import it.contrader.service.UserService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringWebApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringWebApplication.class, args);
//		UserService myEntityService = context.getBean(UserService.class);
//		myEntityService.popolaDatabase();
	}
}
