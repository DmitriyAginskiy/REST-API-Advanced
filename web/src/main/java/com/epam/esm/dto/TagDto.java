package com.epam.esm.dto;

import com.epam.esm.entity.GiftCertificate;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

/**
 * Entity of a tag.
 *
 * @author Dzmitry Ahinski
 */
public class TagDto extends RepresentationModel<TagDto> {

    private long id;
    private String name;

    public TagDto() {

    }

    public TagDto(String name) {
        this.name = name;
    }

    public TagDto(long id, String name) {
        this.id = id;
        this.name = name;
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
}
