package com.xingej.ser;

public interface ISerializer {
	// 序列化，将obj序列化成字节数组
	public <T> byte[] serialize(T obj);

	// 反序列化，将字节数组，反序列化为T
	public <T> T deserialize(byte[] data, Class<T> clazz);
}
