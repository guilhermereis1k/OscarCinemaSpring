package com.greis1.oscarcinema.dtos;

import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDateTime;

@Getter
@Setter
public class SessionCreateDTO {
    private Integer roomNumber;
    private String projectorType;
    private Boolean isItDubbed;
    private LocalDateTime sessionDateTime;
    private Long movieId;
}
