package guru.springframework.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;

/**
 * Created by juancho on 21/01/2017.
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan("guru.springframework")
public class JpaIntegrationConfig {
}
