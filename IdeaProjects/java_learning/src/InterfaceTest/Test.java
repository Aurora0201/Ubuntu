package InterfaceTest;

public class Test {
    public static void main(String[] args) {
        C c = new D();
        System.out.println(c.sum(1,2));
    }
}
interface A{
    double PI = 3.1415;
}

interface B {
    int N = (int)1e5;
}

interface C extends A,B{
    int sum(int a, int b);
    int sub(int a, int b);
}

class D implements C{
    @Override
    public int sum(int a, int b) {
        return a + b;
    }

    @Override
    public int sub(int a, int b) {
        return a - b;
    }
}