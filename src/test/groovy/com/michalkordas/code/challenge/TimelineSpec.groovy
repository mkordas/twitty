package com.michalkordas.code.challenge

import spock.lang.Specification

class TimelineSpec extends Specification {

    def "returns messages in reverse chronological way"() {
        given:
        User firstUser = new User('First')
        User secondUser = new User('Second')
        Timeline timeline = new Timeline([firstUser, secondUser])
        String firstMessage = 'first'
        String secondMessage = 'second'
        String thirdMessage = 'third'

        secondUser.postMessage(firstMessage)
        firstUser.postMessage(secondMessage)
        secondUser.postMessage(thirdMessage)

        expect:
        timeline.messages() == [thirdMessage, secondMessage, firstMessage]
    }
}