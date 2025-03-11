package com.greis1.oscarcinema.controllers;

import com.greis1.oscarcinema.dtos.OrderUpdateDTO;
import com.greis1.oscarcinema.entities.Order;
import com.greis1.oscarcinema.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<Order> insertOrder(@RequestBody String userDocumentId, Long movieId, String session, Integer roomNumber, String projectorType, Boolean isItDubbed, List<String> seats){
        Order insertOrder = orderService.insertOrder(userDocumentId, movieId, session, roomNumber, projectorType, isItDubbed, seats);

        return ResponseEntity.ok().body(insertOrder);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Order> findOrderById(@RequestParam Long id){
        Order foundOrder = orderService.findOrderById(id);
        return ResponseEntity.ok().body(foundOrder);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> removeOrderById(@RequestParam Long id){
        orderService.removeOrderById(id);
        return ResponseEntity.ok().body("Deleted successfully.");
    }

    @PatchMapping("/change/")
    public ResponseEntity<Order> changeOrder(@RequestBody Long id, OrderUpdateDTO orderDTO) {
        Order changedOrder = orderService.changeOrder(id, orderDTO);
        return ResponseEntity.ok().body(changedOrder);
    }

}
