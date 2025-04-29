package com.greis1.oscarcinema.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.greis1.oscarcinema.dtos.SessionCreateDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer roomNumber;
    private String projectorType;
    private Boolean isItDubbed;
    private LocalDateTime sessionDateTime;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @JsonIgnore
    @OneToMany(mappedBy = "session")
    private List<Order> orders = new ArrayList<>();

    public Session(Integer roomNumber, String projectorType, Boolean isItDubbed, Movie movie, LocalDateTime sessionDateTime) {
        this.roomNumber = roomNumber;
        this.projectorType = projectorType;
        this.isItDubbed = isItDubbed;
        this.movie = movie;
        this.sessionDateTime = sessionDateTime;
    }

    public Session(Movie movie, SessionCreateDTO sessionCreateDTO) {
        this.roomNumber = sessionCreateDTO.getRoomNumber();
        this.projectorType = sessionCreateDTO.getProjectorType();
        this.isItDubbed = sessionCreateDTO.getIsItDubbed();
        this.movie = movie;
        this.sessionDateTime = sessionCreateDTO.getSessionDateTime();
    }
}
