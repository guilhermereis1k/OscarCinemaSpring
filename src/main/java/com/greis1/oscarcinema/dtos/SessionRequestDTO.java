package com.greis1.oscarcinema.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
public class SessionRequestDTO {
    private String projectorType;
    private String isItDubbed;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate sessionDate;

    private Long movieId;
}