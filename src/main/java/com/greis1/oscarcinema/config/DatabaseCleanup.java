package com.greis1.oscarcinema.config;

import com.greis1.oscarcinema.services.MovieService;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class DatabaseCleanup {

    @Autowired
    private MovieService movieService;

    public void cleanUpDatabase() {
        movieService.removeAllMovies();
    }
}