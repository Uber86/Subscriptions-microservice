package com.example.subscriptions.service.impl;

import com.example.subscriptions.model.User;
import com.example.subscriptions.repository.UserRepository;
import com.example.subscriptions.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Сервис UserServiceImpl реализует интерфейс UserService с его методами
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Метод добавления пользователя
     * @param user принимает данные
     * @return сохраняет пользователя при выполнении условии
     */
    @Override
    public User createUser(User user) {
        log.info("Создание пользователя с email {}", user.getEmail());
        if(user.getPhone() == null &&
                user.getEmail() == null &&
                user.getFirstName() == null &&
                user.getLastName() == null &&
                user.getBirthday() == null) {
            log.warn("Попытка создать пользователя с пустыми обязательными полями");
            throw new IllegalArgumentException("Отсутствует поле");
        }
        User savedUser = userRepository.save(user);
        log.info("Пользователь с id {} успешно создан", savedUser.getId());
        return savedUser;
    }

    /**
     * Метод получение пользователя по id
     * @param userId уникальный идентификатор пользователя
     * @return возвращает пользователя если таков имеется
     */
    @Override
    public User getUser(long userId) {
        log.info("Запрос на получение пользователя с id: {}", userId);
        return userRepository.findById(userId).orElseThrow(
                ()-> {
                    log.error("Пользователь с id {} не найден", userId);
                    return new EntityNotFoundException("Пользователь не найден");
                });
    }

    /**
     * Метод обновления пользователя
     * @param userId находим пользователя по его id
     * @param user обновленные данные пользователя
     * @return возвращает при выполнении сохраненные данные пользователя
     */
    @Override
    public User updateUser(long userId, User user) {
        log.info("Запрос на обновление пользователя с id: {}", userId);
        User existingUser = userRepository.findById(userId).orElseThrow(
                () -> {
                    log.error("Пользователь с id {} не найден для обновления", userId);
                    return new EntityNotFoundException("Пользователь не найден");
                });
        if (user != null) {

            if(user.getEmail() != null &&
                    user.getPhone()!= null &&
                    user.getFirstName() != null &&
                    user.getLastName() != null) {
                existingUser.setEmail(user.getEmail());
                existingUser.setBirthday(user.getBirthday());
                existingUser.setFirstName(user.getFirstName());
                existingUser.setLastName(user.getLastName());
                existingUser.setPhone(user.getPhone());
                existingUser.setInfo(user.getInfo());
            }

            return userRepository.save(existingUser);
        }else{
            log.warn("Переданы пустые данные для обновления пользователя с id {}", userId);
            throw new IllegalArgumentException("Данные отсутствуют");
        }

    }

    /**
     * Метод удаления пользователя
     * @param userId ищем пользователя по id если таков имеется и удаляем с БД
     */
    @Override
    public void deleteUser(long userId) {
        log.info("Запрос на удаление пользователя с id: {}", userId);
        User user = userRepository.findById(userId).orElseThrow(() -> {
            log.error("Пользователь с id {} не найден для удаления", userId);
            return new EntityNotFoundException("Пользователь с таким id не существует");
        });
        userRepository.deleteById(userId);
        log.info("Пользователь с id {} успешно удален", userId);
    }
}
