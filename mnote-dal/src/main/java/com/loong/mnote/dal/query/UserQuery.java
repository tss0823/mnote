package com.loong.mnote.dal.query;

import com.loong.mnote.common.domain.BaseQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;

/**
 * @author: sam
 * @date: 2018-12-19 16:29
 */
@ApiModel
public class UserQuery extends BaseQuery {

    @ApiParam(value = "账号/用户名",example = "zs@mnote.com")
    private String accountNo;

    @ApiParam(value = "密码")
    private String pwd;

    @ApiParam(value = "昵称")
    private String nickname;

    @ApiParam(value = "类型")
    private Integer type;

    @ApiParam(value = "状态")
    private Integer status;


    @ApiParam(value = "国家区号")
    private String areaCode;

    @ApiParam(value = "手机")
    private String mobile;

    @ApiParam(value = "邮件")
    private String email;

    @ApiParam(value = "地区语言")
    private String local;


    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }
}
