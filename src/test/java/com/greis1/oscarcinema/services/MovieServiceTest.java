package com.greis1.oscarcinema.services;

import com.greis1.oscarcinema.dtos.MovieUpdateDTO;
import com.greis1.oscarcinema.entities.Movie;
import com.greis1.oscarcinema.repositories.MovieRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MovieServiceTest {

    @Mock
    MovieRepository movieRepository;

    @InjectMocks
    MovieService movieService;

    @Test
    public void insertMovie() {
        Long id = 1L;
        Movie movie = new Movie(id, "Anora", "https://upload.wikimedia.org/wikipedia/pt/thumb/8/86/Anora_%28filme%29.jpg/250px-Anora_%28filme%29.jpg", "Anora, uma jovem stripper do Brooklyn, conhece o filho de um oligarca russo na boate em que trabalha.", 18);

        when(movieRepository.save(movie)).thenReturn(movie);

        Movie savedMovie = movieService.insertMovie(movie);

        assertNotNull(savedMovie);
        assertEquals(id, savedMovie.getId());
        assertEquals("Anora", savedMovie.getName());
    }

    @Test
    public void findMovieById() {
        Long id = 1L;
        Movie movie = new Movie(id, "Anora", "https://upload.wikimedia.org/wikipedia/pt/thumb/8/86/Anora_%28filme%29.jpg/250px-Anora_%28filme%29.jpg", "Anora, uma jovem stripper do Brooklyn, conhece o filho de um oligarca russo na boate em que trabalha.", 18);

        when(movieRepository.findById(id)).thenReturn(java.util.Optional.of(movie));

        Movie foundMovie = movieService.findMovieById(id);

        assertNotNull(foundMovie);
        assertEquals(id, foundMovie.getId());
        assertEquals("Anora", foundMovie.getName());
    }

    @Test
    public void removeMovie() {
        Long id = 1L;
        Movie movie = new Movie(id, "Anora", "https://upload.wikimedia.org/wikipedia/pt/thumb/8/86/Anora_%28filme%29.jpg/250px-Anora_%28filme%29.jpg", "Anora, uma jovem stripper do Brooklyn, conhece o filho de um oligarca russo na boate em que trabalha.", 18);

        when(movieRepository.save(movie)).thenReturn(movie);
        Movie savedMovie = movieService.insertMovie(movie);

        movieService.removeMovie(movie.getId());

        when(movieRepository.findById(movie.getId())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> movieService.findMovieById(movie.getId()));
    }

    @Test
    public void changeMovie(){
        Long id = 1L;
        Movie movie = new Movie(id, "Anora", "https://upload.wikimedia.org/wikipedia/pt/thumb/8/86/Anora_%28filme%29.jpg/250px-Anora_%28filme%29.jpg", "Anora, uma jovem stripper do Brooklyn, conhece o filho de um oligarca russo na boate em que trabalha.", 18);

        when(movieRepository.save(movie)).thenReturn(movie);
        Movie savedMovie = movieService.insertMovie(movie);

        when(movieRepository.findById(1L)).thenReturn(Optional.of(movie));

        MovieUpdateDTO movieAltered = new MovieUpdateDTO();
        movieAltered.setName("Amora");

        when(movieRepository.save(movie)).thenReturn(movie);
        movieService.changeMovie(id, movieAltered);

        assertNotNull(movie);
        assertEquals("Amora", movie.getName());
        assertNotEquals("Anora", movie.getName());
    }

  
}