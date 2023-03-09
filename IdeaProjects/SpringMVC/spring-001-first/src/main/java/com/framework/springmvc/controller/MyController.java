package com.framework.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class MyController{
    @RequestMapping("/some.do")
    public ModelAndView doSome(@RequestParam("rname") String name) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("msg","Welcome to use springmvc");
        modelAndView.addObject("name",name);
        modelAndView.setViewName("show");
        return modelAndView;
    }

    @RequestMapping(value = "/other.do", method = RequestMethod.GET)
    public String doOther() {
        return "show1";
    }

}
