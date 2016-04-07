package com.ocdsoft.bacta.engine.lang;

import com.ocdsoft.bacta.engine.buffer.ByteBufferSerializable;
import com.ocdsoft.bacta.engine.utils.BufferUtil;
import lombok.Getter;

import java.nio.ByteBuffer;

public final class UnicodeString implements ByteBufferSerializable {

    public static final UnicodeString EMPTY = new UnicodeString("");

    @Getter
    private String string;

    public UnicodeString() {
        string = new String();
    }

    public UnicodeString(String string) {
        this.string = string;
    }

    public UnicodeString(char[] buffer) {
        this.string = new String(buffer);
    }

    public UnicodeString(ByteBuffer buffer) {
        this.string = BufferUtil.getUnicode(buffer);
    }

    @Override
    public void writeToBuffer(ByteBuffer buffer) {
        BufferUtil.putUnicode(buffer, string);
    }

    @Override
    public void readFromBuffer(ByteBuffer buffer) {
        string = BufferUtil.getUnicode(buffer);
    }
}
