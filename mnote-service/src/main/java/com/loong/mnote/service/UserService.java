package com.loong.mnote.service;

import com.loong.mnote.common.constants.CacheConstants;
import com.loong.mnote.common.constants.SystemConstant;
import com.loong.mnote.common.constants.UserConstants;
import com.loong.mnote.common.domain.BaseQuery;
import com.loong.mnote.common.enums.RegisterTypeEnum;
import com.loong.mnote.common.enums.UserSexEnum;
import com.loong.mnote.common.enums.UserStatusEnum;
import com.loong.mnote.common.enums.UserTypeEnum;
import com.loong.mnote.common.exception.BizException;
import com.loong.mnote.common.util.MD5Utils;
import com.loong.mnote.dal.domain.User;
import com.loong.mnote.dal.param.UserRegisterParam;
import com.loong.mnote.dal.query.UserQuery;
import com.loong.mnote.service.component.cache.CacheService;
import com.loong.mnote.service.component.cache.StringCacheService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 用户服务类
 *
 * @author: sam
 * @date: 2018-12-19 16:05
 */
@Service
public class UserService extends AbstractBizService<User> {

    @Autowired
    private CacheService cacheService;

    @Autowired
    private StringCacheService stringCacheService;

//    @Autowired
//    private RedisTemplate redisTemplate;
//
//    @Autowired
//    private StringRedisTemplate stringRedisTemplate;


    @Override
    public List<Predicate> getPredicates(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder, BaseQuery q) {
        UserQuery userQuery = (UserQuery) q;
        List<Predicate> predicates = new ArrayList<>();
        if (userQuery.getDelState() != null) {
            predicates.add(criteriaBuilder.equal(root.get("delState"), userQuery.getDelState()));
        }
        if (StringUtils.isNotEmpty(userQuery.getAccountNo())) {
            predicates.add(criteriaBuilder.equal(root.get("accountNo"), userQuery.getAccountNo()));
        }
        if (StringUtils.isNotEmpty(userQuery.getAreaCode())) {
            predicates.add(criteriaBuilder.equal(root.get("areaCode"), userQuery.getAreaCode()));
        }
        if (StringUtils.isNotEmpty(userQuery.getMobile())) {
            predicates.add(criteriaBuilder.equal(root.get("mobile"), userQuery.getMobile()));
        }
        if (StringUtils.isNotEmpty(userQuery.getEmail())) {
            predicates.add(criteriaBuilder.equal(root.get("email"), userQuery.getEmail()));
        }
        if (userQuery.getType() != null) {
            predicates.add(criteriaBuilder.equal(root.get("type"), userQuery.getType()));
        }
        if (StringUtils.isNotEmpty(userQuery.getLocal())) {
            predicates.add(criteriaBuilder.equal(root.get("local"), userQuery.getLocal()));
        }
//        if (StringUtils.isNotEmpty(appDeviceQuery.getAppName())) {
//            predicates.add(criteriaBuilder.equal(root.get("appName"), appDeviceQuery.getAppName()));
//        }
//        if (StringUtils.isNotEmpty(appDeviceQuery.getAppVersion())) {
//            predicates.add(criteriaBuilder.equal(root.get("appVersion"), appDeviceQuery.getAppVersion()));
//        }
        return predicates;
    }


    public User findByTypeAndMobile(Integer type, String areaCode, String mobile) {
        UserQuery userQuery = new UserQuery();
        userQuery.setType(type);
        userQuery.setAreaCode(areaCode);
        userQuery.setMobile(mobile);
        return this.findOne(userQuery);
    }

    public User findByTypeAndEmail(Integer type, String email) {
        UserQuery userQuery = new UserQuery();
        userQuery.setType(type);
        userQuery.setEmail(email);
        return this.findOne(userQuery);

    }

    public User findByTypeAndMobile(Integer type, String mobile) {
        UserQuery userQuery = new UserQuery();
        userQuery.setType(type);
        userQuery.setMobile(mobile);
        return this.findOne(userQuery);

    }

