package com.example.subscriptions.service.impl;

import com.example.subscriptions.enums.SubscriptionStatus;
import com.example.subscriptions.model.Subscription;
import com.example.subscriptions.model.User;
import com.example.subscriptions.repository.SubscriptionRepository;
import com.example.subscriptions.repository.UserRepository;
import com.example.subscriptions.service.SubscriptionService;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class SubscriptionServiceImpl implements SubscriptionService{

    private final UserRepository userRepository;
    private final SubscriptionRepository subscriptionRepository;
    private static final Logger log = LoggerFactory.getLogger(SubscriptionServiceImpl.class);

    public SubscriptionServiceImpl(UserRepository userRepository, SubscriptionRepository subscriptionRepository) {
        this.userRepository = userRepository;
        this.subscriptionRepository = subscriptionRepository;
    }


    /**
     * Метод создания подписки пользователю
     * @param userId id пользователя
     * @param subscription id подписки
     * Проверяет id пользователя и подписки в БД
     * @return привязывает подписку пользователю и помечает как Active + с датой
     */
    @Override
    public Subscription createSubscription(long userId, Subscription subscription) {
        log.info("Создание подписки для пользователя с id {}", userId);
        User user = userRepository.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("Пользователь с таким id не существует"));
        if (subscription.getServiceName() == null) {
            throw new IllegalArgumentException("Название сервиса подписки должно быть заполнено");
        }
        subscription.setUser(user);
        subscription.setDataStart(LocalDateTime.now());
        subscription.setStatus(SubscriptionStatus.ACTIVE);
        log.info("Подписка создана: {}", subscription.getServiceName());
        return subscriptionRepository.save(subscription);
    }

    /**
     * Метод получение всех подписок пользователя
     * @param userId id пользователя
     * @return при нахождении пользователя по id выводит его подписки
     */
    @Override
    public List<Subscription> getSubscriptions(long userId) {
        log.info("Получение подписок для пользователя с id {}", userId);
        return userRepository.findById(userId)
                .map(user -> user.getSubscriptions().stream()
                        .filter(s -> s.getStatus() == SubscriptionStatus.ACTIVE)
                        .toList())
                .orElseThrow(() -> {
                    log.error("Пользователь с id {} не найден", userId);
                    return new EntityNotFoundException("Пользователь с таким id не существует");
                });
    }

    /**
     * Метод для удаления подписки, если быть точнее для деактивации подписки
     * @param userId принимает id пользователя, проверяет есть ли пользователь в БД
     * @param subscriptionId принимает id подписки, проверяет существует ли подписка
     * При успешном нахождении деактивирует подписку + с датой
     */
    @Override
    public void deleteSubscription(long userId, long subscriptionId) {
        log.info("Деактивация подписки с id {} для пользователя с id {}", subscriptionId, userId);
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
        log.info("Подписка с id {} деактивирована", subscriptionId);
    }

    /**
     * Метод для получения топ-3х подписок по кол-ву пользователей
     * @return возвращает лист из 3х подписок
     */
    @Override
    public List<Subscription> getSubscriptionsTopThree() {
        log.info("Получение топ-3 популярных подписок");
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
