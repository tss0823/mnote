package com.loong.mnote.service.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.function.Supplier;

/**
 * @author: sam
 * @date: 2019-01-17 16:35
 */
@Component
public class TransactionComponent {

    @Autowired
    JpaTransactionManager transactionManager;

    public <T> T execute(Supplier<T> supplier){
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus status = transactionManager.getTransaction(def);
        T t = null;
        try {
            t = supplier.get();
            transactionManager.commit(status);

        } catch (Exception e) {
            transactionManager.rollback(status);
        }
        return t;
    }
}
