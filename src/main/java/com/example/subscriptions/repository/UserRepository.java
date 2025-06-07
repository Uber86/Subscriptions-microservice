package com.example.subscriptions.repository;

import com.example.subscriptions.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
