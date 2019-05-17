package com.loong.mnote.dal.dao;

import com.loong.mnote.dal.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author: sam
 * @date: 2019-01-10 14:08
 */
public interface RoleDao extends JpaRepository<Role, Long> {
}