    public User findByTypeAndAccountNo(Integer type, String accountNo) {
        UserQuery userQuery = new UserQuery();
        userQuery.setType(type);
        userQuery.setAccountNo(accountNo);
        return this.findOne(userQuery);

    }

    public User saveUser(User user) {
        //密码加密
        String newPwd = MD5Utils.MD5EncodeForPwd(UserConstants.PWD_KEY, user.getPwd());
        user.setPwd(newPwd);

        User savedUser = this.save(user);

        if (user.getType() == UserTypeEnum.MEMBER.getCode()) {
        }
        return savedUser;
    }

    public User register(UserRegisterParam userRegisterParam) {
        Integer registerType = userRegisterParam.getRegisterType();
        Integer userType = userRegisterParam.getUserType();
        String areaCode = userRegisterParam.getAreaCode();
        String mobile = userRegisterParam.getMobile();
        String email = userRegisterParam.getEmail();
        String checkCode = userRegisterParam.getCheckCode();
        String pwd = userRegisterParam.getPwd();
        //手机号码是否已被注册
        String accountNo = areaCode + mobile;
        User dbUser = null;
        if (registerType == RegisterTypeEnum.MOBILE.getCode()) {
            dbUser = this.findByTypeAndMobile(userType, areaCode, mobile);
        } else {
            dbUser = this.findByTypeAndEmail(userType, email);
            accountNo = email;
        }
        if (dbUser != null) {
            throw new BizException("手机号码已被注册");
        }

        //短信验证码是否正确
        String checkCodeKey = StringUtils.join(new Object[]{CacheConstants.USER_REGISTER_SMS, userType, accountNo}, "_");


        String cacheSmsCode = stringCacheService.get(checkCodeKey);
        if (StringUtils.isEmpty(cacheSmsCode)) {
            throw new BizException("验证码已失效，请重新获取");
        }

        if (!StringUtils.equals(cacheSmsCode, checkCode)) {
            throw new BizException("验证码错误，请重新输入");
        }

        //密码加密
        String newPwd = MD5Utils.MD5EncodeForPwd(UserConstants.PWD_KEY, pwd);

        //保存
        User user = new User();
        user.setAccountNo(accountNo);  //默认账号，可以修改，必须保证唯一性
        user.setType(userType);
        user.setAreaCode(areaCode);
        user.setMobile(mobile);
        user.setEmail(email);
        user.setNickname(accountNo);
        user.setPwd(newPwd);
        user.setRole("1");  //根据用户类型来 TODO
        user.setSex(UserSexEnum.MALE.getCode());
        user.setStatus(UserStatusEnum.PASS.getCode());
        user.setUserName(accountNo);
//        user.setAvatar(+mobile);  //默认图片 TODO

        return this.saveUser(user);
    }

    public User getLoginUser(HttpServletRequest request) {
        String token = request.getHeader(SystemConstant.TOKEN);
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        String tokenCacheKey = StringUtils.join(new Object[]{CacheConstants.USER_TOKEN, token}, "_");
        Object cacheUser = cacheService.get(tokenCacheKey);
        if (cacheUser != null) {
            return (User) cacheUser;
        } else {
            return null;
        }
    }


    public void updatePwd(Long userId, String oldPwd, String newPwd) {
        User user = this.findById(userId);
        //密码加密
        String encodePwd = MD5Utils.MD5EncodeForPwd(UserConstants.PWD_KEY, oldPwd);
        if (!StringUtils.equals(encodePwd, user.getPwd())) {
            throw new BizException("原始密码错误，请重新输入");
        }
        //密码加密
        String encodeNewPwd = MD5Utils.MD5EncodeForPwd(UserConstants.PWD_KEY, newPwd);

        //修改
        user.setPwd(encodeNewPwd);
        this.save(user);
    }

