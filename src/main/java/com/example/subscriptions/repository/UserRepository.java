package com.example.subscriptions.repository;

import com.example.subscriptions.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Класс UserRepository репозитории для работы с сущностью User
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
