package com.greis1.oscarcinema.repositories;

import com.greis1.oscarcinema.entities.Session;
import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
}
