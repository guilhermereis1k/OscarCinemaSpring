package com.greis1.oscarcinema.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class MovieUpdateDTO {
    private String name;
    private String imageUrl;
    private String description;
    private Integer minimumAge;
}
