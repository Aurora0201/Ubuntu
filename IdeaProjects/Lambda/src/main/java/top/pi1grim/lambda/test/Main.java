package top.pi1grim.lambda.test;

import top.pi1grim.lambda.entity.ReturnVO;
import top.pi1grim.lambda.function.ReturnVOBuilder;
import top.pi1grim.lambda.type.Status;

public class Main {
    public static void main(String[] args) {
        ReturnVO returnVO = ReturnVOBuilder.build((code, msg) -> ReturnVO.builder()
                .code(code)
                .message(msg)
                .build());
//        System.out.println(returnVO);
        System.out.println(ReturnVO.getByStatus(Status.NO, "123123123"));
    }
}
