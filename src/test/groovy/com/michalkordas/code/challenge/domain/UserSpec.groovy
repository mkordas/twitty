package com.michalkordas.code.challenge.domain

import com.michalkordas.code.challenge.domain.User
import spock.lang.Specification

class UserSpec extends Specification {

    def "has a name"() {
        given:
        String sampleName = "Name"

        expect:
        new User(sampleName).name() == sampleName
    }

    def "can post a message on a wall"() {
        given:
        User user = new User("User")
        String message = "some short message"

        when:
        user.postMessage(message)

        then:
        user.wall().getMessages().first().getContents() == message
    }

    def "cannot post message longer than 140 chars"() {
        when:
        new User('Talkative').postMessage("." * 141)

        then:
        IllegalArgumentException ex = thrown()
        ex.message == 'You cannot post message over 140 chars'
    }

    def "can follow another user"() {
        given:
        User user = new User('First')

        when:
        user.follow(new User('Second'))

        then:
        noExceptionThrown()
    }

    def "can see a timeline"() {
        given:
        User user = new User('Me')
        User friend = new User('Friend')
        user.follow(friend)
        String message = 'any message'

        when:
        friend.postMessage(message)

        then:
        user.timeline().getMessages().first() == message
    }
}
