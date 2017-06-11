package com.michalkordas.code.challenge

import spock.lang.Specification

class UserSpec extends Specification {

    def "has a name"() {
        given:
        String sampleName = "Name"

        expect:
        new User(sampleName).name() == sampleName
    }

    def "can post a message"() {
        given:
        User user = new User("User")

        when:
        user.postMessage("some short message")

        then:
        noExceptionThrown()
    }
}
