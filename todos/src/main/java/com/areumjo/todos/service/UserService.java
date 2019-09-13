package com.areumjo.todos.service;

import com.areumjo.todos.model.User;

import java.util.List;

public interface UserService
{
    List<User> findAll();

    User findUserByName(String name);

    User findUserById(long id);

    void delete(long id);

    User save(User user);

    User update(User user, long id);
}
