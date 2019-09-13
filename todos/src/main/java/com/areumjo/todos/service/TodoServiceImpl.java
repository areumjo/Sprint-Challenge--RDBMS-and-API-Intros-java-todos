package com.areumjo.todos.service;

import com.areumjo.todos.model.Todo;
import com.areumjo.todos.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "todoService")
public class TodoServiceImpl implements TodoService
{
    @Autowired
    private TodoRepository todorepos;

    @Override
    public List<Todo> findAll() {
        List<Todo> myTodo = new ArrayList<>();
        todorepos.findAll().iterator().forEachRemaining(myTodo::add);
        return myTodo;
    }

    @Override
    public Todo findTodoById(long id) {
        return todorepos.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
    }

    @Override
    public void delete(long id) {
        if (todorepos.findById(id).isPresent())
        {
            todorepos.deleteById(id);
        } else
        {
            throw new EntityNotFoundException(Long.toString(id));
        }
    }

    @Override
    public Todo save(Todo todo) {
        return todorepos.save(todo);
    }

    @Override
    public Todo update(Todo todo, long id) {
        Todo newTodo = todorepos.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));

        if (todo.getDatestarted() != null)
        {
            newTodo.setDatestarted(todo.getDatestarted());
        }

        if (todo.getDescription() != null)
        {
            newTodo.setDescription(todo.getDescription());
        }

        if (todo.getUser() != null)
        {
            newTodo.setUser((todo.getUser()));
        }

        if (todo.isCompleted())
        {
            newTodo.setCompleted(true);
        } else {
            newTodo.setCompleted(false);
        }

        return todorepos.save(newTodo);
    }
}
