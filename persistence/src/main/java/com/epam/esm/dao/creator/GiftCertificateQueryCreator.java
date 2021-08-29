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
    private static final String COMMA = ",";
    private static final String SEMICOLON = ";";
    private static final String WHITESPACE = " ";

    /**
     * Creates gift certificate searching query with criteria.
     *
     * @param criteriaList as list
     * @return String as final search query
     */
    public static String createSearchQuery(List<Criteria> criteriaList) {
        if(criteriaList == null || criteriaList.isEmpty()) {
            return QUERY_WITHOUT_SEARCH_CRITERIA;
        }
        StringBuilder finalQuery = new StringBuilder(QUERY_WITHOUT_SEARCH_CRITERIA);
        addSortCriteria(criteriaList, finalQuery);
        finalQuery.append(WHITESPACE);
        finalQuery.append(WHERE_WORD);
        addSearchCriteria(criteriaList, finalQuery);
        finalQuery.append(WHITESPACE).append(GROUP_BY).append(WHITESPACE);
        finalQuery.append(GiftCertificateColumnName.ID);
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
        if(!searchCriteriaList.isEmpty()) {
            boolean isFirstSearchCriteria = true;
            for(Criteria criteria : searchCriteriaList) {
                if(isFirstSearchCriteria) {
                    finalQuery.append(WHITESPACE);
                    isFirstSearchCriteria = false;
                } else {
                    finalQuery.append(WHITESPACE);
                    finalQuery.append(AND_WORD);
                    finalQuery.append(WHITESPACE);
                }
                criteria.addCriteria(finalQuery);
            }
        }
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
