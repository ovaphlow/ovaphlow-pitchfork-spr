package ovaphlow.pitchfork.spr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SprApplication {

	public static void main(String[] args) {
		SpringApplication.run(SprApplication.class, args);
	}

}
