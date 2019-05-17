package com.loong.mnote.service;

import com.loong.mnote.common.domain.BaseQuery;
import com.loong.mnote.common.util.BeanUtils;
import com.loong.mnote.dal.dao.AppDeviceDao;
import com.loong.mnote.dal.domain.AppDevice;
import com.loong.mnote.dal.query.AppDeviceQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class AppDeviceService extends AbstractBizService<AppDevice> {

    @Autowired
    private AppDeviceDao appDeviceDao;


    @Override
    public List<Predicate> getPredicates(Root<AppDevice> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder, BaseQuery q) {
        AppDeviceQuery appDeviceQuery = (AppDeviceQuery) q;
        List<Predicate> predicates = new ArrayList<>();
        if (StringUtils.isNotEmpty(appDeviceQuery.getDeviceId())) {
            predicates.add(criteriaBuilder.equal(root.get("deviceId"), appDeviceQuery.getDeviceId()));
        }
        if (StringUtils.isNotEmpty(appDeviceQuery.getPlatform())) {
            predicates.add(criteriaBuilder.equal(root.get("platform"), appDeviceQuery.getPlatform()));
        }
        if (StringUtils.isNotEmpty(appDeviceQuery.getAppName())) {
            predicates.add(criteriaBuilder.equal(root.get("appName"), appDeviceQuery.getAppName()));
        }
        if (StringUtils.isNotEmpty(appDeviceQuery.getAppVersion())) {
            predicates.add(criteriaBuilder.equal(root.get("appVersion"), appDeviceQuery.getAppVersion()));
        }
        return predicates;
    }

    public AppDevice getDevice(String deviceId,String platform,String appName){
        AppDeviceQuery query = new AppDeviceQuery();
        query.setDeviceId(deviceId);
        query.setPlatform(platform);
        query.setAppName(appName);
//        query.setAppVersion(appVersion);
        AppDevice appDevice = this.findOne(query);
        return appDevice;

    }

    public void report(AppDevice appDevice){
        AppDevice dbAppDevice = getDevice(appDevice.getDeviceId(), appDevice.getPlatform(), appDevice.getAppName());
        if(dbAppDevice != null){
            //修改相关数据
            BeanUtils.beanCopy(appDevice,dbAppDevice);
            appDevice = dbAppDevice;
        }else{
            //app 第一次上报，生成数据保存

        }
        this.save(appDevice);


    }
}
