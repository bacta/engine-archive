package com.ocdsoft.bacta.engine.utils

import spock.lang.Specification

import java.nio.ByteBuffer

/**
 * Created by kyle on 5/29/2016.
 */
class BufferUtilTest extends Specification {
    def "CombineBuffers"() {


        when:
        initialBuffer = BufferUtil.combineBuffers(initialBuffer, secondBuffer)

        then:
        noExceptionThrown()
        initialBuffer.position() == expectedPosition

        where:
        initialBuffer               |   secondBuffer             |   expectedPosition
        ByteBuffer.allocate(100)    |   ByteBuffer.allocate(100) |   100
        ByteBuffer.allocate(100)    |   ByteBuffer.allocate(200) |   300
        ByteBuffer.allocate(50)     |   ByteBuffer.allocate(500) |   550
    }
}
