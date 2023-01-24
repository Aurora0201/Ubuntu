package StaticMethodTest;

public class StaticTest {
    /**
     * 静态方法，在类初始化时运行，当一个对象被创建时，会先执行静态方法，静态方法只会执行一次
     */
    static {
        System.out.println("Static Method");
    }

    /**
     * 构造方法，每次调用构造方法时都会执行，构造方法会在构造方法前执行
     */
    {
        System.out.println("final");
    }

    public StaticTest() {
        System.out.println("Constructor");
    }
}

class Test{
    public static void main(String[] args) {
        new StaticTest();
        new StaticTest();

    }
}
