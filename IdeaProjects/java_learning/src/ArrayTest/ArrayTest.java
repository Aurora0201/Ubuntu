package ArrayTest;

public class ArrayTest {

    public static void main(String[] args) {
        int[] a = {1,2,3,4};
        int[] b = new int[5];
        System.out.println(a[1]);
//        b[0] = 1;
        for(int i : a) System.out.println(i);
        for(int i = 0; i < b.length; i++)b[i] = i;

    }
}
