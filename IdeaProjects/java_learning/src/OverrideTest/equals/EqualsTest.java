package OverrideTest.equals;

import java.util.Objects;

public class EqualsTest {
    public static void main(String[] args) {
        MyTime t1 = new MyTime(2000);
        MyTime t2 = new MyTime(2001);
        MyTime t3 = new MyTime(2000);
        System.out.println(t1.equals(t2));
        System.out.println(t1.equals(t3));
        System.out.println(t1);
    }
}

class MyTime {
    private int year;
    private int month;
    private int day;

    public MyTime(int year) {
        this(year, 2, 1);
    }

    public MyTime(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

/*
    @Override
    public String toString() {
        return year + "年" + month + "月" + day + "日";
    }
*/

    /**
     * 当年份一样返回true
     * @param obj
     * @return boolean
     */
    /*@Override
    public boolean equals(Object obj) {
        if(obj == null || obj instanceof MyTime)return false;
        MyTime t = (MyTime) obj;
        return t.year == year;
    }
    */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyTime myTime = (MyTime) o;
        return year == myTime.year && month == myTime.month && day == myTime.day;
    }

    @Override
    public String toString() {
        return "MyTime{" +
                "year=" + year +
                ", month=" + month +
                ", day=" + day +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, month, day);
    }
}