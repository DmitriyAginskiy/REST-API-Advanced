package com.epam.esm.controller;

import com.epam.esm.dto.OrderDto;
import com.epam.esm.dto.converter.OrderConverter;
import com.epam.esm.service.api.OrderService;
import com.epam.esm.util.HateoasWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
    private final OrderConverter orderConverter;
    private final HateoasWrapper wrapper;

    /**
     * Init the order controller class.
     *
     * @author Dzmitry Ahinski
     */
    @Autowired
    public OrderController(OrderService orderService, OrderConverter orderConverter, HateoasWrapper wrapper) {
        this.orderService = orderService;
        this.orderConverter = orderConverter;
        this.wrapper = wrapper;
    }

    /**
     * Connects the certificate with the user.
     *
     * @param userId the id of the user to be connected.
     * @param certificateId the if of the certificate to be connected.
     */
    @PostMapping(produces = "application/json; charset=utf-8")
    public OrderDto createOrder(@RequestParam long userId, @RequestParam long certificateId) {
        OrderDto orderDto = orderConverter.convertToDto(orderService.createOrder(userId, certificateId));
        wrapper.orderWrap(orderDto);
        return orderDto;
    }

    /**
     * Finds order by id
     *
     * @param id the id of order to be found.
     * @return found order object.
     */
    @GetMapping(value = "/{id}", produces = "application/json; charset=utf-8")
    public OrderDto findOrderById(@PathVariable long id) {
        OrderDto orderDto = orderConverter.convertToDto(orderService.findById(id));
        wrapper.orderWrap(orderDto);
        return orderDto;
    }

    /**
     * Finds order by user id.
     *
     * @param userId the id of the user.
     * @return List of found orders.
     */
    @GetMapping(produces = "application/json; charset=utf-8", params = "userId")
    public List<OrderDto> findAllByUserId(@RequestParam long userId,
                                       @RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "10") int size) {
        List<OrderDto> orderDtoList = new ArrayList<>();
        orderService.findAllByUserId(userId, page, size).forEach(order -> orderDtoList.add(orderConverter.convertToDto(order)));
        orderDtoList.forEach(wrapper::orderWrap);
        return orderDtoList;
    }

    /**
     * Finds order by user id and certificate id.
     *
     * @param userId the id of the user.
     * @param certificateId the id of the certificate.
     * @return found order.
     */
    @GetMapping(produces = "application/json; charset=utf-8", params = { "userId", "certificateId" })
    public OrderDto findAllByUserAndCertificate(@RequestParam long userId, @RequestParam long certificateId) {
        OrderDto orderDto = orderConverter.convertToDto(orderService.findByUserAndCertificate(userId, certificateId));
        wrapper.orderWrap(orderDto);
        return orderDto;
    }
}
