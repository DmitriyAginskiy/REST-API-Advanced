package com.epam.esm.dao.api;

import com.epam.esm.dao.exception.DaoException;
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
     * @throws DaoException if the element already exists
     */
    void insert(Tag tag) throws DaoException;

    /**
     * Deletes tag from the table.
     *
     * @param id of object to be deleted.
     * @throws DaoException if the element does not exist
     */
    void delete(long id) throws DaoException;

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
     * Finds the most widely used tag of a user with the highest cost of all orders
     *
     * @return Optional object of found tag
     */
    Optional<Tag> findMostExpensiveTag();
}
