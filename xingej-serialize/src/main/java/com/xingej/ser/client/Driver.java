package com.xingej.ser.client;

import com.xingej.ser.ISerializer;
import com.xingej.ser.impl.DefaultJavaSerializer;
import com.xingej.ser.impl.XMLSerializer;

/**
 * 对java默认序列化进行测试
 * 
 *
 */
public class Driver {
	public static void main(String[] args) {
		testXMLser();

	}

	private static void testJavaDefaultSer() {
		String name = "spark";
		ISerializer serializer = new DefaultJavaSerializer();

		// 序列化
		byte[] nameSer = serializer.serialize(name);

		// 反序列
		Object object = serializer.deserialize(nameSer, String.class);

		System.out.println("---序列化--->:\t" + object);
	}

	private static void testXMLser() {
		XMLSerializer xmlSerializer = new XMLSerializer();
		String name = "hadoop";
		// 序列化
		byte[] nameByte = xmlSerializer.serialize(name);

		// 反序列化
		Object object = xmlSerializer.deserialize(nameByte, String.class);

		System.out.println("---序列化--->:\t" + object);
	}

}
