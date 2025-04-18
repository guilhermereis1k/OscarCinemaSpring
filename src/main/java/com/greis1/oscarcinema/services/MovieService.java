package com.greis1.oscarcinema.services;

import com.greis1.oscarcinema.dtos.MovieUpdateDTO;
import com.greis1.oscarcinema.entities.Movie;
import com.greis1.oscarcinema.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Movie insertMovie(Movie movie){
        return movieRepository.save(movie);
    }

    public Movie findMovieById(Long id) {
        return movieRepository.findById(id).orElseThrow(() -> new RuntimeException("Movie not found."));
    }

    public void removeMovie(Long id){
        movieRepository.deleteById(id);
    }

    public void removeAllMovies() {
        movieRepository.deleteAll();
    }

    public Movie changeMovie(Long id, MovieUpdateDTO movieDTO){
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found."));

        Movie alteredMovie = new Movie(
                movie.getId(),
                movieDTO.getName(),
                movieDTO.getImageUrl(),
                movieDTO.getDescription(),
                movieDTO.getMinimumAge()
        );

        return movieRepository.save(alteredMovie);
    }
}
