package com.greis1.oscarcinema.services;

import com.greis1.oscarcinema.dtos.MovieUpdateDTO;
import com.greis1.oscarcinema.dtos.UserUpdateDTO;
import com.greis1.oscarcinema.entities.Movie;
import com.greis1.oscarcinema.entities.User;
import com.greis1.oscarcinema.repositories.UserRepository;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @Test
    void insertUser() {
        Long id = 1L;
        User user = new User(id, "John Doe", "123456789", null);

        when(userRepository.save(user)).thenReturn(user);

        User savedUser = userService.insertUser(user);

        assertNotNull(user);
        assertEquals(id, user.getId());
        assertEquals("John Doe", user.getName());
    }

    @Test
    void findUserById() {
        Long id = 1L;
        User user = new User(id, "John Doe", "123456789", null);

        when(userRepository.findById(id)).thenReturn(java.util.Optional.of(user));

        User foundUser = userService.findUserById(id);

        assertNotNull(foundUser);
        assertEquals(id, foundUser.getId());
        assertEquals("John Doe", foundUser.getName());
    }

    @Test
    void findUserByDocumentId() {
        String documentId = "123456789";
        User user = new User(1L, "John Doe", documentId, null);

        when(userRepository.findByDocumentId(documentId)).thenReturn(java.util.Optional.of(user));

        User foundUser = userService.findUserByDocumentId(documentId);

        assertNotNull(foundUser);
        assertEquals(documentId, foundUser.getDocumentId());
        assertEquals("John Doe", foundUser.getName());
    }

    @Test
    void removeUser() {
        Long id = 1L;
        User user = new User(id, "John Doe", "123456789", null);

        when(userRepository.save(user)).thenReturn(user);
        User savedUser = userService.insertUser(user);

        userService.removeUser(user.getId());

        when(userRepository.findById(user.getId())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> userService.findUserById(user.getId()));
    }

    @Test
    void changeUser() {
        Long id = 1L;
        User user = new User(id, "John Doe", "123456789", null);

        when(userRepository.save(user)).thenReturn(user);
        User savedUser = userService.insertUser(user);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        UserUpdateDTO userAltered = new UserUpdateDTO();
        userAltered.setName("Joao Dias");

        when(userRepository.save(user)).thenReturn(user);
        userService.changeUser(id, userAltered);

        assertNotNull(user);
        assertEquals("Joao Dias", user.getName());
        assertNotEquals("John Doe", user.getName());
    }
}