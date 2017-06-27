package com.cw.testdemo.jetty.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import com.cw.testdemo.jetty.dispatcher.agent.HttpAgent;

/**
 * 分发器
 * @author cw
 *
 */
public class DispatcherServer extends AbstractHandler{

	public void handle(String target, Request baseRequest,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		HttpAgent httpAgent = new HttpAgent(request, response);
		httpAgent.renturnResponse(request.toString() + "\n" + request.getParameterMap());
	}

}
