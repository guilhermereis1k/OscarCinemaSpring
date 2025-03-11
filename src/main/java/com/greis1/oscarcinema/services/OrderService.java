package com.greis1.oscarcinema.services;

import com.greis1.oscarcinema.dtos.OrderUpdateDTO;
import com.greis1.oscarcinema.entities.Movie;
import com.greis1.oscarcinema.entities.Order;
import com.greis1.oscarcinema.entities.User;
import com.greis1.oscarcinema.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    MovieService movieService;

    @Autowired
    UserService userService;

    public Order insertOrder(String userDocumentId, Long movieId, String session, Integer roomNumber, String projectorType, Boolean isItDubbed, List<String> seats){
        User user = userService.findUserByDocumentId(userDocumentId);
        Movie movie = movieService.findMovieById(movieId);

        Order order = new Order(movie, user, session, roomNumber, projectorType, isItDubbed, seats);

        return orderRepository.save(order);
    }

    public Order findOrderById(Long id){
        return orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found."));
    }

    public void removeOrderById(Long id){
        orderRepository.deleteById(id);
    }

    public Order changeOrder(Long id, OrderUpdateDTO orderDTO) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found."));

        if (orderDTO.getSession() != null) {
            order.setSession(orderDTO.getSession());
        }
        if (orderDTO.getRoomNumber() != null) {
            order.setRoomNumber(orderDTO.getRoomNumber());
        }
        if (orderDTO.getProjectorType() != null) {
            order.setProjectorType(orderDTO.getProjectorType());
        }
        if (orderDTO.getIsItDubbed() != null) {
            order.setItDubbed(orderDTO.getIsItDubbed());
        }
        if (orderDTO.getSeats() != null) {
            order.setSeats(orderDTO.getSeats());
        }
        if (orderDTO.getTotalPaid() != null) {
            order.setTotalPaid(orderDTO.getTotalPaid());
        }

        return orderRepository.save(order);
    }
}
