package com.areumjo.todos.repository;

import com.areumjo.todos.model.Todo;
import org.springframework.data.repository.CrudRepository;

public interface TodoRepository extends CrudRepository<Todo, Long>
{

}
