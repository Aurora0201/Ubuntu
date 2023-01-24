package InterfaceTest;

public class MutiInterface {
    public static void main(String[] args) {
        E e = new G();
        F f = (F)e;
        f.m2();
    }
}
interface E{
    void m1();
}
interface F{
    void m2();
}
class G implements E,F{
    @Override
    public void m1() {
        System.out.println("m1...");
    }

    @Override
    public void m2() {
        System.out.println("m2...");
    }
}