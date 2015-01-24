package com.ocdsoft.bacta.engine.buffer;

import java.nio.ByteBuffer;

/**
 * Created by kburkhardt on 1/24/15.
 *
 * The ByteBufferReadable interface represents objects that can deserialize from a {@link ByteBuffer}
 */
public interface ByteBufferReadable {

    /**
     * Reads object from a {@link ByteBuffer}'s byte buffer.
     *
     * @param buffer The message to which this object will be written.
     */
    void readFromBuffer(ByteBuffer buffer);
}
