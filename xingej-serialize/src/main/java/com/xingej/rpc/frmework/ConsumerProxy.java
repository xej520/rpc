package com.xingej.rpc.frmework;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;

public class ConsumerProxy {

	@SuppressWarnings("unchecked")
	public static <T> T consume(final Class<T> interfaceClass, final String host, final int port) throws Exception {

		return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class<?>[] { interfaceClass },
				new InvocationHandler() {

					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						Socket socket = new Socket(host, port);

						ObjectOutputStream oos = null;
						ObjectInputStream ois = null;

						try {
							oos = new ObjectOutputStream(socket.getOutputStream());

							oos.writeUTF(method.getName());
							oos.writeObject(args);

							ois = new ObjectInputStream(socket.getInputStream());
							Object result = ois.readObject();

							if (result instanceof Throwable) {
								throw (Throwable) result;
							}

							return result;

						} catch (Exception e) {
							e.printStackTrace();
						} finally {
							ois.close();
							oos.close();
							socket.close();
						}

						return null;
					}

				});
	}
}
