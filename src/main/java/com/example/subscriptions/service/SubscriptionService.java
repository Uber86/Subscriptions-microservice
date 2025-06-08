package com.example.subscriptions.service;

import com.example.subscriptions.dto.SubscriptionCreateDto;
import com.example.subscriptions.dto.SubscriptionDto;
import com.example.subscriptions.model.Subscription;

import java.util.List;

public interface SubscriptionService {

    SubscriptionDto createSubscription(long userId, SubscriptionCreateDto dto);

    List<SubscriptionDto> getSubscriptions(long userId);

    void deleteSubscription(long userId, long subscriptionId);

    List<SubscriptionDto> getSubscriptionsTopThree();

}
