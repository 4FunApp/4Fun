package com.joker.fourfun.model;

/**
 * Created by joker on 2016/12/30.
 */

public class LoginInfo {
    /**
     * error : false
     * result : [{"userName":"1","password":"1","sex":0,"code":1051}]
     */
    /**
     * userName : 1
     * password : 1
     * sex : 0
     * code : 1051
     */
    private String userName;
    private String password;
    private int sex;
    private int code;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}