package com.areumjo.todos.controller;

import com.areumjo.todos.model.Todo;
import com.areumjo.todos.model.User;
import com.areumjo.todos.service.TodoService;
import com.areumjo.todos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController
{

    @Autowired
    private UserService userService;
    private TodoService todoService;

    //localhost:8000/users/users
    @GetMapping(value = "/users", produces = {"application/json"})
    public ResponseEntity<?> listAllUsers()
    {
        List<User> myUsers = userService.findAll();
        return new ResponseEntity<>(myUsers, HttpStatus.OK);
    }

    //localhost:8000/users/{username}
    @GetMapping(value = "/{username}", produces = {"application/json"})
    public ResponseEntity<?> getUser(@PathVariable String username)
    {
        User u = userService.findUserByName(username);
        return new ResponseEntity<>(u, HttpStatus.OK);
    }

    // POST - localhost:8000/users
    @PostMapping(value = "/user", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> addNewUser(@Valid @RequestBody User newuser) throws URISyntaxException
    {
        newuser =  userService.save(newuser);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUserURI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{userid}")
                .buildAndExpand(newuser.getUserid())
                .toUri();
        responseHeaders.setLocation(newUserURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    // PUT - localhost:8000/users/{userId}
    @PutMapping(value = "/user/{userId}")
    public ResponseEntity<?> updateUser(@RequestBody User updateUser, @PathVariable long userId)
    {
        userService.update(updateUser, userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // DELETE - localhost:8000/users/userid/{userId}
    //@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @DeleteMapping("/userid/{userId}")
    public ResponseEntity<?> deleteUserById(@PathVariable long userId)
    {
        userService.delete(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // POST - /users/todo/{userid} - add a todo to the assigned user
    @PostMapping(value = "/todo/{userid}",
                consumes = {"application/json"},
                produces = {"application/json"})
    public ResponseEntity<?> postNewTodo(@PathVariable long userid, @RequestBody Todo todo){
        todo.setUser(userService.findUserById(userid));
        return new ResponseEntity<>(todoService.save(todo), HttpStatus.OK);
    }


}
