package com.epam.esm.controller;


import com.epam.esm.entity.Tag;
import com.epam.esm.entity.User;
import com.epam.esm.service.TagService;
import com.epam.esm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Users controller class.
 *
 * @author Dzmitry Ahinski
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    /**
     * Init the tags controller class.
     *
     * @author Dzmitry Ahinski
     */
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    /**
     * Finds user by id
     *
     * @param id the id of user to be found.
     * @return found user object.
     */
    @GetMapping(value = "/{id}", produces = "application/json; charset=utf-8")
    public User findUserById(@PathVariable long id) {
        User user = userService.findById(id);
        user.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class)
                .findUserById(user.getId())).withSelfRel());
        return user;
    }

    /**
     * Finds all users.
     *
     * @return list with found users.
     */
    @GetMapping(produces = "application/json; charset=utf-8")
    public List<User> findAll(@RequestParam(defaultValue = "0") int page,
                             @RequestParam(defaultValue = "10") int size) {
        List<User> users = userService.findAll(page, size);
        users.forEach(user -> user.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class)
                .findUserById(user.getId())).withSelfRel()));
        return users;
    }

}
