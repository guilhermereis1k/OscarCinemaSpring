package com.greis1.oscarcinema.services;

import com.greis1.oscarcinema.dtos.MovieUpdateDTO;
import com.greis1.oscarcinema.dtos.UserCreateDTO;
import com.greis1.oscarcinema.dtos.UserUpdateDTO;
import com.greis1.oscarcinema.entities.Movie;
import com.greis1.oscarcinema.entities.User;
import com.greis1.oscarcinema.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User insertUser(UserCreateDTO userCreateDTO){
        User user = new User(userCreateDTO);
        return userRepository.save(user);
    }

    public User findUserById(Long id){
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found."));
    }

    public User findUserByDocumentId(String documentId){
        return userRepository.findByDocumentId(documentId).orElseThrow(() -> new RuntimeException("User not found."));
    }

    public void removeUser(Long id) {
        userRepository.deleteById(id);
    }

    public User changeUser(Long id, UserUpdateDTO userDTO){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found."));

        if (userDTO.getName() != null) {
            user.setName(userDTO.getName());
        }
        if (userDTO.getDocumentId() != null) {
            user.setDocumentId(userDTO.getDocumentId());
        }
        return userRepository.save(user);
    }
}
