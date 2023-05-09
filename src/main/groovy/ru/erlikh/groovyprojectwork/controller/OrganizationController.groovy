package ru.erlikh.groovyprojectwork.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.ModelAndView
import ru.erlikh.groovyprojectwork.service.dtoServices.OrganizationDtoService

@RestController
class OrganizationController {

    @Autowired
    private OrganizationDtoService organizationDtoService

    @GetMapping(value = "/organization/all")
    ModelAndView allOrganizations() {
        ModelAndView modelAndView = new ModelAndView()
        modelAndView.setViewName("/organization")
        modelAndView.addObject("organizations", organizationDtoService.findAll())
        return modelAndView;
    }

    @GetMapping(value = "/organization/update")
    ModelAndView updateOrganizations() {
        ModelAndView modelAndView = new ModelAndView()
        organizationDtoService.updateData()
        modelAndView.setViewName("/organization")
        modelAndView.addObject("organizations", organizationDtoService.findAll())
        return modelAndView;
    }
}
