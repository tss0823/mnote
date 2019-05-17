package com.loong.mnote.service.component;

import com.loong.mnote.common.enums.SmsCodeTypeEnum;
import com.loong.mnote.service.AbstractService;
import com.loong.mnote.service.component.cache.StringCacheService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author: sam
 * @date: 2018-12-19 14:31
 */
@Component
public class SmsComponent  extends AbstractService {

    @Autowired
    private SystemConfigComponent systemConfigComponent;

//    @Autowired
//    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private StringCacheService stringCacheService;

    public String getSmsCode(Integer type,Integer userType,final String areaCode,final String mobile){
//        if (mobile.length() != 11 || !mobile.startsWith("1")) {
//            throw new BizException("手机账号不合法");
//        }
        final String smsCode = RandomStringUtils.random(4, false, true);
        //
        SmsCodeTypeEnum smsCodeTypeEnum = SmsCodeTypeEnum.getByCode(type);
        String smsCodeKey = StringUtils.join(new Object[]{smsCodeTypeEnum.getCacheKey(),userType, areaCode + mobile}, "_");
        String cacheValue = stringCacheService.get(smsCodeKey);
        if (StringUtils.isNotEmpty(cacheValue)) {  //存在不用重复发送
            return cacheValue;
        }
        long period = smsCodeTypeEnum.getTimeUnit().toSeconds(smsCodeTypeEnum.getExpireTime());
        stringCacheService.set(smsCodeKey,smsCode, (int) period);
        if (systemConfigComponent.isProd()) {  //生产环境才发送验证码
            new Thread(() -> {
                String value = "%23code%23%3d"+smsCode;
                //TODO
//                SmsServer.this.sendMsgJH(AppConstant.SMSTemplate.registerId,mobile,value,0);
            }).start();
        }
        return smsCode;
    }

}
