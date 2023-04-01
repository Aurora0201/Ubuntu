package top.pi1grim.lambda.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status {
    OK(10, "连接成功"), NO(15, "连接失败");
    private final int code;
    private final String massage;
}
