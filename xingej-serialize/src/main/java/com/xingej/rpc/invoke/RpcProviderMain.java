package com.xingej.rpc.invoke;

import com.xingej.rpc.frmework.ProviderReflect;
import com.xingej.rpc.service.HelloServiceImpl;
import com.xingej.rpc.service.IHelloService;

public class RpcProviderMain {
	public static void main(String[] args) throws Exception {
		IHelloService helloService = new HelloServiceImpl();

		ProviderReflect.provider(helloService, 8083);
	}
}
