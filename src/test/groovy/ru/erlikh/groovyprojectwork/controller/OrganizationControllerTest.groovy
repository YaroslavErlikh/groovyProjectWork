package ru.erlikh.groovyprojectwork.controller

import org.mockito.Mock
import org.springframework.http.HttpStatus
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get

class OrganizationControllerTest extends Specification {

    @Mock
    private OrganizationController organizationController

    private MockMvc mockController

    def setup() {
        organizationController = Mock(OrganizationController)
        this.mockController = MockMvcBuilders.standaloneSetup(organizationController).build()
    }

    void 'get all organizations'() {
        given:
        def response = mockController.perform(get("/organization/all")).andReturn().response

        expect:
        response.status == HttpStatus.OK.value()
    }
}
