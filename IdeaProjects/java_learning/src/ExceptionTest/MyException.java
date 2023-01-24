package ExceptionTest;

public class MyException extends Exception{
    /**
     * 自定义异常，在业务开发中，jdk内置的异常肯定是不够我们使用的，所以JAVA允许程序员自定义异常
     * 如何自定义异常？
     * 1.编写一个类继承Exception类或者RunTimeException
     * 2.提供一个有String参数的构造方法，一个无参数的构造方法
     *
     * 如何选择继承哪个异常类
     * 1.如果发生异常概率较高，则选择Exception，否则选择RunTimeException
     */
    public MyException() {
        super();
    }

    public MyException(String message) {
        super(message);
    }
}

