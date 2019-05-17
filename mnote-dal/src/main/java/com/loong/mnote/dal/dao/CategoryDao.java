package com.loong.mnote.dal.dao;

import com.loong.mnote.dal.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 * @Description: 类目管理dao
 *
 * @Author: zheng.yuan
 * @Date: 2019-01-10
 **/
public interface CategoryDao extends JpaRepository<Category,Long> {

    @Transactional
    @Modifying
    @Query(value = "update category set delState = 1,gmtModified = now(3) where id = ?", nativeQuery = true)
    int delete(Long id);

    @Transactional
    @Modifying
    @Query(value = "update category set delState = 1,gmtModified = now(3) where id in (:idList)", nativeQuery = true)
    int batchDelete(@Param("idList") List<Long> idList);
}
