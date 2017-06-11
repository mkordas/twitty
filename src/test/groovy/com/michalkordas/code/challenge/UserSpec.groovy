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

    def "cannot post message longer than 140 chars"() {
        when:
        new User("Talkative").postMessage("." * 141)

        then:
        IllegalArgumentException ex = thrown()
        ex.message == "You cannot post message over 140 chars"
    }
}
