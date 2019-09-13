package com.areumjo.todos.service;

import com.areumjo.todos.model.Todo;

import java.util.List;

public interface TodoService
{
    List<Todo> findAll();

    Todo findTodoById(long id);

    void delete(long id);

    Todo save(Todo todo);

    Todo update(Todo todo, long id);

}
