package com.greis1.oscarcinema.repositories;

import com.greis1.oscarcinema.entities.Movie;
import com.greis1.oscarcinema.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

@org.springframework.stereotype.Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
