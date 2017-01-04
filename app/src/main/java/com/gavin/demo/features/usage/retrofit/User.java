package com.gavin.demo.features.usage.retrofit;

import com.google.gson.annotations.Expose;

/**
 * 这里是萌萌哒注释君
 *
 * @author gavin.xiong 2016/12/5  2016/12/5
 */
public class User {

    @Expose
    private String id;
    @Expose(serialize = false)
    private String phone;
    @Expose(deserialize = false)
    private String nickName;
    @Expose
    private transient String headPic;
    @Expose(serialize = false, deserialize = false)
    private int sex;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", nickName='" + nickName + '\'' +
                ", headPic='" + headPic + '\'' +
                ", sex=" + sex +
                '}';
    }
}
