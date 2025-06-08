package com.example.subscriptions.service.impl;

import com.example.subscriptions.dto.UserCreateDto;
import com.example.subscriptions.dto.UserDto;
import com.example.subscriptions.dto.UserUpdateDto;
import com.example.subscriptions.mapper.UserMapper;
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

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    /**
     * Метод добавления пользователя
     * @param dto принимает данные
     * @return сохраняет пользователя при выполнении условии
     */
    @Override
    public UserDto createUser(UserCreateDto dto) {
        log.info("Создание пользователя с email {}", dto.getEmail());
        User user = userMapper.fromCreateDto(dto);
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
        return userMapper.toDto(savedUser);
    }

    /**
     * Метод получение пользователя по id
     * @param userId уникальный идентификатор пользователя
     * @return возвращает пользователя если таков имеется
     */
    @Override
    public UserDto getUser(long userId) {
        log.info("Запрос на получение пользователя с id: {}", userId);
        User user = userRepository.findById(userId).orElseThrow(
                ()-> {
                    log.error("Пользователь с id {} не найден", userId);
                    return new EntityNotFoundException("Пользователь не найден");
                });
        return userMapper.toDto(user);
    }

    /**
     * Метод обновления пользователя
     * @param userId находим пользователя по его id
     * @param dto обновленные данные пользователя
     * @return возвращает при выполнении сохраненные данные пользователя
     */
    @Override
    public UserDto updateUser(long userId, UserUpdateDto dto) {
        log.info("Запрос на обновление пользователя с id: {}", userId);
        User existingUser = userRepository.findById(userId).orElseThrow(
                () -> {
                    log.error("Пользователь с id {} не найден для обновления", userId);
                    return new EntityNotFoundException("Пользователь не найден");
                });
        if (dto == null) {
            log.warn("Пустые данные для обновления пользователя с id {}", userId);
            throw new IllegalArgumentException("Данные отсутствуют");
        }
        userMapper.updateFromDto(dto, existingUser);
        User updatedUser = userRepository.save(existingUser);
        log.info("Пользователь с id {} успешно обновлен", userId);
        return userMapper.toDto(updatedUser);
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
