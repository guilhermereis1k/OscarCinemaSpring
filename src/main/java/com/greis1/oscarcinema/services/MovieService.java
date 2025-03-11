package com.greis1.oscarcinema.services;

import com.greis1.oscarcinema.dtos.MovieUpdateDTO;
import com.greis1.oscarcinema.entities.Movie;
import com.greis1.oscarcinema.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public Movie insertMovie(Movie movie){
        return movieRepository.save(movie);
    }

    public Movie findMovieById(Long id) {
        return movieRepository.findById(id).orElseThrow(() -> new RuntimeException("Movie not found."));
    }

    public void removeMovie(Long id){
        movieRepository.deleteById(id);
    }

    public Movie changeMovie(Long id, MovieUpdateDTO movieDTO){
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found."));

        if (movieDTO.getName() != null) {
            movie.setName(movieDTO.getName());
        }
        if (movieDTO.getImageUrl() != null) {
            movie.setImageUrl(movieDTO.getImageUrl());
        }
        if (movieDTO.getDescription() != null) {
            movie.setDescription(movieDTO.getDescription());
        }
        if (movieDTO.getMinimumAge() != null) {
            movie.setMinimumAge(movieDTO.getMinimumAge());
        }

        return movieRepository.save(movie);
    }
}
