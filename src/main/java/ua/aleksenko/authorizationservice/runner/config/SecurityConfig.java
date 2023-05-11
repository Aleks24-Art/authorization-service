package ua.aleksenko.authorizationservice.runner.config;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
				.csrf().disable()
				.formLogin().disable()
				.httpBasic().disable()
				.sessionManagement(sm -> sm.sessionCreationPolicy(STATELESS))
				.authorizeHttpRequests()
				.requestMatchers("/api/v1/registration", "/api/v1/login", "/api/v1/token/validate")
				.permitAll();
		return http.build();
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOriginPatterns(List.of("*"));
		configuration.setAllowedMethods(List.of("*"));
		configuration.setAllowedHeaders(List.of("*"));
		configuration.setAllowCredentials(true);
		configuration.setExposedHeaders(List.of("*"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(@NonNull CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOriginPatterns("*")
						.allowedMethods("*")
						.allowedHeaders("*")
						.allowCredentials(true)
						.exposedHeaders("*");
			}
		};
	}
}
