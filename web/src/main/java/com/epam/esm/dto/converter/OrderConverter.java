package com.epam.esm.dto.converter;

import com.epam.esm.dto.OrderDto;
import com.epam.esm.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderConverter {

    private final UserConverter userConverter;
    private final GiftCertificateConverter certificateConverter;

    @Autowired
    public OrderConverter(UserConverter userConverter, GiftCertificateConverter certificateConverter) {
        this.userConverter = userConverter;
        this.certificateConverter = certificateConverter;
    }

    public OrderDto convertToDto(Order order) {
        return new OrderDto(
                order.getId(),
                order.getPurchasePrice(),
                order.getPurchaseTime(),
                userConverter.convertToDto(order.getUser()),
                certificateConverter.convertToDto(order.getCertificate())
        );
    }
}
