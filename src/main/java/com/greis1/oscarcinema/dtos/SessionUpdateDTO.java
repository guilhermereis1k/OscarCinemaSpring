package com.greis1.oscarcinema.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.greis1.oscarcinema.entities.Movie;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class SessionUpdateDTO {
    private Integer roomNumber;
    private String projectorType;
    private String isItDubbed;
    private Movie movie;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate sessionDate;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime sessionTime;
}
