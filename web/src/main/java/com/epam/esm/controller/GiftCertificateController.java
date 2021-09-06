package com.epam.esm.controller;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.service.api.GiftCertificateService;
import com.epam.esm.util.HateoasWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Gift certificates controller class.
 *
 * @author Dzmitry Ahinski
 */
@RestController
@RequestMapping("/certificates")
public class GiftCertificateController {

    private final GiftCertificateService certificateService;
    private final HateoasWrapper wrapper;

    /**
     * Init the gift certificates controller class.
     *
     * @author Dzmitry Ahinski
     */
    @Autowired
    public GiftCertificateController(GiftCertificateService certificateService, HateoasWrapper wrapper) {
        this.certificateService = certificateService;
        this.wrapper = wrapper;
    }

    /**
     * Create a new gift certificate;
     *
     * @param certificate an object to be created
     * @return created gift certificate object
     */
    @PostMapping(produces = "application/json; charset=utf-8")
    public GiftCertificate createGiftCertificate(@RequestBody GiftCertificate certificate) {
        GiftCertificate giftCertificate = certificateService.insert(certificate);
        wrapper.certificateWrap(giftCertificate);
        return giftCertificate;
    }

    /**
     * Finds all gift certificates by criteria
     *
     * @param certificateName the name of certificate to be found.
     * @param description the description of certificate to be found.
     * @param sortByDate the sort param for creation date.
     * @param sortByName the sort param for name.
     * @param tagName the list of the tags names.
     * @param page pagination current page.
     * @param size pagination current page size.
     * @return list with found items.
     */
    @GetMapping(produces = "application/json; charset=utf-8")
    public List<GiftCertificate> findAllGiftCertificates(@RequestParam(required = false) String certificateName,
                                                         @RequestParam(required = false) String description,
                                                         @RequestParam(required = false) String sortByDate,
                                                         @RequestParam(required = false) String sortByName,
                                                         @RequestParam(required = false) List<String> tagName,
                                                         @RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "10") int size) {
        List<GiftCertificate> certificates = certificateService.findAll(certificateName, description, sortByDate, sortByName, tagName, page, size);
        certificates.forEach(wrapper::certificateWrap);
        return certificates;
    }

    /**
     * Finds gift certificate by id
     *
     * @param id of certificate to be found.
     * @return found gift certificate object.
     */
    @GetMapping(value = "/{id}", produces = "application/json; charset=utf-8")
    public GiftCertificate findGiftCertificateById(@PathVariable long id) {
        GiftCertificate giftCertificate = certificateService.findById(id);
        wrapper.certificateWrap(giftCertificate);
        return giftCertificate;
    }

    /**
     * Deletes gift certificate.
     *
     * @param id of certificate to be deleted.
     * @return Response entity with NO CONTENT status
     */
    @DeleteMapping(value = "/{id}", produces = "application/json; charset=utf-8")
    public ResponseEntity<String> deleteGiftCertificate(@PathVariable long id) {
        certificateService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");
    }

    /**
     * Updates gift certificate.
     *
     * @param id the id of certificate to be updated.
     * @param certificate with new fields for update.
     * @return Updated object.
     */
    @PatchMapping(value = "/{id}", produces = "application/json; charset=utf-8")
    public GiftCertificate updateGiftCertificate(@PathVariable long id, @RequestBody GiftCertificate certificate) {
        GiftCertificate giftCertificate = certificateService.update(id, certificate);
        wrapper.certificateWrap(giftCertificate);
        return giftCertificate;
    }
}
