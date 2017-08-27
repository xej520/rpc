package com.xingej.ser.client;

import com.xingej.ser.ISerializer;
import com.xingej.ser.impl.DefaultJavaSerializer;

/**
 * 对java默认序列化进行测试
 * 
 *
 */
public class Driver {
	public static void main(String[] args) {
		String name = "spark";
		ISerializer serializer = new DefaultJavaSerializer();

		// 序列化
		byte[] nameSer = serializer.serialize(name);

		// 反序列
		Object object = serializer.deserialize(nameSer, String.class);

		System.out.println("---序列化--->:\t" + object);
	}
}
