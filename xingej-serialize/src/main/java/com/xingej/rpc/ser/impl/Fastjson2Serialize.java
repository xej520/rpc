package com.xingej.rpc.ser.impl;

import com.alibaba.fastjson.JSON;
import com.xingej.rpc.ser.ISerializer;

public class Fastjson2Serialize implements ISerializer {

	@Override
	public <T> byte[] serialize(T obj) {
		String objStr = JSON.toJSONString(obj);
		return objStr.getBytes();
	}

	@Override
	public <T> T deserialize(byte[] data, Class<T> clazz) {
		return (T) JSON.parseObject(new String(data), clazz);
	}

}
