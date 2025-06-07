package com.example.subscriptions.service.impl;

import com.example.subscriptions.enums.SubscriptionStatus;
import com.example.subscriptions.model.Subscription;
import com.example.subscriptions.model.User;
import com.example.subscriptions.repository.SubscriptionRepository;
import com.example.subscriptions.repository.UserRepository;
import com.example.subscriptions.service.SubscriptionService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class SubscriptionServiceImpl implements SubscriptionService{

    private final UserRepository userRepository;
    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionServiceImpl(UserRepository userRepository, SubscriptionRepository subscriptionRepository) {
        this.userRepository = userRepository;
        this.subscriptionRepository = subscriptionRepository;
    }


    @Override
    public Subscription createSubscription(long userId, Subscription subscription) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("Пользователь с таким id не существует"));
        if (subscription.getServiceName() == null) {
            throw new IllegalArgumentException("Название сервиса подписки должно быть заполнено");
        }
        subscription.setUser(user);
        subscription.setDataStart(LocalDateTime.now());
        subscription.setStatus(SubscriptionStatus.ACTIVE);
        return subscriptionRepository.save(subscription);
    }

    @Override
    public List<Subscription> getSubscription(long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("Пользователь с таким id не существует"));
        return user.getSubscriptions().stream()
                .filter(it->it.getStatus()== SubscriptionStatus.ACTIVE).toList();
    }

    @Override
    public void deleteSubscription(long userId, long subscriptionId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("Пользователь с таким id не существует"));
        Subscription subscription = subscriptionRepository.findById(subscriptionId)
                .orElseThrow(() -> new EntityNotFoundException("Подписка с таким id не найдена"));
        if (!subscription.getUser().equals(user)) {
            throw new IllegalArgumentException("Подписка не принадлежит пользователю");
        }
        subscription.setStatus(SubscriptionStatus.CANCELLED);
        subscription.setDataEnd(LocalDateTime.now());
        subscriptionRepository.save(subscription);
    }

    @Override
    public List<Subscription> getSubscriptionsTopThree() {
        PageRequest topThree = PageRequest.of(0, 3);
        List<String> serviceNames = subscriptionRepository
                .findTop3PopularServiceNames(topThree);
        List<Subscription> result = new ArrayList<>();
        for (String name : serviceNames) {
            Subscription sub = new Subscription();
            sub.setServiceName(name);
            result.add(sub);
        }
        return result;
    }
}
