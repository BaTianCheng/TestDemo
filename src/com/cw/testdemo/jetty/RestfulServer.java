package com.cw.testdemo.jetty;

import org.eclipse.jetty.server.Server;

import com.cw.testdemo.jetty.configure.DefaultConfigure;

/**
 * Restful服务
 * @author cw
 *
 */
public class RestfulServer {

	private static Server jettyserver;
	
	static {
		//初始化操作
		jettyserver = DefaultConfigure.getDefaultServer();
	}
	
	public static void start() throws Exception{
		jettyserver.start();
		jettyserver.join();
	}
	
}