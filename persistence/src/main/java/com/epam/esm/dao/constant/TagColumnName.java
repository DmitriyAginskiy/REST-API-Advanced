package com.epam.esm.dao.constant;

/**
 * Enum with the tags columns names.
 *
 * @author Dzmitry Ahinski
 */
public enum TagColumnName {

    TAG_ID("tag_id"),
    TAG_NAME("tag_name");

    private String columnName;

    TagColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnName() {
        return columnName;
    }
}
