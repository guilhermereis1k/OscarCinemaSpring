package com.greis1.oscarcinema.services;

import com.greis1.oscarcinema.dtos.OrderUpdateDTO;
import com.greis1.oscarcinema.entities.Movie;
import com.greis1.oscarcinema.entities.Order;
import com.greis1.oscarcinema.entities.User;
import com.greis1.oscarcinema.repositories.MovieRepository;
import com.greis1.oscarcinema.repositories.OrderRepository;
import com.greis1.oscarcinema.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    OrderRepository orderRepository;

    @Mock
    UserService userService;

    @Mock
    MovieService movieService;

    @InjectMocks
    OrderService orderService;


    @Test
    void insertOrder() {
        Long idMovie = 1L;
        Movie mockedMovie = new Movie(idMovie, "Anora", "https://upload.wikimedia.org/wikipedia/pt/thumb/8/86/Anora_%28filme%29.jpg/250px-Anora_%28filme%29.jpg", "Anora, uma jovem stripper do Brooklyn, conhece o filho de um oligarca russo na boate em que trabalha.", 18);
        String documentIdUser = "123456789";
        User mockedUser = new User(1L, "John Doe", documentIdUser, null);

        List<String> seats = Arrays.asList("A1", "A2");

        Order expectedOrder = new Order(mockedMovie, mockedUser, "S892098", 1, "IMAX", false, seats);

        when(orderRepository.save(any(Order.class))).thenReturn(expectedOrder);

        Order savedOrder = orderService.insertOrder(documentIdUser, idMovie, "S892098", 1, "IMAX", false, seats);

        assertNotNull(expectedOrder);
        assertEquals(expectedOrder, savedOrder);
        assertEquals(mockedUser, savedOrder.getUser());
        assertEquals(mockedMovie, savedOrder.getMovie());

        verify(orderRepository, times(1)).save(any(Order.class));
    }

    @Test
    void findOrderById() {
        Long idMovie = 1L;
        Movie mockedMovie = new Movie(idMovie, "Anora", "https://upload.wikimedia.org/wikipedia/pt/thumb/8/86/Anora_%28filme%29.jpg/250px-Anora_%28filme%29.jpg", "Anora, uma jovem stripper do Brooklyn, conhece o filho de um oligarca russo na boate em que trabalha.", 18);
        String documentIdUser = "123456789";
        User mockedUser = new User(1L, "John Doe", documentIdUser, null);

        List<String> seats = Arrays.asList("A1", "A2");
        Long orderId = 1L;
        Order expectedOrder = new Order(orderId, mockedMovie, mockedUser, "S892098", 1, "IMAX", false, seats);

        when(orderRepository.save(any(Order.class))).thenReturn(expectedOrder);
        orderService.insertOrder(documentIdUser, idMovie, "S892098", 1, "IMAX", false, seats);

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(expectedOrder));
        Order foundOrder = orderService.findOrderById(orderId);

        assertNotNull(foundOrder);
        assertEquals(expectedOrder, foundOrder);
        assertEquals(mockedUser, foundOrder.getUser());
        assertEquals(mockedMovie, foundOrder.getMovie());

        verify(orderRepository, times(1)).save(any(Order.class));
        verify(orderRepository, times(1)).findById(orderId);
    }

    @Test
    void removeOrderById() {
        Long idMovie = 1L;
        Movie mockedMovie = new Movie(idMovie, "Anora", "https://upload.wikimedia.org/wikipedia/pt/thumb/8/86/Anora_%28filme%29.jpg/250px-Anora_%28filme%29.jpg", "Anora, uma jovem stripper do Brooklyn, conhece o filho de um oligarca russo na boate em que trabalha.", 18);
        String documentIdUser = "123456789";
        User mockedUser = new User(1L, "John Doe", documentIdUser, null);

        List<String> seats = Arrays.asList("A1", "A2");
        Long orderId = 1L;
        Order expectedOrder = new Order(orderId, mockedMovie, mockedUser, "S892098", 1, "IMAX", false, seats);

        when(orderRepository.save(any(Order.class))).thenReturn(expectedOrder);
        orderService.insertOrder(documentIdUser, idMovie, "S892098", 1, "IMAX", false, seats);

        orderService.removeOrderById(orderId);

        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,() -> orderService.findOrderById(orderId));

        verify(orderRepository, times(1)).save(any(Order.class));
        verify(orderRepository, times(1)).deleteById(orderId);
        verify(orderRepository, times(1)).findById(orderId);
    }

    @Test
    void changeOrder() {
        Long orderId = 1L;
        Long movieId = 1L;
        String documentIdUser = "123456789";

        Movie mockedMovie = new Movie(movieId, "Anora", "https://upload.wikimedia.org/wikipedia/pt/thumb/8/86/Anora_%28filme%29.jpg/250px-Anora_%28filme%29.jpg", "Anora, uma jovem stripper do Brooklyn, conhece o filho de um oligarca russo na boate em que trabalha.", 18);
        User mockedUser = new User(1L, "John Doe", documentIdUser, null);
        List<String> originalSeats = Arrays.asList("A1", "A2");
        Order existingOrder = new Order(orderId, mockedMovie, mockedUser, "S892098", 1, "IMAX", false, originalSeats);

        OrderUpdateDTO orderUpdateDTO = new OrderUpdateDTO();
        orderUpdateDTO.setSession("S123456");
        orderUpdateDTO.setRoomNumber(2);
        orderUpdateDTO.setProjectorType("3D");
        orderUpdateDTO.setIsItDubbed(true);
        orderUpdateDTO.setSeats(Arrays.asList("B1", "B2"));
        orderUpdateDTO.setTotalPaid(30.00);

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(existingOrder));
        when(orderRepository.save(any(Order.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Order updatedOrder = orderService.changeOrder(orderId, orderUpdateDTO);

        assertNotNull(updatedOrder);
        assertEquals(orderUpdateDTO.getSession(), updatedOrder.getSession());
        assertEquals(orderUpdateDTO.getRoomNumber(), updatedOrder.getRoomNumber());
        assertEquals(orderUpdateDTO.getProjectorType(), updatedOrder.getProjectorType());
        assertEquals(orderUpdateDTO.getIsItDubbed(), updatedOrder.isItDubbed());
        assertEquals(orderUpdateDTO.getSeats(), updatedOrder.getSeats());
        assertEquals(orderUpdateDTO.getTotalPaid(), updatedOrder.getTotalPaid());

        verify(orderRepository, times(1)).findById(orderId);
        verify(orderRepository, times(1)).save(existingOrder);
    }
}