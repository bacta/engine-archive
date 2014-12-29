package com.ocdsoft.bacta.engine.network.protocol;

public interface Protocol<T> {
	
	public T decode(int seed, T data, int offset);

	public T encode(int seed, T data, boolean doCompress);
	
	public boolean validate(int seed, T data);

    public void appendCRC(int seed, T data, int crcLength);
	
	public byte getEncryptionID();
	
}
