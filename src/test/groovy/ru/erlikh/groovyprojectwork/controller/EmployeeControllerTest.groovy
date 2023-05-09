package ru.erlikh.groovyprojectwork.controller

import org.mockito.Mock
import org.springframework.http.HttpStatus
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get

class EmployeeControllerTest extends Specification {

    @Mock
    private EmployeeController employeeController

    private MockMvc mockController

    def setup() {
        employeeController = Mock(EmployeeController)
        this.mockController = MockMvcBuilders.standaloneSetup(employeeController).build()
    }

    void 'get all employees'() {
        given:
        def response = mockController.perform(get("/employees/all")).andReturn().response

        expect:
        response.status == HttpStatus.OK.value()
    }
}
