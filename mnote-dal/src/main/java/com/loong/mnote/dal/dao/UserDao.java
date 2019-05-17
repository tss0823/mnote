package com.loong.mnote.dal.dao;

import com.loong.mnote.dal.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 用户DAO
 * @author: sam
 * @date: 2018-12-19 16:06
 */
public interface UserDao extends JpaRepository<User, Long> {


}
