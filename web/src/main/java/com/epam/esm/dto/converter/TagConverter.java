package com.epam.esm.dto.converter;

import com.epam.esm.dto.TagDto;
import com.epam.esm.entity.Tag;
import org.springframework.stereotype.Component;

/**
 * Tag DTO/ENTITY converter class.
 *
 * @author Dzmitry Ahinski
 */
@Component
public class TagConverter {

    public TagDto convertToDto(Tag tag) {
        return new TagDto(
              tag.getId(),
              tag.getName()
        );
    }

    public Tag convertToEntity(TagDto tagDto) {
        return new Tag(
                tagDto.getId(),
                tagDto.getName()
        );
    }
}
