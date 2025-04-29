package com.greis1.oscarcinema.controllers;

import com.greis1.oscarcinema.dtos.UserCreateDTO;
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
    public ResponseEntity<User> insertUser(@RequestBody UserCreateDTO userCreateDTO){
        User insertedUser = userService.insertUser(userCreateDTO);
        return ResponseEntity.ok().body(insertedUser);
    }

    @GetMapping("/search/id/{id}")
    public ResponseEntity<User> findUserById(@PathVariable Long id){
        User foundUser = userService.findUserById(id);
        return ResponseEntity.ok().body(foundUser);
    }

    @GetMapping("/search/documentId/{documentId}")
    public ResponseEntity<User> findUserByDocumentId(@PathVariable String documentId){
        User foundUser = userService.findUserByDocumentId(documentId);
        return ResponseEntity.ok().body(foundUser);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> removeUser(@PathVariable Long id) {
        userService.removeUser(id);
        return ResponseEntity.ok().body("Deleted successfully.");
    }

    @PatchMapping("/change/{id}")
    public ResponseEntity<User> changeUser(@PathVariable Long id, @RequestBody UserUpdateDTO userDTO){
        User changedUser = userService.changeUser(id, userDTO);
        return ResponseEntity.ok().body(changedUser);
    }
}
