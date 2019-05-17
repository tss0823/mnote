package com.loong.mnote.service;

import com.loong.mnote.common.domain.BaseQuery;
import com.loong.mnote.dal.domain.AuthRes;
import com.loong.mnote.dal.query.AuthResQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: sam
 * @date: 2019-01-10 14:11
 */
@Service
public class AuthResService extends AbstractBizService<AuthRes> {
    @Override
    public List<Predicate> getPredicates(Root<AuthRes> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder, BaseQuery q) {
        AuthResQuery thisQuery = (AuthResQuery) q;
        List<Predicate> predicates = new ArrayList<>();
        if (thisQuery.getDelState() != null) {
            predicates.add(criteriaBuilder.equal(root.get("delState"), thisQuery.getDelState()));
        }
        if (StringUtils.isNotBlank(thisQuery.getAppType())) {
            predicates.add(criteriaBuilder.equal(root.get("appType"), thisQuery.getAppType()));
        }
        if (thisQuery.getStatus() != null) {
            predicates.add(criteriaBuilder.equal(root.get("status"), thisQuery.getStatus()));
        }
        return null;
    }


}
