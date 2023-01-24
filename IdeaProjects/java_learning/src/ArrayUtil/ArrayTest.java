package ArrayUtil;

import java.util.Arrays;
import java.util.Collections;

public class ArrayTest {
    static int[] a = {1,23,2,5,4};

    public static void main(String[] args) {
        Arrays.sort(a);
//        for(int i: a) System.out.println(i);
        System.out.println(
                Arrays.binarySearch(a,23)
        );

    }
}

class Test{
    public static void main(String[] args) {
        int[] a = {4,23,12,5};
        sort(a);
        for(int i: a) System.out.println(i);
    }

    /**
     * 即使是传入对象，也会被改变
     * @param a 数组参数
     */
    public static void sort(int[] a){
        Arrays.sort(a);
    }
}
