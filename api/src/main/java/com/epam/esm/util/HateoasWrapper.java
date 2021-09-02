package com.epam.esm.util;

import com.epam.esm.controller.GiftCertificateController;
import com.epam.esm.controller.OrderController;
import com.epam.esm.controller.TagController;
import com.epam.esm.controller.UserController;
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

    public void orderWrap(Order order) {
        order.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(OrderController.class)
                .findOrderById(order.getId())).withSelfRel());
        order.getCertificate().add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(GiftCertificateController.class)
                .findGiftCertificateById(order.getCertificate().getId())).withSelfRel());
        if(order.getUser().getLinks().isEmpty()) {
            order.getUser().add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class)
                    .findUserById(order.getUser().getId())).withSelfRel());
        }
        order.getCertificate().getTags().forEach(tag -> {
            if(tag.getLinks().isEmpty()) {
                tag.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
                        .methodOn(TagController.class).findTagById(tag.getId())).withSelfRel());
            }
        });
    }

    public void certificateWrap(GiftCertificate giftCertificate) {
        giftCertificate.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(GiftCertificateController.class)
                .findGiftCertificateById(giftCertificate.getId())).withSelfRel());
        giftCertificate.getTags().forEach(tag -> {
            if(tag.getLinks().isEmpty()) {
                tag.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
                        .methodOn(TagController.class).findTagById(tag.getId())).withSelfRel());
            }
        });
    }

    public void tagWrap(Tag tag) {
        tag.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TagController.class)
                .findTagById(tag.getId())).withSelfRel());
    }

    public void userWrap(User user) {
        user.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class)
                .findUserById(user.getId())).withSelfRel());
    }
}
