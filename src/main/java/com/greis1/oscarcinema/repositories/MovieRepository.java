package com.greis1.oscarcinema.repositories;

import com.greis1.oscarcinema.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

@org.springframework.stereotype.Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
}
