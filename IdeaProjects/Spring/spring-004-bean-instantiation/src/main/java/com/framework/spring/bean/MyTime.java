package com.framework.spring.bean;

import java.util.Date;

public class MyTime {
    private Date date;

    @Override
    public String toString() {
        return "MyTime{" +
                "date=" + date +
                '}';
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
