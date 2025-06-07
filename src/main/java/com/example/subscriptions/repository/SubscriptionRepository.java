package com.example.subscriptions.repository;

import com.example.subscriptions.model.Subscription;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    @Query("""
            SELECT s.serviceName
            FROM Subscription s
            WHERE s.status = 'ACTIVE'
            GROUP BY s.serviceName
            ORDER BY COUNT(s) DESC
            """)
    List<String> findTop3PopularServiceNames(Pageable pageable);
}
