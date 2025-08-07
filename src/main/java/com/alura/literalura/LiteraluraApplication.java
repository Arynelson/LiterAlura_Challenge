// Em src/main/java/com/alura/literalura/LiteraluraApplication.java

package com.alura.literalura;

import com.alura.literalura.main.Principal;
import com.alura.literalura.repository.AutorRepository;
import com.alura.literalura.repository.LivroRepository; // Importe o repositório
import org.springframework.beans.factory.annotation.Autowired; // Importe o Autowired
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class LiteraluraApplication {

	// Peça ao Spring para injetar uma instância do repositório
	@Autowired
	private LivroRepository livroRepository;

	@Autowired
	private AutorRepository autorRepository;



	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Bean
	public CommandLineRunner executarAplicacao(Principal principal) {
		return args -> {
			principal.exibeMenu();
		};
	}
}
