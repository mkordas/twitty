package com.michalkordas.code.challenge.domain

import com.michalkordas.code.challenge.domain.Timeline
import com.michalkordas.code.challenge.domain.User
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
        timeline.getMessages() == [thirdMessage, secondMessage, firstMessage]
    }
}
