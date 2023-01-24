package ArrayTest;

public class ArrayTest02 {

    public static void main(String[] args) {
        int[][] a = {
                {1,2,3},
                {3,4,5},
                {4,5,6}
        };
        System.out.println(a.length + " " + a[0].length);
        for(int i = 0; i < a.length; i++)
            for (int j = 0; j < a[i].length; j++) System.out.print(a[i][j] + " ");
        int[] b = {1,2,3,4};
        int[] c = new int[6];
        System.arraycopy(b,0,c,0,b.length);
        for(int i: c) System.out.println(i);

    }
}
