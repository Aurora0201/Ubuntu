package org.example;
import org.junit.Test;
import top.pi1grim.lambda.entity.ReturnVO;

/**
 * Unit test for simple App.
 */
public class AppTest{
    @Test
    public void statementTest() {
        ReturnVO.builder()
                .code(10)
                .message("连接成功")
                .build();
    }
}
