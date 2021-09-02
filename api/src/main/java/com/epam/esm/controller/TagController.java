package com.epam.esm.controller;


import com.epam.esm.entity.Tag;
import com.epam.esm.service.TagService;
import com.epam.esm.util.HateoasWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Tags controller class.
 *
 * @author Dzmitry Ahinski
 */
@RestController
@RequestMapping("/tags")
public class TagController {

    private final TagService tagService;
    private final HateoasWrapper wrapper;

    /**
     * Init the tags controller class.
     *
     * @author Dzmitry Ahinski
     */
    @Autowired
    public TagController(TagService tagService, HateoasWrapper wrapper) {
        this.tagService = tagService;
        this.wrapper = wrapper;
    }

    /**
     * Create a new tag.
     *
     * @param tag an object to be created
     * @return created Tag object.
     */
    @PostMapping(produces = "application/json; charset=utf-8")
    public Tag createTag(@RequestBody Tag tag) {
        Tag insertedTag = tagService.insert(tag);
        wrapper.tagWrap(insertedTag);
        return insertedTag;
    }

    /**
     * Finds tag by id
     *
     * @param id the id of tag to be found.
     * @return found tag object.
     */
    @GetMapping(value = "/{id}", produces = "application/json; charset=utf-8")
    public Tag findTagById(@PathVariable long id) {
        Tag tag = tagService.findById(id);
        wrapper.tagWrap(tag);
        return tag;
    }

    /**
     * Finds all tags.
     *
     * @return list with found tags.
     * @param page pagination current page.
     * @param size pagination current page size.
     */
    @GetMapping(produces = "application/json; charset=utf-8")
    public List<Tag> findAll(@RequestParam(defaultValue = "0") int page,
                             @RequestParam(defaultValue = "10") int size) {
        List<Tag> tags = tagService.findAll(page, size);
        tags.forEach(wrapper::tagWrap);
        return tags;
    }

    /**
     * Deletes tag by id
     *
     * @return Response entity with NO CONTENT status
     */
    @DeleteMapping(value = "/{id}", produces = "application/json; charset=utf-8")
    public ResponseEntity<String> deleteTag(@PathVariable long id) {
        tagService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");
    }

    /**
     * Finds all most popular tag of a user (by cost).
     *
     * @return optional with found tag.
     * @param userId user id
     *
     */
    @GetMapping(produces = "application/json; charset=utf-8", params = "userId")
    public Tag findMostPopularUserTags(@RequestParam long userId) {
        Tag tag = tagService.findMostExpensiveTag(userId);
        wrapper.tagWrap(tag);
        return tag;
    }
}
