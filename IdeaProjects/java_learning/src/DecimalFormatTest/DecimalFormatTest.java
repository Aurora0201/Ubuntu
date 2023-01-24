package DecimalFormatTest;

import java.text.DecimalFormat;

public class DecimalFormatTest {
    //DecimalFormat负责数字格式化
    public static void main(String[] args) {
        DecimalFormat df = new DecimalFormat("###,###.##");
        System.out.println(df.format(1231231123.45));
        // 1,231,231,123.45
        DecimalFormat df1 = new DecimalFormat("###,###.0000");
        System.out.println(df1.format(123.1));
        //123.1000
    }
}
