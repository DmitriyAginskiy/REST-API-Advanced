package com.epam.esm.controller;


import com.epam.esm.entity.Order;
import com.epam.esm.entity.Tag;
import com.epam.esm.entity.User;
import com.epam.esm.service.OrderService;
import com.epam.esm.service.UserService;
import com.epam.esm.util.HateoasWrapper;
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
    private final HateoasWrapper wrapper;

    /**
     * Init the order controller class.
     *
     * @author Dzmitry Ahinski
     */
    @Autowired
    public OrderController(OrderService orderService, HateoasWrapper wrapper) {
        this.orderService = orderService;
        this.wrapper = wrapper;
    }

    /**
     * Connects the certificate with the user.
     *
     * @param userId the id of the user to be connected.
     * @param certificateId the if of the certificate to be connected.
     */
    @PostMapping(produces = "application/json; charset=utf-8")
    public Order buyCertificate(@RequestParam long userId, @RequestParam long certificateId) {
        Order order = orderService.buyCertificate(userId, certificateId);
        wrapper.orderWrap(order);
        return order;
    }

    /**
     * Finds order by id
     *
     * @param id the id of order to be found.
     * @return found order object.
     */
    @GetMapping(value = "/{id}", produces = "application/json; charset=utf-8")
    public Order findOrderById(@PathVariable long id) {
        Order order = orderService.findById(id);
        wrapper.orderWrap(order);
        return order;
    }

    /**
     * Finds order by user id.
     *
     * @param userId the id of the user.
     * @return List of found orders.
     */
    @GetMapping(produces = "application/json; charset=utf-8", params = "userId")
    public List<Order> findAllByUserId(@RequestParam long userId) {
        List<Order> orders = orderService.findAllByUserId(userId);
        orders.forEach(wrapper::orderWrap);
        return orders;
    }

    /**
     * Finds order by user id and certificate id.
     *
     * @param userId the id of the user.
     * @param certificateId the id of the certificate.
     * @return found order.
     */
    @GetMapping(produces = "application/json; charset=utf-8", params = { "userId", "certificateId" })
    public Order findAllByUserAndCertificate(@RequestParam long userId, @RequestParam long certificateId) {
        Order order = orderService.findByUserAndCertificate(userId, certificateId);
        wrapper.orderWrap(order);
        return order;
    }
}
