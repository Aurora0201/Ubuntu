package AccessTest;

import Hotel.Test;

public class AccessTest {

    /*
    * 从下面的例子可以发现，java中传入的是值
    * 传入函数的对象和外面的对象是同一个对象，在函数内对对象的操作会改变对象的值
    * 至始至终只有一个对象被操作
    * */
    public static void main(String[] args) {
        Test test = new Test();
        System.out.println(test);
        m1(test);
    }

    public static void m1(Test test) {
        System.out.println(test);
    }
}
