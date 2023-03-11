package top.pi1grim.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@ControllerAdvice
public class AndroidExceptionHandler {
    @ExceptionHandler(IOException.class)
    public ModelAndView IOException() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("error");
        return mv;
    }
}
