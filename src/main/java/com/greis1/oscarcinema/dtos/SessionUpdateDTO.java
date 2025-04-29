package com.greis1.oscarcinema.dtos;

import com.greis1.oscarcinema.entities.Movie;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class SessionUpdateDTO {
    private Integer roomNumber;
    private String projectorType;
    private Boolean isItDubbed;
    private Movie movie;
    private LocalDateTime sessionDateTime;
}
