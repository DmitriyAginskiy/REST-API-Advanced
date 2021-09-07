package com.epam.esm.util;

import com.epam.esm.controller.GiftCertificateController;
import com.epam.esm.controller.OrderController;
import com.epam.esm.controller.TagController;
import com.epam.esm.controller.UserController;
import com.epam.esm.dto.GiftCertificateDto;
import com.epam.esm.dto.OrderDto;
import com.epam.esm.dto.TagDto;
import com.epam.esm.dto.UserDto;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Order;
import com.epam.esm.entity.Tag;
import com.epam.esm.entity.User;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

/**
 * Hateoas wrapper class used for wrapping entities.
 *
 * @author Dzmitry Ahinski
 */
@Component
public class HateoasWrapper {

    public void orderWrap(OrderDto orderDto) {
        orderDto.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(OrderController.class)
                .findOrderById(orderDto.getId())).withSelfRel());
        orderDto.getCertificateDto().add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(GiftCertificateController.class)
                .findGiftCertificateById(orderDto.getCertificateDto().getId())).withSelfRel());
        if(orderDto.getUserDto().getLinks().isEmpty()) {
            orderDto.getUserDto().add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class)
                    .findUserById(orderDto.getUserDto().getId())).withSelfRel());
        }
        orderDto.getCertificateDto().getTags().forEach(tag -> {
            if(tag.getLinks().isEmpty()) {
                tag.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
                        .methodOn(TagController.class).findTagById(tag.getId())).withSelfRel());
            }
        });
    }

    public void certificateWrap(GiftCertificateDto giftCertificateDto) {
        giftCertificateDto.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(GiftCertificateController.class)
                .findGiftCertificateById(giftCertificateDto.getId())).withSelfRel());
        giftCertificateDto.getTags().forEach(tagDto -> {
            if(tagDto.getLinks().isEmpty()) {
                tagDto.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
                        .methodOn(TagController.class).findTagById(tagDto.getId())).withSelfRel());
            }
        });
    }

    public void tagWrap(TagDto tagDto) {
        tagDto.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TagController.class)
                .findTagById(tagDto.getId())).withSelfRel());
    }

    public void userWrap(UserDto userDto) {
        userDto.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class)
                .findUserById(userDto.getId())).withSelfRel());
    }
}
