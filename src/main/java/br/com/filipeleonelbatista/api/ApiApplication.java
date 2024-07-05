package br.com.filipeleonelbatista.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(
	info = @Info(
		title = "Documentação API Pessoas V1.0",
		version = "1.0",
		description = "Documentando uma API básica de gerenciamento de pessoas",
		contact = @Contact(name = "Filipe Batista", email = "filipe.x2016@gmail.com", url = "https://filipeleonelbatista.dev.br")
	)
)
public class ApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

}
