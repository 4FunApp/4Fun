package com.joker.fourfun.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by joker on 2016/12/30.
 */

public class User {
    @SerializedName("userName")
    private String username;
    private String password;
    private int sex;
    private String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
