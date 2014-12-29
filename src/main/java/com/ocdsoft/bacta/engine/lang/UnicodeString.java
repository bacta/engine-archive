package com.ocdsoft.bacta.engine.lang;

import com.ocdsoft.bacta.engine.buffer.ByteBufferSerializable;
import lombok.Getter;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public final class UnicodeString implements ByteBufferSerializable {

    public static final UnicodeString EMPTY = new UnicodeString("");
    private static final Charset UTF_16LE = Charset.forName("UTF-16LE");

    @Getter
    private final String string;

    public UnicodeString() {
        string = new String();
    }

    public UnicodeString(String string) {
        this.string = string;
    }

    public UnicodeString(char[] buffer) {
        this.string = new String(buffer);
    }

    @Override
    public void writeToBuffer(ByteBuffer buffer) {
        buffer.putInt(string.length());
        buffer.put(string.getBytes(UTF_16LE));
    }

}
