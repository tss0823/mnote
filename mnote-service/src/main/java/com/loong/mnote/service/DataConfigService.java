package com.loong.mnote.service;

import com.loong.mnote.common.domain.BaseQuery;
import com.loong.mnote.dal.domain.DataConfig;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * @author: sam
 * @date: 2019-01-09 09:58
 */
@Service
public class DataConfigService extends AbstractBizService<DataConfig>{

    @Override
    public List<Predicate> getPredicates(Root<DataConfig> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder, BaseQuery q) {
        return null;
    }


}
