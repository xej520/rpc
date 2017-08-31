package com.xingej.rpc.invoke;

import com.xingej.rpc.frmework.ConsumerProxy;
import com.xingej.rpc.service.IHelloService;

public class RpcConsumerMain {
	public static void main(String[] args) throws Exception {
		IHelloService helloService = ConsumerProxy.consume(IHelloService.class, "127.0.0.1", 8083);

		for (int i = 0; i < 100; i++) {
			String hello = helloService.sayHello("marathon_" + i);
			System.out.println("--->\t" + hello);

			// 每一秒调用一次服务
			Thread.sleep(1000);
		}

	}
}
