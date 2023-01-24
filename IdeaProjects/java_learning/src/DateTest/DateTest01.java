package DateTest;

import java.util.Date;

public class DateTest01 {
    public static void main(String[] args) {
        //获取自1970年1月1日 0时0分0秒 到现在的时间过去的总毫秒数
        long nowTime = System.currentTimeMillis();
        System.out.println(nowTime);

        long start = System.currentTimeMillis();
        for(int i = 0; i < 1000; i++) System.out.println("i = " + i);
        long end = System.currentTimeMillis();
        System.out.println("执行耗费" + (end - start) + "毫秒");
    }
}

class DateTest02{
    public static void main(String[] args) {
        //使用毫秒的构造方法
        Date lastYear = new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 24 * 365L);
        System.out.println(lastYear);

    }
}