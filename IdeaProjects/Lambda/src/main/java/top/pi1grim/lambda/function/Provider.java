package top.pi1grim.lambda.function;

import top.pi1grim.lambda.entity.ReturnVO;

import java.util.function.Consumer;

@FunctionalInterface
public interface Provider {
    ReturnVO provide(int code, String msg);
}
