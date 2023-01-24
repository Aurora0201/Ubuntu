package PublicTest.PrivateTest.ProtectedTest;

public class A {
    public int d = 1;      //都可以访问
    protected int a = 1;   //只有同包和子类可以访问
    int b = 1;             //只有同包可以访问
    private int c = 1;     //只有本类可以访问
}
