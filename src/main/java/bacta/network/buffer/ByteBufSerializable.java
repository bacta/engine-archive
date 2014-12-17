package bacta.network.buffer;

import io.netty.buffer.ByteBuf;

import java.io.Serializable;

/**
 * The BactaBufferWritable interface represents an object that can be serialized into a {@link ByteBuf}.
 */
public interface ByteBufSerializable<T extends ByteBuf> extends Serializable {

    /**
     * Writes the object to a {@link ByteBuf}'s byte buffer.
     *
     * @param buffer The message to which this object will be written.
     */
    void writeToBuffer(T buffer);
}
