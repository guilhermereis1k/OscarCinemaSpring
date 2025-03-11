package com.greis1.oscarcinema.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class OrderUpdateDTO {
    private String session;
    private Integer roomNumber;
    private String projectorType;
    private Boolean isItDubbed;
    private List<String> seats;
    private Double totalPaid;
}