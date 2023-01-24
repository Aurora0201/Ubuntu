import java.util.Collection;

public class Main {
    /**
     *
     */
    public static final double PI = 3.1415;
    
    public static void main(String[] args){
        String s1 = "123";
        String s2 = "123";
        String s3 = new String(s1);
        System.out.println(s1 == s2);
        System.out.println(s1 == s3);



    }
}


