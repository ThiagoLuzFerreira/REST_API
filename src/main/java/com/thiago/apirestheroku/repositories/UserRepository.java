package com.thiago.apirestheroku.repositories;

import com.thiago.apirestheroku.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
