package com.example.addressbook.bean;

import androidx.annotation.NonNull;

public class PeoBean {
    private String id;
    private String name;//姓名
    private String num;//手机号
    private String sex;//性别
    private String remark;//备注
    private String beginZ;//字母

    public PeoBean() {
    }

    @NonNull
    @Override
    public String toString() {
        return "PeoBean{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", num='" + num + '\'' +
                ", sex='" + sex + '\'' +
                ", remark='" + remark + '\'' +
                ", beginZ='" + beginZ + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getBeginZ() {
        return beginZ;
    }

    public void setBeginZ(String beginZ) {
        this.beginZ = beginZ;
    }

    public PeoBean(String id, String name, String num, String sex, String remark, String beginZ) {
        this.id = id;
        this.name = name;
        this.num = num;
        this.sex = sex;
        this.remark = remark;
        this.beginZ = beginZ;
    }

    public PeoBean(String id, String name, String num, String sex, String remark) {
        this.id = id;
        this.name = name;
        this.num = num;
        this.sex = sex;
        this.remark = remark;
    }
}
