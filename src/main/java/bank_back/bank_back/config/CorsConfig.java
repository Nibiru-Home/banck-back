package bank_back.bank_back.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    private static final String[] ALLOWED_METHODS = { "GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS" };
    private static final List<String> DEFAULT_ORIGIN_PATTERNS = List.of(
            "http://localhost:*",
            "http://127.0.0.1:*",
            "http://[::1]:*",
            "https://localhost:*",
            "https://127.0.0.1:*",
            "https://[::1]:*",
            "http://banck.nibiruhome.store",
            "https://banck.nibiruhome.store",
            "http://bank.nibiruhome.store",
            "https://bank.nibiruhome.store");

    private final List<String> allowedOriginPatterns;

    public CorsConfig(
            @Value("${app.cors.allowed-origins:http://localhost:*,http://127.0.0.1:*,http://[::1]:*,https://localhost:*,https://127.0.0.1:*,https://[::1]:*,http://bank.nibiruhome.store,https://bank.nibiruhome.store}") String allowedOriginPatterns) {
        this.allowedOriginPatterns = Stream.concat(
                Arrays.stream(allowedOriginPatterns.split(","))
                        .map(String::trim)
                        .filter(value -> !value.isBlank()),
                DEFAULT_ORIGIN_PATTERNS.stream())
                .distinct()
                .toList();
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildCorsConfiguration());
        return new CorsFilter(source);
    }

    private CorsConfiguration buildCorsConfiguration() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOriginPatterns(allowedOriginPatterns);
        config.setAllowedMethods(Arrays.asList(ALLOWED_METHODS));
        config.addAllowedHeader(CorsConfiguration.ALL);
        config.setAllowCredentials(true);
        config.setMaxAge(3600L);
        return config;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns(allowedOriginPatterns.toArray(String[]::new))
                .allowedMethods(ALLOWED_METHODS)
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600);
    }
}
