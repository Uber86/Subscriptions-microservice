package com.example.subscriptions.service.impl;

import com.example.subscriptions.model.User;
import com.example.subscriptions.repository.UserRepository;
import com.example.subscriptions.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Сервис UserServiceImpl реализует интерфейс UserService с его методами
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

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
        if(user.getPhone() == null &&
                user.getEmail() == null &&
                user.getFirstName() == null &&
                user.getLastName() == null &&
                user.getBirthday() == null) {
            throw new IllegalArgumentException("Отсутствует поле");
        }
        return userRepository.save(user);
    }

    /**
     * Метод получение пользователя по id
     * @param userId уникальный идентификатор пользователя
     * @return возвращает пользователя если таков имеется
     */
    @Override
    public User getUser(long userId) {
        return userRepository.findById(userId).orElseThrow(
                ()-> new EntityNotFoundException("Пользователь не найден"));
    }

    /**
     * Метод обновления пользователя
     * @param userId находим пользователя по его id
     * @param user обновленные данные пользователя
     * @return возвращает при выполнении сохраненные данные пользователя
     */
    @Override
    public User updateUser(long userId, User user) {
        User existingUser = userRepository.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("Пользователь не найден"));
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
            throw new IllegalArgumentException("Данные отсутствуют");
        }

    }

    /**
     * Метод удаления пользователя
     * @param userId ищем пользователя по id если таков имеется и удаляем с БД
     */
    @Override
    public void deleteUser(long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("Пользователь с таким id не существует"));
        userRepository.deleteById(userId);
    }
}
