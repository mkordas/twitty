package com.michalkordas.code.challenge

import spock.lang.Specification

class UserSpec extends Specification {

    def "has a name"() {
        given:
        String sampleName = "Name"

        expect:
        new User(sampleName).name = sampleName
    }
}
