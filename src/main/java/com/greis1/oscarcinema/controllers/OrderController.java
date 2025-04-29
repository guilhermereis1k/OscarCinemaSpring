package com.greis1.oscarcinema.controllers;

import com.greis1.oscarcinema.dtos.OrderCreateDTO;
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
    public ResponseEntity<Order> insertOrder(@RequestBody OrderCreateDTO orderCreateDTO) {
        Order insertOrder = orderService.insertOrder(
                orderCreateDTO.getUserDocumentId(),
                orderCreateDTO.getSessionId(),
                orderCreateDTO.getSeats()
        );

        return ResponseEntity.ok().body(insertOrder);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Order> findOrderById(@PathVariable Long id){
        Order foundOrder = orderService.findOrderById(id);
        return ResponseEntity.ok().body(foundOrder);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> removeOrderById(@PathVariable Long id){
        orderService.removeOrderById(id);
        return ResponseEntity.ok().body("Deleted successfully.");
    }

    @PatchMapping("/change/{id}")
    public ResponseEntity<Order> changeOrder(@PathVariable Long id, @RequestBody OrderUpdateDTO orderDTO) {
        Order changedOrder = orderService.changeOrder(id, orderDTO);
        return ResponseEntity.ok().body(changedOrder);
    }

}
