package com.epam.esm.dao.api;

import com.epam.esm.entity.Tag;

import java.util.List;
import java.util.Optional;

/**
 * Interface used for interactions with tags table.
 *
 * @author Dzmitry Ahinski
 */
public interface TagDao {

    /**
     * Adds tag to the table.
     *
     * @param tag object to be added.
     *
     */
    void insert(Tag tag);

    /**
     * Deletes tag from the table.
     *
     * @param tag object to be deleted.
     *
     */
    void delete(Tag tag);

    /**
     * Finds the tag by id.
     *
     * @param id of the object to be found.
     * @return optional object with found tag.
     */
    Optional<Tag> findById(long id);

    /**
     * Finds the tag by name.
     *
     * @param name of the object to be found.
     * @return optional object with found tag.
     */
    Optional<Tag> findByName(String name);

    /**
     * Finds all the tags.
     *
     * @param page current page for search
     * @param size count of found entities
     *
     * @return list with found tags
     */
    List<Tag> findAll(int page, int size);

    /**
     * Finds all the existing tags.
     *
     * @return list with found tags
     */
    List<Tag> findAllExisting(List<Tag> tags);

    /**
     * Disconnect tags from all certificates
     *
     * @param id as tag id
     */
    void disconnectTagFromCertificates(long id);

    /**
     * Finds the most widely used tag of a user with the highest cost of all orders
     *
     * @param userId as user id
     * @return Optional object of found tag
     */
    Optional<Tag> findMostExpensiveTag(long userId);
}
