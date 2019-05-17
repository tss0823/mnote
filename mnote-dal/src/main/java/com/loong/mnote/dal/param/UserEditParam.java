package com.loong.mnote.dal.param;

import com.loong.mnote.common.domain.BaseDomain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("用户编辑参数")
public class UserEditParam extends BaseDomain {


    @ApiModelProperty(notes = "账号/用户名")
    private String accountNo;

    @ApiModelProperty(notes = "密码")
    private String pwd;

    @ApiModelProperty(notes = "昵称")
    private String nickname;

    @ApiModelProperty(notes = "类型")
    private Integer type;

    @ApiModelProperty(notes = "状态")
    private Integer status;


    @ApiModelProperty(notes = "国家区号")
    private String areaCode;

    @ApiModelProperty(notes = "手机")
    private String mobile;

    @ApiModelProperty(notes = "邮件")
    private String email;

    @ApiModelProperty(notes = "性别")
    private Integer sex;

    @ApiModelProperty(notes = "生日")
    private String birthday;

    @ApiModelProperty(notes = "宣言")
    private String message;

    @ApiModelProperty(notes = "头像")
    private String avatar;


    @ApiModelProperty(notes = "描述")
    private String desc;

    @ApiModelProperty(notes = "支付密码")
    private String payPwd;

    @ApiModelProperty(notes = "用户地址")
    private String address;


    @ApiModelProperty(notes = "用户姓名")
    private String userName;

    @ApiModelProperty(notes = "角色")
    private String role;

    @ApiModelProperty(notes = "信息标签")
    private String otherInfoTag;


    @ApiModelProperty(notes = "职业")
    private String career;


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

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
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

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPayPwd() {
        return payPwd;
    }

    public void setPayPwd(String payPwd) {
        this.payPwd = payPwd;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getOtherInfoTag() {
        return otherInfoTag;
    }

    public void setOtherInfoTag(String otherInfoTag) {
        this.otherInfoTag = otherInfoTag;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }
}
