package com.areumjo.todos.controller;

import com.areumjo.todos.model.Todo;
import com.areumjo.todos.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController
{
    @Autowired
    TodoService todoService;

    // localhost:8000/todos/todos
    @GetMapping(value = "/todos",
                produces = {"application/json"})
    public ResponseEntity<?> listAllTodos()
    {
        List<Todo> allTodos = todoService.findAll();
        return new ResponseEntity<>(allTodos, HttpStatus.OK);
    }

    // localhost:8000/todos/todos/{todoid}
    @GetMapping(value = "/todos/{todoId}",
            produces = {"application/json"})
    public ResponseEntity<?> getQuote(
            @PathVariable
                    Long todoId)
    {
        Todo myTodo = todoService.findTodoById(todoId);
        return new ResponseEntity<>(myTodo, HttpStatus.OK);
    }
}
