package com.xingej.rpc.serialize;

public interface ISerializer {
	public <T> byte[] serialize(T obj);

	public <T> T deserialize(byte[] data, Class<T> clazz);
}
