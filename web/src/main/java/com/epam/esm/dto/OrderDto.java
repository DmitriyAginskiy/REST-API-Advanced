package com.epam.esm.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Dto of an order.
 *
 * @author Dzmitry Ahinski
 */
public class OrderDto extends RepresentationModel<OrderDto> {

    private long id;
    private BigDecimal purchasePrice;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime purchaseTime;

    UserDto userDto;
    GiftCertificateDto certificateDto;

    public OrderDto() {

    }

    public OrderDto(long id, BigDecimal purchasePrice, LocalDateTime purchaseTime, UserDto userDto, GiftCertificateDto certificateDto) {
        this.id = id;
        this.purchasePrice = purchasePrice;
        this.purchaseTime = purchaseTime;
        this.userDto = userDto;
        this.certificateDto = certificateDto;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public LocalDateTime getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(LocalDateTime purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public GiftCertificateDto getCertificateDto() {
        return certificateDto;
    }

    public void setCertificateDto(GiftCertificateDto certificateDto) {
        this.certificateDto = certificateDto;
    }
}
