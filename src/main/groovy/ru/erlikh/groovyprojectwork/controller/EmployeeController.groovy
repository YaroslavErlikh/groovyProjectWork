package ru.erlikh.groovyprojectwork.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.ModelAndView
import ru.erlikh.groovyprojectwork.service.dtoServices.EmploymentDtoService

@RestController
class EmployeeController {

    @Autowired
    private EmploymentDtoService employmentDtoService

    @GetMapping(value = "/employees/all")
    ModelAndView allEmployees() {
        ModelAndView modelAndView = new ModelAndView()
        modelAndView.setViewName("/employees")
        modelAndView.addObject("employees", employmentDtoService.findAll())
        return modelAndView;
    }

    @GetMapping(value = "/employees/update")
    ModelAndView updateEmployees() {
        ModelAndView modelAndView = new ModelAndView()
        employmentDtoService.updateData()
        modelAndView.setViewName("/employees")
        modelAndView.addObject("employees", employmentDtoService.findAll())
        return modelAndView;
    }
}
