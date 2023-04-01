package top.pi1grim.lambda.function;

import top.pi1grim.lambda.entity.ReturnVO;
import top.pi1grim.lambda.type.Status;

import java.util.function.Supplier;

public class ReturnVOBuilder {
    public static ReturnVO build(Provider provider) {
        return provider.provide(10, "成功");
    }
}
