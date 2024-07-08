package br.com.filipeleonelbatista.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(
	info = @Info(
		title = "People API V1.0 Documentation",
		version = "1.0",
		description = "Documenting a basic people management API",
		contact = @Contact(name = "Filipe Batista", email = "filipe.x2016@gmail.com", url = "https://filipeleonelbatista.dev.br")
	)
)
public class ApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

}
