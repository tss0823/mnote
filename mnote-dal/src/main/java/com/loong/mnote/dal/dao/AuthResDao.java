package com.loong.mnote.dal.dao;

import com.loong.mnote.dal.domain.AuthRes;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author: sam
 * @date: 2019-01-10 14:07
 */
public interface AuthResDao extends JpaRepository<AuthRes, Long> {
}
