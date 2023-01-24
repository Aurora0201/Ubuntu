package IntegerTest;

/**
 *
 */
public class IntegerTest {
    public static void main(String[] args) {
        Integer integer = 1;//自动装箱
        System.out.println(Integer.MAX_VALUE);
        System.out.println(integer == 1); //自动拆箱

         // 不同类型的转换

        System.out.println(Integer.parseInt("1234"));
        System.out.println(Double.parseDouble("3.1415"));

        System.out.println(Integer.toBinaryString(7));
        System.out.println(Integer.toHexString(15));
        System.out.println(Integer.toOctalString(8));

        //String -> int
        Integer.parseInt("123");
        //int -> String
        String a = 1011 + "";
        System.out.println(a);
        
    }
}
