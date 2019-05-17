package com.loong.mnote.service;

import com.loong.mnote.common.domain.BaseQuery;
import com.loong.mnote.dal.domain.RoleAuthRes;
import com.loong.mnote.dal.query.RoleAuthResQuery;
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
public class RoleAuthResService extends AbstractBizService<RoleAuthRes> {
    @Override
    public List<Predicate> getPredicates(Root<RoleAuthRes> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder, BaseQuery q) {
        RoleAuthResQuery thisQuery = (RoleAuthResQuery) q;
        List<Predicate> predicates = new ArrayList<>();
        if (thisQuery.getDelState() != null) {
            predicates.add(criteriaBuilder.equal(root.get("delState"), thisQuery.getDelState()));
        }
        return predicates;
    }


}
