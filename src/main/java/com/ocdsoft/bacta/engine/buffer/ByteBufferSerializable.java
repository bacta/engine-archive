package com.ocdsoft.bacta.engine.buffer;

import io.netty.buffer.ByteBuf;

import java.io.Serializable;
import java.nio.ByteBuffer;

/**I t
 * The BactaBufferWritable interface represents an object that can be serialized into a {@link ByteBuf}.
 */
public interface ByteBufferSerializable extends Serializable {

    /**
     * Writes the object to a {@link ByteBuf}'s byte buffer.
     *
     * @param buffer The message to which this object will be written.
     */
    void writeToBuffer(ByteBuffer buffer);

    /**
     * Reads the object data from the provided buffer
     * @param buffer The message to which this object will be read from.
     */
    void readFromBuffer(ByteBuffer buffer);
}
