package com.example.subscriptions.service;

import com.example.subscriptions.model.User;

public interface UserService {

    User createUser(User user);

    User getUser(long id);

    User updateUser(long id, User user);

    void deleteUser(long id);
}
