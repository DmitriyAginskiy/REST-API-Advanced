package com.epam.esm.controller;


import com.epam.esm.entity.User;
import com.epam.esm.service.OrderService;
import com.epam.esm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    /**
     * Init the tags controller class.
     *
     * @author Dzmitry Ahinski
     */
    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(produces = "application/json; charset=utf-8")
    public void buyCertificate(@RequestParam long userId, @RequestParam long certificateId) {
        orderService.buyCertificate(userId, certificateId);
    }

}
