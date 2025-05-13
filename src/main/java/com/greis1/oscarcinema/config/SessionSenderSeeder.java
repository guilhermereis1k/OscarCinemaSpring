package com.greis1.oscarcinema.config;

import com.greis1.oscarcinema.entities.Movie;
import com.greis1.oscarcinema.entities.Session;
import com.greis1.oscarcinema.repositories.MovieRepository;
import com.greis1.oscarcinema.repositories.SessionRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;

@Profile("dev")
@Component
public class SessionSenderSeeder {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private SessionRepository sessionRepository;

    private final Random random = new Random();

    @PostConstruct
    public void seedSessions() {
        if (sessionRepository.count() == 0) {
            List<Movie> movies = movieRepository.findAll();

            for (Movie movie : movies) {
                createSession(movie, LocalDate.now(), LocalTime.of(19, 0), "2D", "dub");
                createSession(movie, LocalDate.now(), LocalTime.of(21, 30), "3D", "leg");

                createSession(movie, LocalDate.now().plusDays(1), LocalTime.of(16, 0), "2D", "dub");
                createSession(movie, LocalDate.now().plusDays(1), LocalTime.of(20, 0), "IMAX", "leg");

                LocalDate nextSaturday = LocalDate.now().with(DayOfWeek.SATURDAY);
                if (nextSaturday.isBefore(LocalDate.now())) {
                    nextSaturday = nextSaturday.plusWeeks(1);
                }

                createSession(movie, nextSaturday, LocalTime.of(14, 0), "3D", "dub");
                createSession(movie, nextSaturday, LocalTime.of(18, 30), "2D", "leg");
            }
        }
    }

    private void createSession(Movie movie, LocalDate date, LocalTime time, String projectorType, String isItDubbed) {
        int roomNumber = random.nextInt(5) + 1;
        Session session = new Session(roomNumber, projectorType, isItDubbed, movie, date, time);
        sessionRepository.save(session);
    }
}
