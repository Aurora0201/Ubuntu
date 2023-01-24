package RandomTest;

import java.util.Arrays;
import java.util.Random;

public class RandomTest {
    public static void main(String[] args) {
        Random random = new Random();
        //产生一个int类型的随机数
        System.out.println(random.nextInt());
        System.out.println(random.nextLong());
    }
}

class HomeWork{
    public static void main(String[] args) {
        int[] a = new int[5];
        int idx = 0;

        while(idx < 5){
            Random random = new Random();
            int value = random.nextInt(10);
            if(!contains(a, idx, value))a[idx++] = value;
        }
        for(int i: a) System.out.print(i + " ");
    }
    public static boolean contains(int[] a, int size, int value){
        if(size == 0)return false;
        Arrays.sort(a,0,size);
        return Arrays.binarySearch(a,0,size,value) >= 0;
        /*        Arrays.sort(a);
        return Arrays.binarySearch(a,0,size,value) >= 0;*/
    }
}
