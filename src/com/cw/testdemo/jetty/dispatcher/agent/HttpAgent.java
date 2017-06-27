package com.cw.testdemo.jetty.dispatcher.agent;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Http代理
 * @author cw
 *
 */
public class HttpAgent {
	
	/**
	 * 请求
	 */
	private final HttpServletRequest request;
	
	/**
	 * 响应
	 */
	private final HttpServletResponse response;
	
	/**
	 * 路径
	 */
	private final String path;
	
	/**
	 * 查询字符串
	 */
	private final String queryString;
	
	/**
	 * 参数集合
	 */
	private final Map<String, String[]> params;
	
	/**
	 * 属性集合
	 */
	private final Map<String, Object> attributes;
	
	/**
	 * 构造器
	 * @param request
	 * @param response
	 */
	public HttpAgent(HttpServletRequest request, HttpServletResponse response){
		this.request = request;
		this.response = response;
		this.path = request.getPathInfo();
		this.queryString = request.getQueryString();
		this.params = new HashMap<String, String[]>();
		this.attributes = new HashMap<String, Object>();
	}
	
	/**
	 * 输出流
	 * @param jsonData
	 */
	public void renturnResponse(String jsonData){
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/json;charset=UTF-8");
		try{
			PrintWriter out = response.getWriter();
			out.write(jsonData);
			out.flush();
			out.close();
		}
		catch(Exception ex){}
		finally{}
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public String getPath() {
		return path;
	}

	public String getQueryString() {
		return queryString;
	}

	public Map<String, String[]> getParams() {
		return params;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}
	
}
