package top.pi1grim.model;

import lombok.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Data
@Component
public class User {
    private Integer id;
    private String actno;
    private Double balance;
}
