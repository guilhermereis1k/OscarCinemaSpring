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
    @JoinColumn(name = "users_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "session_id")
    private Session session;

    private Double totalPaid;

    @ElementCollection
    private List<String> seats = new ArrayList<>();;

    public Order(User user, Session session, List<String> seats) {
        this.user = user;
        this.session = session;
        this.seats = seats;
        this.totalPaid = seats.size() * 15.00;
    }

    public Order(Long orderId, User user, List<String> seats) {
        this.id = orderId;
        this.user = user;
        this.seats = seats;
        this.totalPaid = seats.size() * 15.00;
    }
}
