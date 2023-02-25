package com.java.framework.bean;

public class Student {
    private Integer sid;
    private String sname;
    private Clazz clazz;

    public String retClazz() {
        return clazz + "123";
    }

    @Override
    public String toString() {
        return "Student{" +
                "sid=" + sid +
                ", sname='" + sname + '\'' +
                ", clazz=" + clazz +
                '}';
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public void setClazz(Clazz clazz) {
        this.clazz = clazz;
    }

    public Integer getSid() {
        return sid;
    }

    public String getSname() {
        return sname;
    }

    public Clazz getClazz() {
        return clazz;
    }

    public Student(Integer sid, String sname, Clazz clazz) {
        this.sid = sid;
        this.sname = sname;
        this.clazz = clazz;
    }

    public Student() {
    }
}
