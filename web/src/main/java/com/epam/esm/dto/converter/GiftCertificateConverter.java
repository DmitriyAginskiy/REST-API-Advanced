package com.epam.esm.dto.converter;

import com.epam.esm.dto.GiftCertificateDto;
import com.epam.esm.dto.TagDto;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * Gift certificate DTO/ENTITY converter class.
 *
 * @author Dzmitry Ahinski
 */
@Component
public class GiftCertificateConverter {

    private final TagConverter tagConverter;

    @Autowired
    public GiftCertificateConverter(TagConverter tagConverter) {
        this.tagConverter = tagConverter;
    }

    public GiftCertificateDto convertToDto(GiftCertificate giftCertificate) {
        Set<TagDto> tagDtoSet = new HashSet<>();
        giftCertificate.getTags().forEach(tag -> tagDtoSet.add(tagConverter.convertToDto(tag)));
        return new GiftCertificateDto(
                giftCertificate.getId(),
                giftCertificate.getName(),
                giftCertificate.getDescription(),
                giftCertificate.getPrice(),
                giftCertificate.getDuration(),
                giftCertificate.getCreateDate(),
                giftCertificate.getLastUpdateDate(),
                tagDtoSet
        );
    }

    public GiftCertificate convertToEntity(GiftCertificateDto giftCertificateDto) {
        Set<Tag> tagSet = new HashSet<>();
        giftCertificateDto.getTags().forEach(tagDto -> tagSet.add(tagConverter.convertToEntity(tagDto)));
        return new GiftCertificate(
                giftCertificateDto.getName(),
                giftCertificateDto.getDescription(),
                giftCertificateDto.getPrice(),
                giftCertificateDto.getDuration(),
                tagSet
        );
    }
}
