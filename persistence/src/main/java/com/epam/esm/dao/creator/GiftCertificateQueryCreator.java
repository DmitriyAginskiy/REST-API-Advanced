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
    private static final String COUNT_ALL = "COUNT(*)";
    private static final String HAVING = "HAVING";
    private static final String EQUALS = "=";
    private static final String COMMA = ",";
    private static final String SEMICOLON = ";";
    private static final String WHITESPACE = " ";
    private static final String OR_WORD = "OR";

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
        int counter = 0;
        if(!searchCriteriaList.isEmpty()) {
            for(int i = 0; i < searchCriteriaList.size(); i++) {
                Criteria criteria = searchCriteriaList.get(i);
                finalQuery.append(WHITESPACE);
                if(criteria.getCriteriaField().equals(TagColumnName.TAG_NAME)) {
                    counter++;
                }
                criteria.addCriteria(finalQuery);
                if(i != searchCriteriaList.size() - 1) {
                    finalQuery.append(WHITESPACE);
                    finalQuery.append(criteria.getCriteriaField().equals(TagColumnName.TAG_NAME) ?
                            OR_WORD : AND_WORD);
                }
            }
            if(counter > 0) {
                finalQuery.append(WHITESPACE).append(GROUP_BY).append(WHITESPACE).append(GiftCertificateColumnName.ID);
                finalQuery.append(WHITESPACE).append(HAVING).append(WHITESPACE).append(COUNT_ALL);
                finalQuery.append(WHITESPACE).append(EQUALS).append(WHITESPACE).append(counter);
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
