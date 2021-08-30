package com.epam.esm.dao.constant;

/**
 * Class with the tags queries.
 *
 * @author Dzmitry Ahinski
 */
public final class TagQuery {

    public static final String FIND_ALL_TAGS = "SELECT * FROM tags LIMIT ?, ?;";

    public static final String FIND_BY_NAME = "SELECT * FROM tags WHERE tag_name = ?;";

    public static final String DISCONNECT_TAG_FROM_CERTIFICATES = "DELETE FROM gift_certificates_has_tags WHERE tags_id_fk = ?;";


    private TagQuery() {

    }
}
