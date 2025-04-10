package pl.edu.pg.eti;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;


@SpringBootApplication(scanBasePackages = "pl.edu.pg.eti")
@EnableDiscoveryClient
public class WebApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
	}

	@Bean
	public RouteLocator routeLocator(
			RouteLocatorBuilder builder,
			@Value("${gateway.host}") String host
	) {
		return builder
				.routes()
				.route("categories", route -> route
						.host(host)
						.and()
						.path(
								"/api/categories/{id}",
								"/api/categories"
						)
						.uri("lb://spring-app-categories")
				)
				.route("instruments", route -> route
						.host(host)
						.and()
						.path(
								"/api/instruments",
								"/api/instruments/{id}",
								"/api/instruments/category",
								"/api/instruments/category/{id}"
						)
						.uri("lb://spring-app-instruments")
				)
				.build();
	}

	@Bean
	public CorsWebFilter corsWebFilter() {

		final CorsConfiguration corsConfig = new CorsConfiguration();
		corsConfig.setAllowedOrigins(Collections.singletonList("*"));
		corsConfig.setMaxAge(3600L);
		corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PUT"));
		corsConfig.addAllowedHeader("*");

		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfig);

		return new CorsWebFilter(source);
	}

}
