package com.xingej.rpc.serialize;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.xingej.rpc.serialize.impl.DefaultJavaSerializer;
import com.xingej.rpc.serialize.impl.Fastjson2Serialize;
import com.xingej.rpc.serialize.impl.HessianSerializer;
import com.xingej.rpc.serialize.impl.XMLSerializer;

/**
 * 总结：1、针对同一个业务，如果有多种技术方案可以提供时，可以使用枚举类型；2、创建一容器，专门来存储(或者叫注册)不同的实现方案
 * 3、其实，容器，以后可以用zk来代替，这样的话，就实现了，动态的增加，删除；不然，每次增加新的实现方案时，都要重新修改代码，重新部署，
 * 因此，可以将下面的serializerMap，以后用ZK来实现
 */

public class SerializerEngine {
	// 声明一容器，专门用来存储序列化工具
	public static final Map<SerializeType, ISerializer> serializerMap = new ConcurrentHashMap<>();

	// 注册序列化工具类到容器serializerMap里
	static {
		serializerMap.put(SerializeType.DefaultJavaSerializer, new DefaultJavaSerializer());

		serializerMap.put(SerializeType.HessianSerializer, new HessianSerializer());

		serializerMap.put(SerializeType.Fastjson2Serializer, new Fastjson2Serialize());

		serializerMap.put(SerializeType.XmlSerializer, new XMLSerializer());
	}

	public static <T> byte[] serialize(T obj, String serializeType) {

		ISerializer iSerializer = getSerializer(serializeType);

		try {
			return iSerializer.serialize(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	public static <T> T deserializer(byte[] data, Class<T> clazz, String serializeType) {
		ISerializer iSerializer = getSerializer(serializeType);

		try {
			return iSerializer.deserialize(data, clazz);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static ISerializer getSerializer(String serializeType) {
		SerializeType serialize = SerializeType.queryByType(serializeType);

		if (null == serialize) {
			// 将运行时的异常，抛出去
			throw new RuntimeException("serialize is null");
		}

		ISerializer iSerializer = serializerMap.get(serialize);

		if (null == iSerializer) {
			// 将异常抛给调用者，
			// 让业务层去处理
			throw new RuntimeException("serialize is error");
		}

		return iSerializer;
	}

}
