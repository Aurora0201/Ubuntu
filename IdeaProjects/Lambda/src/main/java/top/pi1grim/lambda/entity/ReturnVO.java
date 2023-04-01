package top.pi1grim.lambda.entity;

import lombok.Builder;
import lombok.Data;
import top.pi1grim.lambda.type.Status;

@Data
@Builder
public class ReturnVO {
    private int code;
    private String message;
    private Object data;
    public static ReturnVO getByStatus(Status status, Object data) {
        return builder()
                .code(status.getCode())
                .message(status.getMassage())
                .data(data)
                .build();
    }
}
