package com.loong.mnote.dal.configuration;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class TransactionPersistBizDao<T> extends AbstractDao {


    @PersistenceContext
    private EntityManager entityManager;

    private JpaEntityInformation<T, ?> entityInformation;

    private  SimpleJpaRepository<T, Serializable> jpaRepository;

    public TransactionPersistBizDao() {

    }

    @Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
    public void init(Class<T> clazz,SimpleJpaRepository<T, Serializable> jpaRepository){
        this.entityInformation = JpaEntityInformationSupport.getEntityInformation(clazz, entityManager);
        this.jpaRepository = jpaRepository;
    }

    public T save(T entity) {
        if (entityInformation.isNew(entity)) {
            entityManager.persist(entity);
            return entity;
        } else {
            return entityManager.merge(entity);
        }

    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public T saveWithoutTx(T entity) {
        if (entityInformation.isNew(entity)) {
            entityManager.persist(entity);
            return entity;
        } else {
            return entityManager.merge(entity);
        }

    }

    public List<T> saveAll(Iterable<T> entityList) {
        Assert.notNull(entityList, "The given Iterable of entities not be null!");

        List<T> result = new ArrayList<T>();

        for (T entity : entityList) {
            result.add(save(entity));
        }

        return result;
    }
//
    public void deleteById(Serializable id) {
        Assert.notNull(id, "id must not be null");

        T entity = jpaRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(
                String.format("No %s entity with id %s exists!", entityInformation.getJavaType(), id), 1));

        entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));

    }


}
