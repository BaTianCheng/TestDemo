package com.cw.testdemo.jetty.configure;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.util.thread.QueuedThreadPool;

import com.cw.testdemo.jetty.dispatcher.DispatcherServer;


/**
 * 默认配置类
 * @author cw
 */
public class DefaultConfigure {
	
	/**
	 * 获得默认服务
	 * @return
	 */
	public static Server getDefaultServer(){
		
		//创建服务
		Server server = new Server();
		
		//添加线程池
		QueuedThreadPool queuedThreadPool = new QueuedThreadPool();
		queuedThreadPool.setName("queuedThreadPool");
		queuedThreadPool.setMinThreads(10);
		queuedThreadPool.setMaxThreads(200);
		server.setThreadPool(queuedThreadPool);
		
		//添加Connector
		SelectChannelConnector connector = new SelectChannelConnector();
		connector.setPort(4000);
		connector.setAcceptors(4);
		connector.setMaxBuffers(2048);
		connector.setMaxIdleTime(10000);
		server.addConnector(connector);
		
		//添加Handler
		ContextHandlerCollection context = new ContextHandlerCollection();
		ContextHandler contextHandler = context.addContext("/", "/");
		contextHandler.setHandler(new DispatcherServer());
		Handler defaults = new DefaultHandler();
		HandlerCollection collection = new HandlerCollection();
		collection.setHandlers(new Handler[] {context, defaults});
		server.setHandler(collection);
		
		return server;
	}
}