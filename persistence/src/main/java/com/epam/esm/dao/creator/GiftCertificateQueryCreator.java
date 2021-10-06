package com.epam.esm.dao.creator;

import com.epam.esm.dao.constant.GiftCertificateColumnName;
import com.epam.esm.dao.constant.TagColumnName;
import com.epam.esm.dao.creator.criteria.Criteria;
import com.epam.esm.dao.creator.criteria.impl.SearchCriteria;
import com.epam.esm.dao.creator.criteria.impl.SortCriteria;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Class that creates gift certificate update or search query with criteria.
 *
 * @author Dzmitry Ahinski
 */
public class GiftCertificateQueryCreator {

    private static final String QUERY_WITHOUT_SEARCH_CRITERIA = "SELECT gift_certificates.*, tags.* FROM gift_certificates LEFT JOIN " +
            "gift_certificates_has_tags ON certificate_id = gift_certificates_id_fk LEFT JOIN tags ON tag_id = tags_id_fk";
    private static final String AND_WORD = "AND";
    private static final String WHERE_WORD = "WHERE";
    private static final String ORDER_BY = "ORDER BY";
    private static final String GROUP_BY = "GROUP BY";
    private static final String HAVING = "HAVING";
    private static final String COMMA = ",";
    private static final String SEMICOLON = ";";
    private static final String WHITESPACE = " ";
    private static final String OR_WORD = "OR";
    private static final String LIMIT = "LIMIT";
    private static final String OPENING_BRACKET = "(";
    private static final String CLOSING_BRACKET = ")";
    private static final String CASE = "CASE";
    private static final String WHEN = "WHEN";
    private static final String THEN = "THEN";
    private static final String END = "END";
    private static final String COUNT = "COUNT";
    private static final String ONE = "1";
    private static final String EQUAL_OR_GREATER = ">=";

    /**
     * Creates gift certificate searching query with criteria.
     *
     * @param criteriaList as list
     * @return String as final search query
     */
    public static String createSearchQuery(List<Criteria> criteriaList, int page, int size) {
        if(criteriaList == null || criteriaList.isEmpty()) {
            return QUERY_WITHOUT_SEARCH_CRITERIA;
        }
        StringBuilder finalQuery = new StringBuilder(QUERY_WITHOUT_SEARCH_CRITERIA);
        finalQuery.append(WHITESPACE);
        addSearchCriteria(criteriaList, finalQuery);
        addSortCriteria(criteriaList, finalQuery);
        finalQuery.append(WHITESPACE).append(LIMIT).append(WHITESPACE);
        finalQuery.append(page).append(COMMA).append(WHITESPACE).append(size);
        finalQuery.append(SEMICOLON);
        return finalQuery.toString();
    }

    /**
     * Adds search criteria to the final query/
     *
     * @param criteriaList as list of criteria
     * @param finalQuery as final search query
     */
    private static void addSearchCriteria(List<Criteria> criteriaList, StringBuilder finalQuery) {
        List<Criteria> searchCriteriaList = criteriaList.stream().filter(t -> t instanceof SearchCriteria).collect(Collectors.toList());
        addWhereCondition(searchCriteriaList, finalQuery);
        finalQuery.append(WHITESPACE).append(GROUP_BY).append(WHITESPACE).append(GiftCertificateColumnName.ID.getColumnName());
        List<Criteria> tagNamesCriteriaList = searchCriteriaList.stream().filter(criteria ->
                criteria.getCriteriaField().equalsIgnoreCase(TagColumnName.TAG_NAME.getColumnName()))
                .collect(Collectors.toList());
        if(!tagNamesCriteriaList.isEmpty()) {
            finalQuery.append(WHITESPACE).append(HAVING).append(WHITESPACE);
            tagNamesCriteriaList.forEach(criteria -> {
                if(!criteria.equals(tagNamesCriteriaList.stream().findFirst().get())) {
                    finalQuery.append(WHITESPACE).append(AND_WORD).append(WHITESPACE);
                }
                addTagNameHavingLikeCondition(criteria, finalQuery);
            });
        }
    }

    /**
     * Adds where condition to the final query.
     *
     * @param searchCriteriaList as list of criteria
     * @param finalQuery as final search query
     */
    private static void addWhereCondition(List<Criteria> searchCriteriaList, StringBuilder finalQuery) {
        if(!searchCriteriaList.isEmpty()) {
            finalQuery.append(WHERE_WORD);
        }
        searchCriteriaList.forEach(criteria -> {
            finalQuery.append(WHITESPACE);
            criteria.addCriteria(finalQuery);
            if(searchCriteriaList.size() > 1 && !criteria.equals(searchCriteriaList.get(searchCriteriaList.size() - 1))) {
                finalQuery.append(WHITESPACE).append(criteria.getCriteriaField().equalsIgnoreCase(TagColumnName.TAG_NAME.getColumnName()) ?
                        OR_WORD : AND_WORD);
            }
        });
    }

    /**
     * Adds having condition with tags names to the final query.
     *
     * @param criteria as criteria to be added
     * @param finalQuery as final search query
     */
    private static void addTagNameHavingLikeCondition(Criteria criteria, StringBuilder finalQuery) {
        finalQuery.append(COUNT).append(OPENING_BRACKET).append(CASE).append(WHITESPACE).append(WHEN)
                .append(WHITESPACE);
        criteria.addCriteria(finalQuery);
        finalQuery.append(WHITESPACE).append(THEN).append(WHITESPACE).append(ONE).append(WHITESPACE)
                .append(END).append(CLOSING_BRACKET).append(WHITESPACE).append(EQUAL_OR_GREATER)
                .append(WHITESPACE).append(ONE);
    }

    /**
     * Adds sort criteria to the final query.
     *
     * @param criteriaList as list of criteria
     * @param finalQuery as final search query
     */
    private static void addSortCriteria(List<Criteria> criteriaList, StringBuilder finalQuery) {
        List<Criteria> sortCriteriaList = criteriaList.stream().filter(t -> t instanceof SortCriteria).collect(Collectors.toList());
        if(!sortCriteriaList.isEmpty()) {
            boolean isFirstSortCriteria = true;
            for(Criteria c : sortCriteriaList) {
                if(isFirstSortCriteria) {
                    finalQuery.append(WHITESPACE).append(ORDER_BY);
                    isFirstSortCriteria = false;
                } else {
                    finalQuery.append(WHITESPACE).append(COMMA);
                }
                c.addCriteria(finalQuery);
            }
        }
    }
}
