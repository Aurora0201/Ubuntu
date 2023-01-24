package OverrideTest.toString;

public class MyTime {
    private int year;
    private int month;
    private int day;

    public MyTime(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    @Override
    public String toString() {
        return year + "年" + month + "月" + day + "日";
    }
}

class Test{
    public static void main(String[] args) {
        System.out.println(new MyTime(2002,2,1));
    }
}