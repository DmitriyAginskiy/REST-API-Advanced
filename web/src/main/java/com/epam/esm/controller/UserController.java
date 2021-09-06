package com.epam.esm.controller;


import com.epam.esm.entity.User;
import com.epam.esm.service.api.UserService;
import com.epam.esm.util.HateoasWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    private final HateoasWrapper wrapper;

    /**
     * Init the user controller class.
     *
     * @author Dzmitry Ahinski
     */
    @Autowired
    public UserController(UserService userService, HateoasWrapper wrapper) {
        this.userService = userService;
        this.wrapper = wrapper;
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
        wrapper.userWrap(user);
        return user;
    }

    /**
     * Finds all users.
     *
     * @return list with found users.
     * @param page pagination current page.
     * @param size pagination current page size.
     */
    @GetMapping(produces = "application/json; charset=utf-8")
    public List<User> findAll(@RequestParam(defaultValue = "0") int page,
                             @RequestParam(defaultValue = "10") int size) {
        List<User> users = userService.findAll(page, size);
        users.forEach(wrapper::userWrap);
        return users;
    }
}
