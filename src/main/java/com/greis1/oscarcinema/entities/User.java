package com.greis1.oscarcinema.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.greis1.oscarcinema.dtos.UserCreateDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true, nullable = false)
    private String documentId;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Order> orders = new ArrayList<>();

    public User(UserCreateDTO userCreateDTO) {
        this.name = userCreateDTO.getName();
        this.documentId = userCreateDTO.getDocumentId();
    }
}
