package top.pi1grim.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import top.pi1grim.config.SpringConfig;
import top.pi1grim.vo.Student;

public class springbootTest {
    @Test
    public void studentTest() {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        Student student = context.getBean("student", Student.class);
        System.out.println(student);
    }
}
