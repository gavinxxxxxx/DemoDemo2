package com.gavin.demo.features.usage.retrofit;

import java.io.Serializable;

/**
 * 修改用户昵称请求体
 *
 * @author gavin.xiong 2016/12/5
 */
public class UpdateUserNameBody implements Serializable {

    private long uid;
    private String name;
    private String nickName;
    private String oldNickName;

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getOldNickName() {
        return oldNickName;
    }

    public void setOldNickName(String oldNickName) {
        this.oldNickName = oldNickName;
    }
}
