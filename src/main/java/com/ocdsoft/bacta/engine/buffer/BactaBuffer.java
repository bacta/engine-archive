package com.ocdsoft.bacta.engine.buffer;

/**
 * Created by Kyle on 8/31/2014.
 */
public class BactaBuffer {
/*
    private final ByteBuffer buffer;
    private static final Charset UTF_16LE = Charset.forName("UTF-16LE");
    private static final Charset ISO_8859_1 = Charset.forName("ISO-8859-1");

    private BactaBuffer(ByteBuffer buffer) {
        this.buffer = buffer;
    }

    public static BactaBuffer wrap(ByteBuffer buffer) {
        return new BactaBuffer(buffer);
    }

    public static BactaBuffer wrap(byte[] buffer) {
        return new BactaBuffer(ByteBuffer.wrap(buffer));
    }

    public void writeUnicode(String value) {
        buffer.putInt(value.length());
        buffer.put(value.getBytes(UTF_16LE));
    }

    public String readUnicode() {
        int length = buffer.getInt();
        byte[] bytes = new byte[length * 2];
        buffer.get(bytes);
        return new String(bytes, UTF_16LE);
    }

    public void writeAscii(String value) {
        buffer.putShort((short)value.length());
        buffer.put(value.getBytes(ISO_8859_1));
    }

    public String readAscii() {
        short length = buffer.getShort();
        byte[] bytes = new byte[length];
        buffer.get(bytes);
        return new String(bytes, ISO_8859_1);
    }

    public void putShortBE(short value) {

        if (buffer.order() == ByteOrder.LITTLE_ENDIAN) {
            value = Short.reverseBytes(value);

        }

        putShort(value);
    }

    public void putShortBE(int value) {
        putShortBE((short) value);
    }

    public void setShortBE(int index, short value) {

        if (buffer.order() == ByteOrder.LITTLE_ENDIAN) {
            value = Short.reverseBytes(value);
        }

        setShort(index, value);
    }

    public short getShortBE(int index) {

        short value;

        if (buffer.order() == ByteOrder.LITTLE_ENDIAN) {
            value = Short.reverseBytes(getShort(index));
        } else {
            value = getShort(index);
        }

        return value;
    }

    public void writeIntBE(int value) {

        if (buffer.order() == ByteOrder.LITTLE_ENDIAN) {
            value = Integer.reverseBytes(value);
        }

        writeInt(value);
    }

    public void setIntBE(int index, int value) {

        if (buffer.order() == ByteOrder.LITTLE_ENDIAN) {
            value = Integer.reverseBytes(value);
        }

        setInt(index, value);
    }

    public void writeBinaryString(String value) {
        writeInt(value.length());
        put(value.getBytes(CharsetUtil.ISO_8859_1));
    }

    public String readBinaryString() {
        int size = readInt();
        byte[] bytes = new byte[size];
        get(bytes);
        return new String(bytes);
    }

    public short getShortBE() {

        short value = getShort();

        if (buffer.order() == ByteOrder.LITTLE_ENDIAN) {
            value = Short.reverseBytes(value);
        }

        return value;
    }

    public int readIntBE() {

        int value = readInt();

        if (buffer.order() == ByteOrder.LITTLE_ENDIAN) {
            value = Integer.reverseBytes(value);
        }
        return value;
    }

    public int capacity() {
        return buffer.capacity();
    }

    public ByteOrder order() {
        return buffer.order();
    }

    public ByteBuffer order(ByteOrder endianness) {
        return buffer.order(endianness);
    }

    public boolean isDirect() {
        return buffer.isDirect();
    }

    public int position() {
        return buffer.position();
    }

    public Buffer position(int position) {
        return buffer.position(position);
    }

    public int readableBytes() {
        return buffer.limit() - buffer.position();
    }

    public int writableBytes() {
        return buffer.capacity() - buffer.position();
    }

    public boolean isWritable(int size) {
        return buffer.isReadOnly() == false;
    }

    public Buffer clear() {
       return buffer.clear();
    }

    public boolean getBoolean(int index) {
        return buffer.get(index) == 1;
    }

    public byte getByte(int index) {
        return buffer.get(index);
    }

    public short getUnsignedByte(int index) {
        return UnsignedUtil.getUnsignedByte(buffer, index);
    }

    public short getShort(int index) {
        return buffer.getShort(index);
    }

    public int getUnsignedShort(int index) {
        return UnsignedUtil.getUnsignedShort(buffer, index);
    }

    public int getInt(int index) {
        return buffer.getInt(index);
    }

    public long getUnsignedInt(int index) {
        return UnsignedUtil.getUnsignedInt(buffer, index);
    }

    public long getLong(int index) {
        return buffer.getLong(index);
    }

    public char getChar(int index) {
        return buffer.getChar(index);
    }

    public float getFloat(int index) {
        return buffer.getFloat(index);
    }

    public double getDouble(int index) {
        return buffer.getDouble(index);
    }

    @Override
    public ByteBuf getBytes(int index, ByteBuf dst) {
        return buffer.getBytes(index, dst);
    }

    @Override
    public ByteBuf getBytes(int index, ByteBuf dst, int length) {
        return buffer.getBytes(index, dst, length);
    }

    @Override
    public ByteBuf getBytes(int index, ByteBuf dst, int dstIndex, int length) {
        return buffer.getBytes(index, dst, dstIndex, length);
    }

    @Override
    public ByteBuf getBytes(int index, byte[] dst) {
        return buffer.getBytes(index, dst);
    }

    @Override
    public ByteBuf getBytes(int index, byte[] dst, int dstIndex, int length) {
        return buffer.getBytes(index, dst, dstIndex, length);
    }

    @Override
    public ByteBuf getBytes(int index, ByteBuffer dst) {
        return buffer.getBytes(index, dst);
    }

    @Override
    public ByteBuf getBytes(int index, OutputStream out, int length) throws IOException {
        return buffer.getBytes(index, out, length);
    }

    @Override
    public int getBytes(int index, GatheringByteChannel out, int length) throws IOException {
        return buffer.getBytes(index, out, length);
    }

    @Override
    public ByteBuf setBoolean(int index, boolean value) {
        return buffer.setBoolean(index, value);
    }

    @Override
    public ByteBuf setByte(int index, int value) {
        return buffer.setByte(index, value);
    }

    @Override
    public ByteBuf setShort(int index, int value) {
        return buffer.setShort(index, value);
    }

    @Override
    public ByteBuf setMedium(int index, int value) {
        return buffer.setMedium(index, value);
    }

    @Override
    public ByteBuf setInt(int index, int value) {
        return buffer.setInt(index, value);
    }

    @Override
    public ByteBuf setLong(int index, long value) {
        return buffer.setLong(index, value);
    }

    @Override
    public ByteBuf setChar(int index, int value) {
        return buffer.setChar(index, value);
    }

    @Override
    public ByteBuf setFloat(int index, float value) {
        return buffer.setFloat(index, value);
    }

    @Override
    public ByteBuf setDouble(int index, double value) {
        return buffer.setDouble(index, value);
    }

    @Override
    public ByteBuf setBytes(int index, ByteBuf src) {
        return buffer.setBytes(index, src);
    }

    @Override
    public ByteBuf setBytes(int index, ByteBuf src, int length) {
        return buffer.setBytes(index, src, length);
    }

    @Override
    public ByteBuf setBytes(int index, ByteBuf src, int srcIndex, int length) {
        return buffer.setBytes(index, src, srcIndex, length);
    }

    @Override
    public ByteBuf setBytes(int index, byte[] src) {
        return buffer.setBytes(index, src);
    }

    @Override
    public ByteBuf setBytes(int index, byte[] src, int srcIndex, int length) {
        return buffer.setBytes(index, src, srcIndex, length);
    }

    @Override
    public ByteBuf setBytes(int index, ByteBuffer src) {
        return buffer.setBytes(index, src);
    }

    @Override
    public int setBytes(int index, InputStream in, int length) throws IOException {
        return buffer.setBytes(index, in, length);
    }

    @Override
    public int setBytes(int index, ScatteringByteChannel in, int length) throws IOException {
        return buffer.setBytes(index, in, length);
    }

    @Override
    public ByteBuf setZero(int index, int length) {
        return buffer.setZero(index, length);
    }

    @Override
    public boolean readBoolean() {
        return buffer.readBoolean();
    }

    @Override
    public byte readByte() {
        return buffer.readByte();
    }

    @Override
    public short readUnsignedByte() {
        return buffer.readUnsignedByte();
    }

    @Override
    public short getShort() {
        return buffer.getShort();
    }

    @Override
    public int readUnsignedShort() {
        return buffer.readUnsignedShort();
    }

    @Override
    public int readMedium() {
        return buffer.readMedium();
    }

    @Override
    public int readUnsignedMedium() {
        return buffer.readUnsignedMedium();
    }

    @Override
    public int readInt() {
        return buffer.readInt();
    }

    @Override
    public long readUnsignedInt() {
        return buffer.readUnsignedInt();
    }

    @Override
    public long readLong() {
        return buffer.readLong();
    }

    @Override
    public char readChar() {
        return buffer.readChar();
    }

    @Override
    public float readFloat() {
        return buffer.readFloat();
    }

    @Override
    public double readDouble() {
        return buffer.readDouble();
    }

    @Override
    public ByteBuf get(int length) {
        return buffer.get(length);
    }

    @Override
    public ByteBuf readSlice(int length) {
        return buffer.readSlice(length);
    }

    @Override
    public ByteBuf get(ByteBuf dst) {
        return buffer.get(dst);
    }

    @Override
    public ByteBuf get(ByteBuf dst, int length) {
        return buffer.get(dst, length);
    }

    @Override
    public ByteBuf get(ByteBuf dst, int dstIndex, int length) {
        return buffer.get(dst, dstIndex, length);
    }

    @Override
    public ByteBuf get(byte[] dst) {
        return buffer.get(dst);
    }

    @Override
    public ByteBuf get(byte[] dst, int dstIndex, int length) {
        return buffer.get(dst, dstIndex, length);
    }

    @Override
    public ByteBuf get(ByteBuffer dst) {
        return buffer.get(dst);
    }

    @Override
    public ByteBuf get(OutputStream out, int length) throws IOException {
        return buffer.get(out, length);
    }

    @Override
    public int get(GatheringByteChannel out, int length) throws IOException {
        return buffer.get(out, length);
    }

    @Override
    public ByteBuf skipBytes(int length) {
        return buffer.skipBytes(length);
    }

    @Override
    public ByteBuf writeBoolean(boolean value) {
        return buffer.writeBoolean(value);
    }

    @Override
    public ByteBuf writeByte(int value) {
        return buffer.writeByte(value);
    }

    @Override
    public ByteBuf putShort(int value) {
        return buffer.putShort(value);
    }

    @Override
    public ByteBuf writeMedium(int value) {
        return buffer.writeMedium(value);
    }

    @Override
    public ByteBuf writeInt(int value) {
        return buffer.writeInt(value);
    }

    @Override
    public ByteBuf writeLong(long value) {
        return buffer.writeLong(value);
    }

    @Override
    public ByteBuf writeChar(int value) {
        return buffer.writeChar(value);
    }

    @Override
    public ByteBuf writeFloat(float value) {
        return buffer.writeFloat(value);
    }

    @Override
    public ByteBuf writeDouble(double value) {
        return buffer.writeDouble(value);
    }

    @Override
    public ByteBuf put(ByteBuf src) {
        return buffer.put(src);
    }

    @Override
    public ByteBuf put(ByteBuf src, int length) {
        return buffer.put(src, length);
    }

    @Override
    public ByteBuf put(ByteBuf src, int srcIndex, int length) {
        return buffer.put(src, srcIndex, length);
    }

    @Override
    public ByteBuf put(byte[] src) {
        return buffer.put(src);
    }

    @Override
    public ByteBuf put(byte[] src, int srcIndex, int length) {
        return buffer.put(src, srcIndex, length);
    }

    @Override
    public ByteBuf put(ByteBuffer src) {
        return buffer.put(src);
    }

    public Buffer duplicate() {
        return buffer.duplicate();
    }

    public ByteBuffer slice() {
        return buffer.slice();
    }

    public boolean hasArray() {
        return buffer.hasArray();
    }

    public byte[] array() {
        return buffer.array();
    }

    public int arrayOffset() {
        return buffer.arrayOffset();
    }

    @Override
    public int hashCode() {
        return buffer.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return buffer.equals(obj);
    }

    @Override
    public String toString() {
        return buffer.toString();
    }
    */
}
