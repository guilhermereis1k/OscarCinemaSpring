package com.greis1.oscarcinema.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderCreateDTO {
    private String userDocumentId;
    private Long sessionId;
    private List<String> seats;
}