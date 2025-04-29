package com.greis1.oscarcinema.controllers;

import com.greis1.oscarcinema.dtos.SessionCreateDTO;
import com.greis1.oscarcinema.dtos.SessionUpdateDTO;
import com.greis1.oscarcinema.entities.Order;
import com.greis1.oscarcinema.entities.Session;
import com.greis1.oscarcinema.entities.User;
import com.greis1.oscarcinema.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/session")
public class SessionController {

    @Autowired
    SessionService sessionService;

    @PostMapping("/create")
    public ResponseEntity<Session> insertSession(@RequestBody SessionCreateDTO sessionCreateDTO) {
        Session session = sessionService.insertSession(sessionCreateDTO);
        return ResponseEntity.ok().body(session);
    }

    @GetMapping
    public ResponseEntity<List<Session>> findAllSessions() {
        List<Session> sessionList = sessionService.findAllSessions();
        return ResponseEntity.ok().body(sessionList);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Session> findSessionById(@PathVariable Long id) {
        Session session = sessionService.findSessionById(id);
        return ResponseEntity.ok().body(session);
    }

    @GetMapping("/users/{sessionId}")
    public ResponseEntity<List<User>> findUsersBySessionId(@PathVariable Long sessionId) {
        List<User> users = sessionService.findUsersBySessionId(sessionId);
        return ResponseEntity.ok().body(users);
    }

    @GetMapping("/movie/{movieId}")
    public ResponseEntity<List<Session>> findSessionsByMovieId(@PathVariable Long movieId) {
        List<Session> sessionList = sessionService.findSessionsByMovieId(movieId);
        return ResponseEntity.ok().body(sessionList);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteSession(@PathVariable Long id) {
        sessionService.deleteSession(id);
        return ResponseEntity.ok().body("Deleted successfully.");
    }

    @PatchMapping("/change/{id}")
    public ResponseEntity<Session> changeSession(@PathVariable Long id,
                                                 @RequestBody SessionUpdateDTO sessionDTO) {
        Session updated = sessionService.changeSession(id, sessionDTO);
        return ResponseEntity.ok().body(updated);
    }
}
