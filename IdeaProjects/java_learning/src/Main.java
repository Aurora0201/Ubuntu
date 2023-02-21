import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) throws Exception{
        Class<?> clazz = Class.forName("A");
        Method[] methods = clazz.getDeclaredMethods();
        Method[] declaredMethods = A.class.getDeclaredMethods();


    }
}

class A{
    public void doSome(String s, String s1) {
        System.out.println(s);
    }

    public String doOther() {
        return "123";
    }

    void insert(String s,String s1){
        System.out.println("123");
    }
    public void update(){
        System.out.println(123);
    }

}