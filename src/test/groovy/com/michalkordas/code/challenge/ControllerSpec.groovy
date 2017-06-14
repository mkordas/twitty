package com.michalkordas.code.challenge

import groovy.json.JsonSlurper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.mock.web.MockHttpServletResponse
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post

@SpringBootTest
@AutoConfigureMockMvc
class ControllerSpec extends Specification {

    @Autowired
    private MockMvc mvc

    def "returns empty wall for a new user"() {
        when:
        def response = perform(get('/john/wall'))

        then:
        json(response).messages == []
    }

    def "returns a wall a with message when it was posted"() {
        given:
        String message = 'sample message'
        perform(post('/andrew/message').content(message))

        when:
        def response = perform(get('/andrew/wall'))

        then:
        json(response).messages == [[contents: message]]
    }

    private perform(MockHttpServletRequestBuilder request) {
        mvc.perform(request).andReturn().response
    }

    private static json(MockHttpServletResponse response) {
        new JsonSlurper().parseText(response.contentAsString)
    }
}
