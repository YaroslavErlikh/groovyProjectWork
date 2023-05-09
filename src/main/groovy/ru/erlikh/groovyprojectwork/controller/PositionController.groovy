package ru.erlikh.groovyprojectwork.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.ModelAndView
import ru.erlikh.groovyprojectwork.service.dtoServices.PositionDtoService

@RestController
@RequestMapping(value = "/position")
class PositionController {

    @Autowired
    private PositionDtoService positionDtoService

    @GetMapping(value = "/all")
    ModelAndView allPositions() {
        ModelAndView modelAndView = new ModelAndView()
        modelAndView.setViewName("/position")
        modelAndView.addObject("positions", positionDtoService.findAll())
        return modelAndView;
    }

    @GetMapping(value = "/update")
    ModelAndView updatePositions() {
        ModelAndView modelAndView = new ModelAndView()
        positionDtoService.updateData()
        modelAndView.setViewName("/position")
        modelAndView.addObject("positions", positionDtoService.findAll())
        return modelAndView;
    }
}
