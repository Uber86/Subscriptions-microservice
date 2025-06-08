package com.example.subscriptions.controller;

import com.example.subscriptions.dto.SubscriptionDto;
import com.example.subscriptions.model.Subscription;
import com.example.subscriptions.service.SubscriptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Контроллер для получения ТОП-3 популярных подписок.
 */
@RestController
@RequestMapping("/subscriptions")
public class SubscriptionTopThreeController {

    private final SubscriptionService service;

    public SubscriptionTopThreeController(SubscriptionService service) {
        this.service = service;
    }

    /**
     *Метод получения топ-3 подписок по пользователям
     */
    @GetMapping("/top")
    public ResponseEntity<List<SubscriptionDto>> getTopSubscriptions() {
        return ResponseEntity.ok(service.getSubscriptionsTopThree());
    }
}
