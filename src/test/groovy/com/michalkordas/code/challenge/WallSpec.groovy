package com.michalkordas.code.challenge

import spock.lang.Specification

class WallSpec extends Specification {

    def "displays posts in reverse chronological way"() {
        given:
        Wall wall = new Wall()
        String first = 'first'
        String second = 'second'

        when:
        wall.add(first)
        wall.add(second)

        then:
        wall.getMessages().collect { it.getContents() } == [second, first]
    }
}
