package com.java.framework.bean;

import java.util.List;

public class Clazz {
    private Integer cid;
    private String cname;
    private List<Student> stus;

    @Override
    public String toString() {
        return "Clazz{" +
                "cid=" + cid +
                ", cname='" + cname + '\'' +
                ", stus=" + stus +
                '}';
    }

    public void setStus(List<Student> stus) {
        this.stus = stus;
    }

    public List<Student> getStus() {
        return stus;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public Integer getCid() {
        return cid;
    }

    public String getCname() {
        return cname;
    }

    public Clazz(Integer cid, String cname) {
        this.cid = cid;
        this.cname = cname;
    }

    public Clazz() {
    }
}
