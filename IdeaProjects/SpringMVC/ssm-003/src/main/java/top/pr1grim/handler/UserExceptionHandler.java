package top.pr1grim.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import top.pr1grim.exception.NameErrorException;
import top.pr1grim.exception.UserException;

@ControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(NameErrorException.class)
    public ModelAndView nameException() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("error");
        return mv;
    }
}
