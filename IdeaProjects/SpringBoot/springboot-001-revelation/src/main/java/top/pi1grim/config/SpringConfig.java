package top.pi1grim.config;

import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;
import top.pi1grim.vo.Tiger;

@Configuration
@ComponentScan("top.pi1grim")
@ImportResource("classpath:conf/spring.xml")
@PropertySource("classpath:conf/students.properties")
public class SpringConfig {
    @Bean
    public Tiger tiger() {
        Tiger tiger = new Tiger();
        tiger.setName("Ma ShiWen");
        tiger.setType("DongBei Hu");
        return tiger;
    }
}
