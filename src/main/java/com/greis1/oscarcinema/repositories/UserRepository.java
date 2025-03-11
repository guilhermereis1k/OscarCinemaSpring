package com.greis1.oscarcinema.repositories;

import com.greis1.oscarcinema.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import java.util.Optional;


@org.springframework.stereotype.Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByDocumentId(String documentId);
}