    public void updateForgetLoginPwdBySmsCode(Integer userType, String areaCode, String mobile, String smsCode, String newPwd) {
        User user = this.findByTypeAndMobile(userType, areaCode, mobile);
        if (user == null) {
            throw new BizException("该用户没有注册");
        }
        //短信验证码是否正确
        String smsCodeKey = StringUtils.join(new Object[]{CacheConstants.USER_FORGET_PWD_SMS, userType, areaCode + mobile}, "_");

        String cacheSmsCode = stringCacheService.get(smsCodeKey);
        if (StringUtils.isEmpty(cacheSmsCode)) {
            throw new BizException("验证码已失效，请重新获取");
        }

        if (!StringUtils.equals(cacheSmsCode, smsCode)) {
            throw new BizException("验证码错误，请重新输入");
        }
        //密码加密
        String encodePwd = MD5Utils.MD5EncodeForPwd(UserConstants.PWD_KEY, newPwd);

        //修改
        user.setPwd(encodePwd);
        this.save(user);
    }

    public void updateForgetLoginPwdByEmailCode(Integer userType, String email, String smsCode, String newPwd) {
        User user = this.findByTypeAndEmail(userType, email);
        if (user == null) {
            throw new BizException("该用户没有注册");
        }
        //短信验证码是否正确
        String smsCodeKey = StringUtils.join(new Object[]{CacheConstants.USER_FORGET_PWD_SMS, userType, email}, "_");

        String cacheSmsCode = stringCacheService.get(smsCodeKey);
        if (StringUtils.isEmpty(cacheSmsCode)) {
            throw new BizException("验证码已失效，请重新获取");
        }

        if (!StringUtils.equals(cacheSmsCode, smsCode)) {
            throw new BizException("验证码错误，请重新输入");
        }
        //密码加密
        String encodePwd = MD5Utils.MD5EncodeForPwd(UserConstants.PWD_KEY, newPwd);

        //修改
        user.setPwd(encodePwd);
        this.save(user);
    }

    public void updateMobile(Long userId, String areaCode, String mobile, String smsCode) {
        User user = this.findById(userId);
        if (user == null) {
            throw new BizException("该用户没有注册");
        }
        //短信验证码是否正确
        String smsCodeKey = StringUtils.join(new Object[]{CacheConstants.USER_CHANGE_MOBILE_SMS, user.getType(), areaCode + mobile}, "_");

        String cacheSmsCode = stringCacheService.get(smsCodeKey);
        if (StringUtils.isEmpty(cacheSmsCode)) {
            throw new BizException("验证码已失效，请重新获取");
        }

        if (!StringUtils.equals(cacheSmsCode, smsCode)) {
            throw new BizException("验证码错误，请重新输入");
        }

        //修改
        user.setMobile(mobile);
        this.save(user);
    }


    public void updateEmail(Long userId, String email, String smsCode) {
        User user = this.findById(userId);
        if (user == null) {
            throw new BizException("该用户没有注册");
        }
        //短信验证码是否正确
        String smsCodeKey = StringUtils.join(new Object[]{CacheConstants.USER_CHANGE_MOBILE_SMS, user.getType(), email}, "_");

        String cacheSmsCode = stringCacheService.get(smsCodeKey);
        if (StringUtils.isEmpty(cacheSmsCode)) {
            throw new BizException("验证码已失效，请重新获取");
        }

        if (!StringUtils.equals(cacheSmsCode, smsCode)) {
            throw new BizException("验证码错误，请重新输入");
        }

        //修改
        user.setEmail(email);
        this.save(user);
    }


    public String resetLoginToken(User user) {
        //剔除老的 token user 对象
        String userIdCacheKey = StringUtils.join(new Object[]{CacheConstants.USER_TOKEN, user.getId()}, "_");
        String oldToken = stringCacheService.get(userIdCacheKey);
        String oldTokenCacheKey = StringUtils.join(new Object[]{CacheConstants.USER_TOKEN, oldToken}, "_");
        cacheService.remove(oldTokenCacheKey);


        //set new token for user 对象
        String newToken = UUID.randomUUID().toString();
        stringCacheService.set(userIdCacheKey, newToken);
        String tokenCacheKey = StringUtils.join(new Object[]{CacheConstants.USER_TOKEN, newToken}, "_");
        cacheService.set(tokenCacheKey, user);
        return newToken;

    }


