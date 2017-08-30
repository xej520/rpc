package com.xingej.ser.client;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.xingej.ser.ISerializer;
import com.xingej.ser.impl.DefaultJavaSerializer;
import com.xingej.ser.impl.Fastjson2Serialize;
import com.xingej.ser.impl.HessianSerializer;
import com.xingej.ser.impl.XMLSerializer;

/**
 * 对java默认序列化进行测试
 * 
 *
 */
public class Driver {
	public static void main(String[] args) {
		// testXMLser();
		// testFastJSON();
		testHessianSer();
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

	private static void testFastJSON() {
		Map<String, Object> map = new HashMap<>();
		map.put("key1", 1);
		map.put("key2", 2);

		String string = JSON.toJSONString(map);

		System.out.println("---->:\t" + string);

		ISerializer serializer = new Fastjson2Serialize();

		byte[] mapBytes = serializer.serialize(map);

		Map<String, Object> result = serializer.deserialize(mapBytes, Map.class);

		for (Map.Entry<String, Object> entry : result.entrySet()) {
			System.out.println("key=\t" + entry.getKey() + "; value=\t" + entry.getValue());
		}

	}

	private static void testHessianSer() {
		HessianSerializer hessianSerializer = new HessianSerializer();

		String name = "mesos";

		// 序列化
		byte[] nameByte = hessianSerializer.serialize(name);

		// 反序列化
		Object object = hessianSerializer.deserialize(nameByte, String.class);

		System.out.println("---序列化--->:\t" + object);
	}

}
