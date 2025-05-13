package com.greis1.oscarcinema.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.greis1.oscarcinema.dtos.SessionCreateDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
    private String isItDubbed;

    @JsonFormat(pattern = "dd-MM-yyyy", timezone = "America/Sao_Paulo")
    private LocalDate sessionDate;

    private LocalTime sessionTime;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @JsonIgnore
    @OneToMany(mappedBy = "session")
    private List<Order> orders = new ArrayList<>();

    public Session(Integer roomNumber, String projectorType, String isItDubbed, Movie movie, LocalDate sessionDate, LocalTime sessionTime) {
        this.roomNumber = roomNumber;
        this.projectorType = projectorType;
        this.isItDubbed = isItDubbed;
        this.movie = movie;
        this.sessionDate = sessionDate;
        this.sessionTime = sessionTime;
    }

    public Session(Movie movie, SessionCreateDTO sessionCreateDTO) {
        this.roomNumber = sessionCreateDTO.getRoomNumber();
        this.projectorType = sessionCreateDTO.getProjectorType();
        this.isItDubbed = sessionCreateDTO.getIsItDubbed();
        this.movie = movie;
        this.sessionDate = sessionCreateDTO.getSessionDate();
        this.sessionTime = sessionCreateDTO.getSessionTime();
    }
}
