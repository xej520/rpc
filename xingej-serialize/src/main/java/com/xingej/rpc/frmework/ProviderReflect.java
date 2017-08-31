package com.xingej.rpc.frmework;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang3.reflect.MethodUtils;

public class ProviderReflect {
	private static final ExecutorService executorService = Executors.newCachedThreadPool();

	// 发布服务
	public static void provider(final Object service, int port) throws Exception {
		@SuppressWarnings("resource")
		ServerSocket serverSocket = new ServerSocket(port);

		System.out.println("-----服务端----已经启动------");
		System.out.println("-----目前提供的服务有------:\t" + service.getClass());
		System.out.println("-----进入---监听---客户端----状态------");

		while (true) {
			// 开始监听，是否有客户端发送请求
			// 会在这里，进行阻塞的
			final Socket socket = serverSocket.accept();

			System.out.println("----->监听到客户端----->:\t" + socket.getInetAddress());

			executorService.execute(new Runnable() {

				@Override
				public void run() {
					ObjectInputStream ois = null;

					ObjectOutputStream oos = null;

					try {
						ois = new ObjectInputStream(socket.getInputStream());

						oos = new ObjectOutputStream(socket.getOutputStream());

						// 开始解析消息体
						// 方法名称
						String methodName = ois.readUTF();
						// 方法参数
						Object[] arguments = (Object[]) ois.readObject();

						// 调用服务，开始计算
						Object result = MethodUtils.invokeExactMethod(service, methodName, arguments);

						// 将计算结果，发送给客户端
						oos.writeObject(result);

					} catch (Exception e) {
						e.getStackTrace();
					} finally {
						try {
							oos.close();
							ois.close();
							socket.close();
						} catch (IOException e) {
							e.printStackTrace();
						}

					}
				}
			});

		}
	}
}
