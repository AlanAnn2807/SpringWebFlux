package com.example.demo;

import com.example.demo.model.Movie;
import com.example.demo.repository.MovieRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;

/**
 * https://auth0.com/blog/introduction-getting-started-with-spring-webflux-api/
 *
 */
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner init(ReactiveMongoOperations operations, MovieRepository movieRepository) {
		return args -> {
			Flux<Movie> productFlux = Flux.just(
					new Movie(null, "Avenger: Infinity Wars", "Action", LocalDateTime.now()),
					new Movie(null, "Gladiator", "Drama/Action", LocalDateTime.now()),
					new Movie(null, "Black Panther", "Action", LocalDateTime.now()))
					.flatMap(movieRepository::save);

			productFlux
					.thenMany(movieRepository.findAll())
					.subscribe(System.out::println);
		};
	}


}
