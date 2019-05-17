package com.loong.mnote.service.component;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Supplier;

/**
 * @author: lhd
 * @date: 2019/1/4 17:33
 */
@Service
public class TransactionHelper {

    @Transactional(rollbackFor = Exception.class)
    public <T> T doInTransaction(Supplier<T> supplier) {

        return supplier.get();
    }
}
