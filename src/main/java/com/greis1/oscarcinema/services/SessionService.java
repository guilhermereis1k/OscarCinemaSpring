package com.greis1.oscarcinema.services;

import com.greis1.oscarcinema.dtos.SessionCreateDTO;
import com.greis1.oscarcinema.dtos.SessionRequestDTO;
import com.greis1.oscarcinema.dtos.SessionUpdateDTO;
import com.greis1.oscarcinema.entities.Movie;
import com.greis1.oscarcinema.entities.Order;
import com.greis1.oscarcinema.entities.Session;
import com.greis1.oscarcinema.entities.User;
import com.greis1.oscarcinema.repositories.MovieRepository;
import com.greis1.oscarcinema.repositories.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class SessionService {

    @Autowired
    SessionRepository sessionRepository;

    @Autowired
    MovieRepository movieRepository;

    public Session insertSession(SessionCreateDTO sessionCreateDTO){
        Movie movie = movieRepository.findById(sessionCreateDTO.getMovieId()).orElseThrow(() -> new RuntimeException("Movie not found."));
        Session session = new Session(movie, sessionCreateDTO);
        return sessionRepository.save(session);
    }

    public List<Session> findAllSessions() {
        return sessionRepository.findAll();
    }

    public Session findSessionById(Long sessionId){
        return sessionRepository.findById(sessionId).orElseThrow(() -> new RuntimeException("Session not found."));
    }

    public List<User> findUsersBySessionId(Long sessionId){
        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Session not found."));

        return session.getOrders()
                .stream()
                .map(Order::getUser)
                .distinct()
                .collect(Collectors.toList());
    }

    public List<Session> findSessionsByMovieId(Long movieId) {
        return sessionRepository.findAll()
                .stream()
                .filter(session -> session.getMovie().getId().equals(movieId))
                .collect(Collectors.toList());
    }

    public List<Session> findSessionsByFilter(SessionRequestDTO sessionRequestDTO) {
        return sessionRepository.findAll()
                .stream()
                .filter(session -> session.getMovie().getId().equals(sessionRequestDTO.getMovieId()))
                .filter(session -> session.getProjectorType().equalsIgnoreCase(sessionRequestDTO.getProjectorType()))
                .filter(session -> session.getIsItDubbed().equals(sessionRequestDTO.getIsItDubbed()))
                .filter(session -> session.getSessionDate().equals(sessionRequestDTO.getSessionDate()))
                .collect(Collectors.toList());
    }


    public void deleteSession(Long id){
        sessionRepository.deleteById(id);
    }

    public Session changeSession(Long id, SessionUpdateDTO sessionDTO) {
        Session existingSession = sessionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Session not found."));

        if (sessionDTO.getRoomNumber() != null) {
            existingSession.setRoomNumber(sessionDTO.getRoomNumber());
        }

        if (sessionDTO.getSessionDate() != null) existingSession.setSessionDate(sessionDTO.getSessionDate());

        if (sessionDTO.getSessionTime() != null) {
            existingSession.setSessionTime(sessionDTO.getSessionTime());
        }

        if (sessionDTO.getProjectorType() != null) {
            existingSession.setProjectorType(sessionDTO.getProjectorType());
        }

        if (Objects.nonNull(sessionDTO.getIsItDubbed())) {
            existingSession.setIsItDubbed(sessionDTO.getIsItDubbed());
        }

        if (sessionDTO.getMovie() != null) {
            existingSession.setMovie(sessionDTO.getMovie());
        }

        return sessionRepository.save(existingSession);
    }
}
