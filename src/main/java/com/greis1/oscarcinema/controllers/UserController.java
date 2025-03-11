package com.greis1.oscarcinema.controllers;

import com.greis1.oscarcinema.dtos.UserUpdateDTO;
import com.greis1.oscarcinema.entities.User;
import com.greis1.oscarcinema.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/create")
    public ResponseEntity<User> insertUser(User user){
        User insertedUser = userService.insertUser(user);
        return ResponseEntity.ok().body(insertedUser);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<User> findUserById(Long id){
        User foundUser = userService.findUserById(id);
        return ResponseEntity.ok().body(foundUser);
    }

    @GetMapping("/documentId/{documentId}")
    public ResponseEntity<User> findUserByDocumentId(String documentId){
        User foundUser = userService.findUserByDocumentId(documentId);
        return ResponseEntity.ok().body(foundUser);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> removeUser(Long id) {
        userService.removeUser(id);
        return ResponseEntity.ok().body("Deleted successfully.");
    }

    @PatchMapping("/change/")
    public ResponseEntity<User> changeUser(Long id, UserUpdateDTO userDTO){
        User changedUser = userService.changeUser(id, userDTO);
        return ResponseEntity.ok().body(changedUser);
    }
}
