package com.example.subscriptions.service;

import com.example.subscriptions.model.Subscription;

import java.util.List;

public interface SubscriptionService {

    Subscription createSubscription(long userId, Subscription subscription);

    List<Subscription> getSubscription(long userId);

    void deleteSubscription(long userId, long subscriptionId);

    List<Subscription> getSubscriptionsTopThree();

}
