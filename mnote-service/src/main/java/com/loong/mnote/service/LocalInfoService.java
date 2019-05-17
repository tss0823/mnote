package com.loong.mnote.service;

import com.loong.mnote.common.domain.BaseQuery;
import com.loong.mnote.common.util.BeanUtils;
import com.loong.mnote.dal.domain.LocalInfo;
import com.loong.mnote.dal.domain.LocalInfoDetail;
import com.loong.mnote.dal.param.LocalInfoDetailParam;
import com.loong.mnote.dal.param.LocalInfoParam;
import com.loong.mnote.dal.query.LocalInfoQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: sam
 * @date: 2018-12-20 18:35
 */
@Service
public class LocalInfoService extends AbstractBizService<LocalInfo> {

    @Autowired
    private LocalInfoDetailService localInfoDetailService;

    @Override
    public List<Predicate> getPredicates(Root<LocalInfo> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder, BaseQuery q) {
        LocalInfoQuery thisQuery = (LocalInfoQuery) q;
        List<Predicate> predicates = new ArrayList<>();
        if (thisQuery.getId() != null) {
            predicates.add(criteriaBuilder.equal(root.get("id"), thisQuery.getId()));
        }
        if (StringUtils.isNotEmpty(thisQuery.getKey())) {
            predicates.add(criteriaBuilder.equal(root.get("key"), thisQuery.getKey()));
        }
        if (thisQuery.getAppType() != null) {
            predicates.add(criteriaBuilder.equal(root.get("appType"), thisQuery.getAppType()));
        }
        if (thisQuery.getType() != null) {
            predicates.add(criteriaBuilder.equal(root.get("type"), thisQuery.getType()));
        }
        return predicates;
    }


    public void saveByParam(LocalInfoParam localInfoParam){
        LocalInfo localInfo = BeanUtils.beanCopy(localInfoParam, LocalInfo.class);
        this.save(localInfo);
        List<LocalInfoDetailParam> itemList = localInfoParam.getItemList();
        for (LocalInfoDetailParam localInfoDetailParam : itemList) {
            LocalInfoDetail localInfoDetail = BeanUtils.beanCopy(localInfoDetailParam, LocalInfoDetail.class);
            localInfoDetail.setInfoId(localInfo.getId());
            localInfoDetailService.save(localInfoDetail);
        }
    }
}
