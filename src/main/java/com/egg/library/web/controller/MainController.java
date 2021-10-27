package com.egg.library.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
    @GetMapping
    public ModelAndView inicio() {
        return new ModelAndView("index");
    }
}
