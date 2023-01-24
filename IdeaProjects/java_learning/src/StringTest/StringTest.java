package StringTest;

public class StringTest {

    public static void main(String[] args) {
        byte[] bytes = {97,98,99};
        String c = new String(bytes);
        String d = new String(bytes,1,2);
        String e = "abcd";
        System.out.println(c.charAt(1));
        System.out.println(c.compareTo(e));
        System.out.println(e.contains(c));
        String f = "aaaaaab";

        System.out.println(f.replace('a','f'));
        System.out.println(f.substring(1,4));
        System.out.println(f.toUpperCase());
        char[] chars = {'1','a','2'};
//        System.out.println(String.valueOf(chars));
        System.out.println(new String(chars));
        String sql = "123";
        sql = "312";
        System.out.println(sql);
    }

}
