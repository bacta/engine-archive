package com.ocdsoft.bacta.engine.utils;

import io.netty.buffer.ByteBuf;

@SuppressWarnings("unused")
public class BufferUtil {

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
	
	public static String bytesToHex(ByteBuf buffer, char seperator) {
	    final char[] hexArray = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
	    int length = seperator != 0 ? 3 : 2;
	    char[] hexChars = new char[(buffer.writerIndex() * length)];
	    int v;
	    for ( int j = 0; j < buffer.writerIndex(); j++ ) {
	        v = buffer.getByte(j) & 0xFF;
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
	
	public static String bytesToHex(ByteBuf buffer) {
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
}
