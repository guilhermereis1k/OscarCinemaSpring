package com.greis1.oscarcinema.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private User user;

    private String session;
    private Integer roomNumber;
    private String projectorType;
    private boolean isItDubbed;
    private Double totalPaid;

    private List<String> seats;

    public Order(Movie movie, User user, String session, Integer roomNumber, String projectorType, Boolean isItDubbed, List<String> seats) {
        this.movie = movie;
        this.user = user;
        this.session = session;
        this.roomNumber = roomNumber;
        this.projectorType = projectorType;
        this.isItDubbed = isItDubbed;
        this.seats = seats;
        this.totalPaid = seats.size() * 15.00;
    }

    public Order(Long orderId, Movie movie, User user, String session, Integer roomNumber, String projectorType, Boolean isItDubbed, List<String> seats) {
        this.id = orderId;
        this.movie = movie;
        this.user = user;
        this.session = session;
        this.roomNumber = roomNumber;
        this.projectorType = projectorType;
        this.isItDubbed = isItDubbed;
        this.seats = seats;
        this.totalPaid = seats.size() * 15.00;
    }
}
