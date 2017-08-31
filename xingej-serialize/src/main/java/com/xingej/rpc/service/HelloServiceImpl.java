package com.xingej.rpc.service;

public class HelloServiceImpl implements IHelloService {

	@Override
	public String sayHello(String content) {
		return "----->:\t" + content;
	}

}
