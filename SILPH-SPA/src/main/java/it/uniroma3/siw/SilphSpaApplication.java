package it.uniroma3.siw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SilphSpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SilphSpaApplication.class, args);
	}

}
