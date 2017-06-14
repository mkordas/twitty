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
class CodeChallengeControllerSpec extends Specification {

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

    def "returns an empty timeline for a user not following anyone"() {
        when:
        def response = perform(get('/anna/timeline'))

        then:
        json(response).messages == []
    }

    def "returns a timeline for a user"() {
        given:
        String aliceMessage = 'Alice message'
        perform(post('/alice/message').content(aliceMessage))
        perform(post('/anna/follow').content('alice'))

        when:
        def response = perform(get('/anna/timeline'))

        then:
        json(response).messages == [aliceMessage]
    }

    def "returns a complex timeline only for followed users"() {
        given:
        String kateMessage = 'Kate message'
        String patrickMessage = 'Patrick message'
        perform(post('/tom/follow').content('kate'))
        perform(post('/tom/follow').content('patrick'))
        perform(post('/patrick/message').content(patrickMessage))
        perform(post('/kate/message').content(kateMessage))
        perform(post('/tom/message').content('Tom message'))
        perform(post('/non-followed-user/message')
                .content('any message'))

        when:
        def response = perform(get('/tom/timeline'))

        then:
        json(response).messages == [kateMessage, patrickMessage]
    }

    private perform(MockHttpServletRequestBuilder request) {
        mvc.perform(request).andReturn().response
    }

    private static json(MockHttpServletResponse response) {
        new JsonSlurper().parseText(response.contentAsString)
    }
}
