package com.greis1.oscarcinema.services;

import com.greis1.oscarcinema.dtos.OrderUpdateDTO;
import com.greis1.oscarcinema.entities.Movie;
import com.greis1.oscarcinema.entities.Order;
import com.greis1.oscarcinema.entities.Session;
import com.greis1.oscarcinema.entities.User;
import com.greis1.oscarcinema.repositories.OrderRepository;
import org.aspectj.weaver.ast.Or;
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

    @Autowired
    SessionService sessionService;

    public Order insertOrder(String userDocumentId, Long sessionId, List<String> seats){
        User user = userService.findUserByDocumentId(userDocumentId);
        Session session = sessionService.findSessionById(sessionId);

        Order order = new Order(user, session, seats);

        return orderRepository.save(order);
    }

    public Order findOrderById(Long id){
        return orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found."));
    }

    public void removeOrderById(Long id){
        orderRepository.deleteById(id);
    }

    public Order changeOrder(Long id, OrderUpdateDTO orderDTO) {
        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found."));

        if (orderDTO.getSeats() != null) {
            existingOrder.getSeats().clear();
            existingOrder.getSeats().addAll(orderDTO.getSeats());
            existingOrder.setTotalPaid(existingOrder.getSeats().size() * 15.00);
        }

        if (orderDTO.getUser() != null) {
            existingOrder.setUser(orderDTO.getUser());
        }

        if (orderDTO.getSession() != null) {
            existingOrder.setSession(orderDTO.getSession());
        }

        return orderRepository.save(existingOrder);
    }
}
