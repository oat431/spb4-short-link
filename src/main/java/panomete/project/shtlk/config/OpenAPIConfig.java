package panomete.project.shtlk.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Short link API",
                version = "0.1-dev",
                description = "Short link API Document"
        ),
        servers = {
                @Server(
                        description = "Local ENV 1",
                        url = "http://localhost:8080"
                )
        }
)

public class OpenAPIConfig {
}
