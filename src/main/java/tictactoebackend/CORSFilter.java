package tictactoebackend;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * A custom web filter built on top of the spring default configuration.
 */
@Configuration
public class CORSFilter extends WebMvcConfigurationSupport {
    /**
     * Adds Cross Origin support for localhost and similar issues.
     * @param registry  The cors registry to edit for more origins.
     */
    @Override
    protected void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedOrigins("*")
                .allowedHeaders("*")
                .allowCredentials(false);
    }
}
