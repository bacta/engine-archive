package com.ocdsoft.bacta.engine.utils;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

@SuppressWarnings("unused")
public class BufferUtil {

    private static final Charset UTF_16LE = Charset.forName("UTF-16LE");
    private static final Charset ISO_8859_1 = Charset.forName("ISO-8859-1");

    public static String bytesToHex(byte[] bytes, char seperator) {
	    final char[] hexArray = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
	    int length = seperator != 0 ? 3 : 2;
	    char[] hexChars = new char[(bytes.length * length)];
	    int v;
	    for ( int j = 0; j < bytes.length; j++ ) {
	        v = bytes[j] & 0xFF;
	        hexChars[j * length] = hexArray[v >>> 4];
	        hexChars[j * length + 1] = hexArray[v & 0x0F];
	        
	        if (seperator != 0)
	        	hexChars[j * length + 2] = seperator;
	    }
	    return new String(hexChars);
	}
	
	public static String bytesToHex(byte[] bytes) {
		return bytesToHex(bytes, ' ');
	}
	
	public static String bytesToHex(ByteBuffer buffer, char seperator) {
	    final char[] hexArray = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
	    int length = seperator != 0 ? 3 : 2;

        int bufferSize = buffer.capacity();
	    char[] hexChars = new char[(bufferSize * length)];
	    int v;
	    for ( int j = 0; j < bufferSize; j++ ) {
	        v = buffer.get(j) & 0xFF;
	        hexChars[j * length] = hexArray[v >>> 4];
	        hexChars[j * length + 1] = hexArray[v & 0x0F];
	        
	        if (seperator != 0)
	        	hexChars[j * length + 2] = seperator;
	    }
	    return new String(hexChars);
	}
	
	public static String bytesToAscii(byte[] bytes) {

	    StringBuffer buffer = new StringBuffer();
	    int v;
	    for ( int j = 0; j < bytes.length; j++ ) {
	        v = (bytes[j] & 0xFF);
	        if(v < 32) {
	        	v = 46;
	        }
	        if(v > 126) {
	        	v = 46;
	        }
	        buffer.append((char) v);

	    }
	    return buffer.toString();
	}
	
	public static String bytesToHex(ByteBuffer buffer) {
		return bytesToHex(buffer, ' ');
	}
	
	public static String bytesToHex(short[] bytes) {
	    final char[] hexArray = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
	    char[] hexChars = new char[(bytes.length * 3)];
	    int v;
	    for ( int j = 0; j < bytes.length; j++ ) {
	        v = bytes[j] & 0xFF;
	        hexChars[j * 3] = hexArray[v >>> 4];
	        hexChars[j * 3 + 1] = hexArray[v & 0x0F];
	        hexChars[j * 3 + 2] = ' ';
	    }
	    return new String(hexChars);
	}

    public static void putBoolean(ByteBuffer buffer, boolean value) {
        buffer.put(value ? (byte) 1 : 0);
    }

    public static String getAscii(ByteBuffer buffer) {
        short length = buffer.getShort();
        byte[] bytes = new byte[length];
        buffer.get(bytes);
        return new String(bytes, ISO_8859_1);
    }

    public static void putAscii(ByteBuffer buffer, String value) {
        buffer.putShort((short)value.length());
        buffer.put(value.getBytes(ISO_8859_1));
    }

    public static void putUnicode(ByteBuffer buffer, String value) {
        buffer.putInt(value.length());
        buffer.put(value.getBytes(UTF_16LE));
    }

    public static String getUnicode(ByteBuffer buffer) {
        int length = buffer.getInt();
        byte[] bytes = new byte[length * 2];
        buffer.get(bytes);
        return new String(bytes, UTF_16LE);
    }

    public static void putBinaryString(ByteBuffer buffer, String value) {
        buffer.putInt(value.length());
        buffer.put(value.getBytes(ISO_8859_1));
    }

    public static String getBinaryString(ByteBuffer buffer) {
        int size = buffer.getInt();
        byte[] bytes = new byte[size];
        buffer.get(bytes);
        return new String(bytes);
    }
}
