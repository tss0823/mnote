package com.loong.mnote.service;

import com.loong.mnote.common.domain.BaseQuery;
import com.loong.mnote.dal.domain.LocalInfo;
import com.loong.mnote.dal.domain.LocalInfoDetail;
import com.loong.mnote.dal.query.LocalInfoDetailQuery;
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
 * @date: 2018-12-20 18:37
 */
@Service
public class LocalInfoDetailService extends AbstractBizService<LocalInfoDetail> {

    @Autowired
    private LocalInfoService localInfoService;

    @Override
    public List<Predicate> getPredicates(Root<LocalInfoDetail> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder, BaseQuery q) {
        LocalInfoDetailQuery thisQuery = (LocalInfoDetailQuery) q;
        List<Predicate> predicates = new ArrayList<>();
        if (thisQuery.getId() != null) {
            predicates.add(criteriaBuilder.equal(root.get("id"), thisQuery.getId()));
        }
        if (StringUtils.isNotEmpty(thisQuery.getLanguage())) {
            predicates.add(criteriaBuilder.equal(root.get("language"), thisQuery.getLanguage()));
        }
        return predicates;
    }

    /**
     * 获取db中的国际化值，后续走缓存 TODO
     * @param appType
     * @param bizType
     * @param language
     * @param key
     * @return
     */
    public String findByKey(Integer appType,Integer bizType,String language,String key){
        LocalInfoQuery localInfoQuery = new LocalInfoQuery();
        localInfoQuery.setAppType(appType);
        localInfoQuery.setType(bizType);
        localInfoQuery.setKey(key);
        LocalInfo localInfo = localInfoService.findOne(localInfoQuery);
        if(localInfo == null){
            return null;
        }
        LocalInfoDetailQuery localInfoDetailQuery = new LocalInfoDetailQuery();
        localInfoDetailQuery.setInfoId(localInfo.getId());
        localInfoDetailQuery.setLanguage(language);
        LocalInfoDetail localInfoDetail = this.findOne(localInfoDetailQuery);
        if(localInfoDetail != null){
            return localInfoDetail.getContent();
        }
        return null;
    }
}
