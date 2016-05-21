package com.ocdsoft.bacta.engine.serialize;

/**
 * Created by kburkhardt on 7/26/14.
 */
public interface NetworkSerializer {
    byte[] serialize(Object object);
    Object deserialize(byte[] bytes);
}
