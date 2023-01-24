package BigDecimalTest;

import java.math.BigDecimal;

public class BigDecimalTest {
    public static void main(String[] args) {
        //BigDecimal精度比double更高
        BigDecimal v1 = new BigDecimal(100L);
        BigDecimal v2 = new BigDecimal(200L);
        System.out.println(v1.add(v2));

    }
}
