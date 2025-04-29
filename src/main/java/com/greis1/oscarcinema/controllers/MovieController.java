package com.greis1.oscarcinema.controllers;

import com.greis1.oscarcinema.dtos.MovieUpdateDTO;
import com.greis1.oscarcinema.entities.Movie;
import com.greis1.oscarcinema.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/movie")
public class MovieController {

    @Autowired
    MovieService movieService;

    @PostMapping("/create")
    public ResponseEntity<Movie> insertMovie(@RequestBody Movie movie){
        Movie insertedMovie = movieService.insertMovie(movie);
        return ResponseEntity.ok().body(insertedMovie);
    }

    @GetMapping
    public ResponseEntity<List<Movie>> findAllMovies() {
        List<Movie> movies = movieService.findAllMovies();
        return ResponseEntity.ok().body(movies);
    }

    @GetMapping("/search/id/{id}")
    public ResponseEntity<Movie> findMovieById(@PathVariable Long id) {
        Movie foundMovie = movieService.findMovieById(id);
        return ResponseEntity.ok().body(foundMovie);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> removeMovie(@PathVariable Long id){
        movieService.removeMovie(id);
        return ResponseEntity.ok().body("Deleted successfully.");
    }

    @PatchMapping("/change/{id}")
    public ResponseEntity<Movie> changeMovie(@PathVariable Long id, @RequestBody MovieUpdateDTO movieDTO) {
        Movie changedMovie = movieService.changeMovie(id, movieDTO);
        return ResponseEntity.ok().body(changedMovie);
    }
}
