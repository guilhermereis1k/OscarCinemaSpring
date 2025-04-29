package com.greis1.oscarcinema.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String imageUrl;
    private String description;
    private Integer minimumAge;

    @JsonIgnore
    @OneToMany(mappedBy = "movie")
    private List<Session> sessions = new ArrayList<>();

    public Movie(String name, String imageUrl, String description, Integer minimumAge) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.description = description;
        this.minimumAge = minimumAge;
        this.sessions = new ArrayList<>();
    }
    public Movie(Long id, String name, String imageUrl, String description, Integer minimumAge) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.description = description;
        this.minimumAge = minimumAge;
        this.sessions = new ArrayList<>();
    }
}
