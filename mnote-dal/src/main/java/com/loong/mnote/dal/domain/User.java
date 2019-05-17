/*
 *
 *
 *
 *
 */

package com.loong.mnote.dal.domain;

import com.loong.mnote.common.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@ApiModel("用户")
@Entity(name = "user")
@Table(indexes = {@Index(name = "user_account_index", columnList = "type,accountNo", unique = true),
        @Index(name = "user_mobile_index", columnList = "type,areaCode,mobile", unique = true),
        @Index(name = "user_email_index", columnList = "type,email", unique = true),
})
public class User extends BaseEntity {


    @ApiModelProperty(notes = "账号/用户名")
    @Column(length = 50, nullable = false)
    private String accountNo;

    @ApiModelProperty(notes = "密码")
    @Column(length = 50, nullable = false)
    private String pwd;

    @ApiModelProperty(notes = "昵称")
    @Column(length = 50, nullable = false)
    private String nickname;

    @ApiModelProperty(notes = "类型")
    @Column(length = 4, nullable = false)
    private Integer type;

    @ApiModelProperty(notes = "状态")
    @Column(length = 4, nullable = false)
    private Integer status;


    @ApiModelProperty(notes = "国家区号")
    @Column(length = 30)
    private String areaCode;

    @ApiModelProperty(notes = "手机")
    @Column(length = 30)
    private String mobile;

    @ApiModelProperty(notes = "邮件")
    @Column(length = 50)
    private String email;

    @ApiModelProperty(notes = "性别")
    @Column(length = 1)
    private Integer sex;

    @ApiModelProperty(notes = "生日")
    @Column(length = 20)
    private String birthday;

    @ApiModelProperty(notes = "宣言")
    @Column(length = 200)
    private String message;

    @ApiModelProperty(notes = "头像")
    @Column(length = 200)
    private String avatar;


    @ApiModelProperty(notes = "描述")
    @Column(name = "[desc]", length = 1000)
    private String desc;

    @ApiModelProperty(notes = "支付密码")
    @Column(length = 50)
    private String payPwd;

    @ApiModelProperty(notes = "用户地址")
    @Column(length = 1000)
    private String address;


    @ApiModelProperty(notes = "用户姓名")
    @Column(length = 50)
    private String userName;

    @ApiModelProperty(notes = "角色")
    @Column(length = 200, nullable = false)
    private String role;

    @ApiModelProperty(notes = "信息标签")
    @Column(length = 200)
    private String otherInfoTag;


    @ApiModelProperty(notes = "职业")
    @Column(length = 50)
    private String career;


    @ApiModelProperty(notes = "邀请码")
    @Column(length = 50)
    private String inviteCode;

    @ApiModelProperty(notes = "别人的邀请码")
    @Column(length = 50)
    private String shareInviteCode;

    @Column(name="local",length = 16)
    @Field(type = FieldType.Text)
    private String local;

    public User() {
    }


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

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public String getShareInviteCode() {
        return shareInviteCode;
    }

    public void setShareInviteCode(String shareInviteCode) {
        this.shareInviteCode = shareInviteCode;
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