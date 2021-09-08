package com.epam.esm.dao.constant;

/**
 * Enum with the gift certificates columns names.
 *
 * @author Dzmitry Ahinski
 */
public enum GiftCertificateColumnName {

    ID("certificate_id"),
    NAME("certificate_name"),
    DESCRIPTION("description"),
    PRICE("price"),
    DURATION("duration"),
    CREATE_DATE("create_date"),
    LAST_UPDATE_DATE("last_update_date");

    private String columnName;

    GiftCertificateColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnName() {
        return columnName;
    }
}
