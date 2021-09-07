package com.epam.esm.dto;

import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;

public class UserDto extends RepresentationModel<UserDto> {

    private long id;
    private String name;
    private BigDecimal cash;

    public UserDto() {
    }

    public UserDto(long id, String name, BigDecimal cash) {
        this.id = id;
        this.name = name;
        this.cash = cash;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getCash() {
        return cash;
    }

    public void setCash(BigDecimal cash) {
        this.cash = cash;
    }
}
