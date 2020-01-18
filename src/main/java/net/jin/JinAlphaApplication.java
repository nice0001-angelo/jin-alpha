package net.jin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class JinAlphaApplication {

	public static void main(String[] args) {
		SpringApplication.run(JinAlphaApplication.class, args);
	}

}
