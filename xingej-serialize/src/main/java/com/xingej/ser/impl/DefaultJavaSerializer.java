package com.xingej.ser.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.xingej.ser.ISerializer;

public class DefaultJavaSerializer implements ISerializer {

	public <T> byte[] serialize(T obj) {
		// 默认创建的是32个字节
		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		ObjectOutputStream oos = null;

		try {
			oos = new ObjectOutputStream(bos);

			// 通过writeObject,将obj对象进行序列化
			oos.writeObject(obj);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return bos.toByteArray();
	}

	@SuppressWarnings("unchecked")
	public <T> T deserialize(byte[] data, Class<T> clazz) {
		// 将字节数组，转换成输入流
		ByteArrayInputStream bis = new ByteArrayInputStream(data);

		ObjectInputStream ois = null;

		Object object = null;

		try {
			// 将字节输入流 转换成 对象输入流
			ois = new ObjectInputStream(bis);

			// 这样的话，就可以通过ObjectInputStream的readObject方法
			// 直接读取了
			object = ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return (T) object;
	}

}
