package com.loong.mnote.service;

import com.loong.mnote.common.domain.BaseQuery;
import com.loong.mnote.common.util.StringUtil;
import com.loong.mnote.dal.dao.CategoryDao;
import com.loong.mnote.dal.domain.Category;
import com.loong.mnote.dal.query.CategoryQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @Description: 类目管理service
 *
 * @Author: zheng.yuan
 * @Date: 2019-01-10
 **/
@Service
public class CategoryService extends AbstractBizService<Category> {

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public List<Predicate> getPredicates(Root<Category> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder, BaseQuery q) {
        CategoryQuery categoryQuery = (CategoryQuery) q;
        List<Predicate> predicates = new ArrayList<>();
        if (categoryQuery.getId() != null) {
            predicates.add(criteriaBuilder.equal(root.get("id"), categoryQuery.getId()));
        }
        if (StringUtil.isNotBlank(categoryQuery.getDesc())) {
            predicates.add(criteriaBuilder.equal(root.get("desc"), categoryQuery.getDesc()));
        }
        if (StringUtil.isNotBlank(categoryQuery.getLocal())) {
            predicates.add(criteriaBuilder.equal(root.get("local"), categoryQuery.getLocal()));
        }
        if (StringUtil.isNotBlank(categoryQuery.getName())) {
            predicates.add(criteriaBuilder.equal(root.get("name"), categoryQuery.getName()));
        }
        if (categoryQuery.getParentId() != null) {
            predicates.add(criteriaBuilder.equal(root.get("parentId"), categoryQuery.getParentId()));
        }
        if (categoryQuery.getDelState() != null) {
            predicates.add(criteriaBuilder.equal(root.get("delState"), categoryQuery.getDelState()));
        }
        return predicates;
    }

    public int delete(Long id) {
        return categoryDao.delete(id);
    }

    public int batchDelete(String ids) {
        List<Long> idList = Arrays.stream(ids.split(","))
                                  .map(Long::valueOf)
                                  .collect(Collectors.toList());
        return categoryDao.batchDelete(idList);
    }

}
