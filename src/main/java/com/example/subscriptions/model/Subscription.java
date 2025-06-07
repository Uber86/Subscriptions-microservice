package com.example.subscriptions.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

/**
 * Класс Subscription представляет подписки для пользователя
 */
@Entity
@Table(name = "subscriptions")
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="service_name")
    private String serviceName;

    @Column(name = "data_start")
    private LocalDateTime dataStart;

    @Column(name = "data_end")
    private LocalDateTime dataEnd;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
