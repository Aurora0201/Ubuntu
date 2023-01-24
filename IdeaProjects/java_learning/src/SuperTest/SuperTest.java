package SuperTest;

public class SuperTest {
    public static void main(String[] args) {
        B a = new B();
        a.m1();

        A a1 = new A();
        a1.m();
    }
}

class A{
    public void m(){
        System.out.println(toString());
    }
}

class B extends A{
    public void m1(){
        super.m();
    }
}