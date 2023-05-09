package ru.erlikh.groovyprojectwork.controller

import org.mockito.Mock
import org.springframework.http.HttpStatus
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get

class PositionControllerTest extends Specification {

    @Mock
    private PositionController positionController

    private MockMvc mockController

    def setup() {
        positionController = Mock(PositionController)
        this.mockController = MockMvcBuilders.standaloneSetup(positionController).build()
    }

    void 'get all positions'() {
        given:
        def response = mockController.perform(get("/position/all")).andReturn().response

        expect:
        response.status == HttpStatus.OK.value()
    }
}
