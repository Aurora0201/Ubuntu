package InnerClass;

public class InnerClassTest {
    public static void main(String[] args) {
        new MyMath().sum(new Compute() {
            @Override
            public int sum(int a, int b) {
                return a + b;
            }
        }, 10, 20);
    }
}

interface Compute{
    int sum(int a, int b);
}

class MyMath{
    public void sum(Compute c, int a, int b){
        System.out.println(c.sum(a, b));
    }
}