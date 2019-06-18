package com.example.demo.repository;

import com.example.demo.model.Movie;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface MovieRepository  extends ReactiveMongoRepository<Movie,String> {

}
