package com.loong.mnote.dal.dao;

import com.loong.mnote.dal.domain.AppDevice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppDeviceDao extends JpaRepository<AppDevice, Long> {
}
