package com.xingej.rpc.serialize;

import org.apache.commons.lang3.StringUtils;

/**
 * 总结：1、针对同一个业务，如果有多种技术方案可以提供时，可以使用枚举类型
 *
 */
public enum SerializeType {
	DefaultJavaSerializer("DefaultJavaSerializer"), Fastjson2Serializer("Fastjson2Serializer"), HessianSerializer(
			"HessianSerializer"), XmlSerializer("XmlSerializer");

	private String serializeType;

	private SerializeType(String serializeType) {
		this.serializeType = serializeType;
	}

	public static SerializeType queryByType(String serializeType) {
		if (StringUtils.isEmpty(serializeType)) {
			return null;
		}

		for (SerializeType serialize : SerializeType.values()) {
			if (StringUtils.equals(serializeType, serialize.getSerializeType())) {
				return serialize;
			}
		}

		return null;
	}

	public String getSerializeType() {
		return serializeType;
	}

}
