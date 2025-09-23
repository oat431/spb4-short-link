package panomete.project.shtlk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ShtlkApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShtlkApplication.class, args);
    }

}
