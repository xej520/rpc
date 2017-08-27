package com.xingej.ser.impl;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.xingej.ser.ISerializer;

public class XMLSerializer implements ISerializer {

	// 初始化XStream对象
	private static final XStream XStream = new XStream(new DomDriver());

	@Override
	public <T> byte[] serialize(T obj) {
		return XStream.toXML(obj).getBytes();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T deserialize(byte[] data, Class<T> clazz) {
		// 将字节数组，封装成字符串
		String xml = new String(data);
		return (T) XStream.fromXML(xml);
	}

}