    public void clearLoginUser(HttpServletRequest request) {
        String token = request.getHeader(SystemConstant.TOKEN);
        if (StringUtils.isEmpty(token)) {
            return;
        }
        String tokenCacheKey = StringUtils.join(new Object[]{CacheConstants.USER_TOKEN, token}, "_");
        cacheService.remove(tokenCacheKey);
    }

    public User loginByPwd(Integer userType, String accountNo, String pwd) {
        User user = this.findByTypeAndAccountNo(userType, accountNo);
        if (user == null) {
            throw new BizException("账号不存在");
        }
        String newPwd = MD5Utils.MD5EncodeForPwd(UserConstants.PWD_KEY, pwd);
        if (!user.getPwd().equals(newPwd)) {
            throw new BizException("密码不正确");
        }
        return user;
    }

    public User loginByPwd(Integer userType, String accountNo, String pwd, String imgCode, String deviceId) {
        User user = this.findByTypeAndAccountNo(userType, accountNo);
        if (user == null) {
            throw new BizException("账号不存在");
        }
        String newPwd = MD5Utils.MD5EncodeForPwd(UserConstants.PWD_KEY, pwd);
        if (!user.getPwd().equals(newPwd)) {
            throw new BizException("密码不正确");
        }

        //图形验证码是否正确
        String checkCodeKey = StringUtils.join(new Object[]{CacheConstants.USER_LOGIN_SMS, userType, deviceId}, "_");

        String cacheCheckCode = stringCacheService.get(checkCodeKey);
        if (StringUtils.isEmpty(cacheCheckCode)) {
            throw new BizException("验证码已失效，请重新获取");
        }

        if (!StringUtils.equals(cacheCheckCode, imgCode)) {
            throw new BizException("验证码错误，请重新输入");
        }
        return user;
    }

    public User loginBySmsCode(Integer userType, String areaCode, String mobile, String smsCode) {
        User dbUser = this.findByTypeAndMobile(userType, areaCode, mobile);
        if (dbUser == null) {
            throw new BizException("用户不存在");
        }

        //短信验证码是否正确
        String checkCodeKey = StringUtils.join(new Object[]{CacheConstants.USER_LOGIN_SMS, userType, areaCode + mobile}, "_");

        String cacheCheckCode = stringCacheService.get(checkCodeKey);
        if (StringUtils.isEmpty(cacheCheckCode)) {
            throw new BizException("验证码已失效，请重新获取");
        }

        if (!StringUtils.equals(cacheCheckCode, smsCode)) {
            throw new BizException("验证码错误，请重新输入");
        }
        return dbUser;
    }


    public User loginByEmailCode(Integer userType, String email, String emailCode) {
        User dbUser = this.findByTypeAndEmail(userType, email);
        if (dbUser == null) {
            throw new BizException("用户不存在");
        }

        //短信验证码是否正确
        String checkCodeKey = StringUtils.join(new Object[]{CacheConstants.USER_LOGIN_SMS, userType, email}, "_");

        String cacheCheckCode = stringCacheService.get(checkCodeKey);
        if (StringUtils.isEmpty(cacheCheckCode)) {
            throw new BizException("验证码已失效，请重新获取");
        }

        if (!StringUtils.equals(cacheCheckCode, emailCode)) {
            throw new BizException("验证码错误，请重新输入");
        }
        return dbUser;
    }

    public User loginByWechat(Integer userType, String openId, String areaCode, String mobile, String smsCode, String avatar) {
        return null;
    }

    public User loginByFacebook(Integer userType, String openId, String areaCode, String mobile, String smsCode, String avatar) {
        return null;
    }

}
