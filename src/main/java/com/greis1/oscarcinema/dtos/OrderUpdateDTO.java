package com.greis1.oscarcinema.dtos;

import com.greis1.oscarcinema.entities.Movie;
import com.greis1.oscarcinema.entities.Session;
import com.greis1.oscarcinema.entities.User;
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
    private Movie movie;
    private User user;
    private Session session;
    private List<String> seats;
}