package com.loong.mnote.boss.controller;


import com.loong.mnote.common.domain.Pager;
import com.loong.mnote.common.domain.WebResult;
import com.loong.mnote.common.enums.UserTypeEnum;
import com.loong.mnote.common.web.WebResultBuilder;
import com.loong.mnote.dal.domain.User;
import com.loong.mnote.dal.query.UserQuery;
import com.loong.mnote.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author: sam
 * @date: 2018-12-19 12:02
 */
@RestController
@RequestMapping(path = "/member/")
@Api( description="会员管理 (author:Sam)")
@Validated
public class MemberController {

    @Autowired
    private UserService userService;


    @ApiOperation(value = "会员列表",nickname = "sam@mnote.com",response = WebResult.class)
    @GetMapping(path = "list",produces = "application/json")
    public Object list(UserQuery query) {
        query.setType(UserTypeEnum.MEMBER.getCode());
        Pager<User> page = userService.findByPage(query);
        return WebResultBuilder.successResult(page);
    }


    @ApiOperation(value = "会员详情",nickname = "sam@mnote.com",response = WebResult.class)
    @GetMapping(path = "detail",produces = "application/json")
    public Object detail(@ApiParam(value = "用户ID", required = true, example = "1")  @RequestParam Long id) {
        User user = userService.findById(id);
        return WebResultBuilder.successResult(user);
    }

}
