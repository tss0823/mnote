package com.loong.mnote.service;

import com.google.common.collect.Lists;
import com.loong.mnote.common.domain.BaseQuery;
import com.loong.mnote.common.domain.Pager;
import com.loong.mnote.dal.configuration.TransactionPersistBizDao;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@Transactional
public abstract class AbstractBizService<T> extends AbstractService {

    public SimpleJpaRepository<T, Serializable> baseDao;

    @PersistenceContext
    public EntityManager entityManager;

    @Autowired
    private TransactionPersistBizDao<T> transactionPersistBizDao;

    public AbstractBizService() {
    }

    @PostConstruct
    public void init() {
        Type type = getClass().getGenericSuperclass();
        Class<T> clazz = (Class<T>) ((ParameterizedType) type).getActualTypeArguments()[0];
        this.baseDao = new SimpleJpaRepository(clazz, this.entityManager);
        transactionPersistBizDao.init(clazz,this.baseDao);
    }

    public abstract List<Predicate> getPredicates(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder, BaseQuery q);

    private List<Predicate> getThisPredicates(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder, BaseQuery q){
        List<Predicate> predicates = getPredicates(root, query, criteriaBuilder, q);

        if (CollectionUtils.isEmpty(predicates)) {
            return Lists.newArrayList();
        }

        return predicates;
    }

    public T save(T entity) {
        return this.transactionPersistBizDao.save(entity);
    }

    public T saveWithoutTx(T entity) {
        return this.transactionPersistBizDao.saveWithoutTx(entity);
    }

    public List<T> saveAll(Iterable<T> entityList) {
        return this.transactionPersistBizDao.saveAll(entityList);
    }

    public void deleteById(Serializable id) {
        this.transactionPersistBizDao.deleteById(id);
    }



    public T findById(Serializable id) {
        Optional<T> optional =  this.baseDao.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    public T findOne(BaseQuery q) {
        return this.baseDao.findOne((Specification<T>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = getThisPredicates(root, query, criteriaBuilder, q);
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        }).orElse(null);
    }

    public List<T> findAll(BaseQuery q) {
        List<Sort.Order> orderList = wrapOrder(q);
        return this.baseDao.findAll((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = getThisPredicates(root, query, criteriaBuilder, q);
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        }, Sort.by(orderList));
    }

    public Pager<T> findByPage(BaseQuery query) {
        if (query.getPageIndex() <= 0) {
            query.setPageIndex(1);
        }
        Pager<T> pager = new Pager<>(query.getPageIndex(), query.getPageSize());
        //count
        long count = count(query);
        if (count == 0) {
            return pager;
        } else {
            pager.setTotal(count);
        }
        //dataList
        pager.setDataList(pageQuery(query));
        return pager;

    }

    /**
     * 分页查询
     * @param query
     * @return
     */
    public List<T> pageQuery(BaseQuery query) {
        List<Sort.Order> orderList = wrapOrder(query);
        Pageable pageable = PageRequest.of(query.getPageIndex() - 1, query.getPageSize(), Sort.by(orderList));
        Page<T> page = this.baseDao.findAll((Specification<T>) (root, q, criteriaBuilder) -> {
            List<Predicate> predicates = getThisPredicates(root, q, criteriaBuilder, query);
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        }, pageable);

        return page.getContent();
    }

    /**
     * 统计
     * @param query
     * @return
     */
    public long count(BaseQuery query) {
        long count = this.baseDao.count((root, q, criteriaBuilder) -> {
            List<Predicate> predicates = getThisPredicates(root, q, criteriaBuilder, query);
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        });
        return count;
    }

    /**
     * 组装order
     * @param query
     * @return
     */
    private List<Sort.Order> wrapOrder(BaseQuery query) {
        String orderByColumn = query.getOrderByColumn();
        String ascOrDesc = query.getAscOrDesc();
        String[] orderByColumnArray = orderByColumn.split(",");
        String[] ascOrDescArray = ascOrDesc.split(",");
        List<Sort.Order> orderList = new ArrayList<>();
        for (int i = 0; i < orderByColumnArray.length; i++) {
            String orderByValue = orderByColumnArray[i];
            String ascOrDescValue = ascOrDescArray[i];
            orderList.add(new Sort.Order(Sort.Direction.fromString(ascOrDescValue), orderByValue));
        }

        return orderList;
    }
}
