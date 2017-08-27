package com.xingej.ser.impl;

import com.xingej.ser.ISerializer;

public class XMLSerializer implements ISerializer {

	@Override
	public <T> byte[] serialize(T obj) {
		return null;
	}

	@Override
	public <T> T deserialize(byte[] data, Class<T> clazz) {
		return null;
	}

}
