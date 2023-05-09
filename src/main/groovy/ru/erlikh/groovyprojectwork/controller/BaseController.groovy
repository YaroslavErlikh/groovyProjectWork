package ru.erlikh.groovyprojectwork.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.ModelAndView

@RestController(value = "/")
class BaseController {

    @GetMapping
    ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView()
        modelAndView.setViewName("index")
        return modelAndView
    }
}
