package com.jite.pay.model.entity;

/** 商户端的用户信息描述 */
public class UserInfo {
    /**
     * 商户端的用户唯一ID
     */
    private String userId;

    /**
     * 商户端的用户名
     */
    private String userName;

    /**
     * 商户端的用户昵称
     */
    private String userNickname;

    /**
     * 商户端的手机号
     */
    private String userMobile;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }
}
