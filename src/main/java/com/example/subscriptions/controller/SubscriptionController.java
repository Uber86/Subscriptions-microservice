package com.example.subscriptions.controller;

import com.example.subscriptions.dto.SubscriptionCreateDto;
import com.example.subscriptions.dto.SubscriptionDto;
import com.example.subscriptions.model.Subscription;
import com.example.subscriptions.service.SubscriptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/{userId}/subscriptions")
public class SubscriptionController {

    private final SubscriptionService service;

    public SubscriptionController(SubscriptionService service) {
        this.service = service;
    }

    /**
     *Метод добавления подписок пользователю
     */
    @PostMapping
    public ResponseEntity<SubscriptionDto> createSubscription(@PathVariable long userId,
                                                           @RequestBody SubscriptionCreateDto dto) {
        return ResponseEntity.ok(service.createSubscription(userId, dto));
    }

    /**
     *Метод получения подписок пользователя
     */
    @GetMapping
    public ResponseEntity<List<SubscriptionDto>> getSubscriptions(@PathVariable long userId) {
        return ResponseEntity.ok(service.getSubscriptions(userId));
    }

    /**
     *Метод деактивации подписки пользователя
     */
    @DeleteMapping("/{sub_id}")
    public void deleteSubscription(@PathVariable long userId, @PathVariable long sub_id) {
        service.deleteSubscription(userId, sub_id);
    }
}
