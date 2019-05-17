package com.loong.mnote.service.component;

import com.loong.mnote.common.enums.YesNoEnum;
import com.loong.mnote.common.exception.BizException;
import com.loong.mnote.common.util.MapUtils;
import com.loong.mnote.dal.domain.AuthRes;
import com.loong.mnote.dal.domain.RoleAuthRes;
import com.loong.mnote.dal.domain.User;
import com.loong.mnote.dal.query.AuthResQuery;
import com.loong.mnote.dal.query.RoleAuthResQuery;
import com.loong.mnote.service.*;
import com.loong.mnote.service.component.auth.AuthResObj;
import com.loong.mnote.service.component.auth.UserManager;
import com.loong.mnote.service.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: sam
 * @date: 2019-01-10 13:44
 */
@Component
public class AuthComponent extends AbstractService {

    @Value("${auth.check}")
    private boolean authCheck;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthResService authResService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleAuthResService roleAuthResService;

    @Autowired
    private SystemConfigComponent systemConfigComponent;

    private Map<Long, Set<AuthResObj>> roleAuthResMap = new HashMap<>();
    private Set<String> whiteUrls = new HashSet<>();


    @EventListener(ApplicationReadyEvent.class)
    public void initData(){
        roleAuthResMap.clear();
        //获取所有可用资源
        AuthResQuery authResQuery = new AuthResQuery();
        String appName = systemConfigComponent.getAppName();
        authResQuery.setAppType(appName);
        authResQuery.setStatus(YesNoEnum.YES.getCode());
        List<AuthRes> authResList = authResService.findAll(authResQuery);

        if (CollectionUtils.isEmpty(authResList)) {
            return;
        }
        Map<Long, AuthResObj> authResMap = authResList.stream().filter(x -> x.getAuthCheck()).collect(Collectors.toMap(AuthRes::getId, authRes -> {
            AuthResObj authResObj = new AuthResObj();
            authResObj.setCode(authRes.getCode());
            authResObj.setUrl(authRes.getUrl());
            return authResObj;
        }));

        whiteUrls = authResList.stream().filter(x -> !x.getAuthCheck()).map(x -> x.getUrl()).collect(Collectors.toSet());

        //手动加入
        whiteUrls.add("/tools/initData");
        whiteUrls.add("swagger-ui.html");
        whiteUrls.add("/liveStatus");
        whiteUrls.add("/readyStatus");
        whiteUrls.add("/redirectHttpStatus");


        //建立角色资源对应关系
        RoleAuthResQuery roleAuthResQuery = new RoleAuthResQuery();
        List<RoleAuthRes> roleAuthResList = roleAuthResService.findAll(roleAuthResQuery);
        if (CollectionUtils.isEmpty(roleAuthResList)) {
            return;
        }
        for (RoleAuthRes roleAuthRes : roleAuthResList) {
            Long roleId = roleAuthRes.getRoleId();
            Long authResId = roleAuthRes.getAuthResId();
            AuthResObj authResObj = authResMap.get(authResId);
            if(authResObj == null){
                continue;
            }
            MapUtils.putSetElement(roleAuthResMap,roleId,authResMap.get(authResId));
        }
    }

    public boolean checkAuth(HttpServletRequest request){
        //不要检查
        if(!authCheck){
            return true;
        }

        String requestURI = request.getRequestURI();
        if(whiteUrls.contains(requestURI)){
            return true;
        }

        //暂时默认get请求都通过,后续通过资源做标记，获取所有资源去匹配
//        if (request.getMethod().equalsIgnoreCase(HttpMethod.GET.toString())) {
//            return true;
//        }
        //现在都在数据中去标记
        User loginUser = userService.getLoginUser(request);
        if(loginUser == null){
            throw new BizException("请登录");
        }

        //将当前访问用户放入threadLocal
        UserManager.setLoginUser(loginUser);

        String role = loginUser.getRole();
        if (StringUtils.isEmpty(role)) {
            throw new BizException("user role is null");
        }
        String[] roleIds = role.split(",");
        for (String roleId : roleIds) {
            Set<AuthResObj> authResSet = roleAuthResMap.get(Long.valueOf(roleId));
            if(CollectionUtils.isEmpty(authResSet)){
                continue;
            }
            boolean isMatch = authResSet.stream().anyMatch(x -> StringUtils.equals(x.getUrl(),requestURI));
            if(isMatch){
                return true;
            }
        }
        return false;

    }


}
