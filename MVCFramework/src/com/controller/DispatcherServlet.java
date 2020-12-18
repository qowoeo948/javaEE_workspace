package com.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movie.controller.MovieController;

public class DispatcherServlet extends HttpServlet{
	FileInputStream fis;
	Properties props;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		ServletContext context = config.getServletContext();
		String contextConfigLocation = config.getInitParameter("contextConfigLocation");
		String savePath = context.getRealPath(contextConfigLocation);
		System.out.println(savePath);
		
		try {
			fis = new FileInputStream(savePath);
			props = new Properties();
			props.load(fis);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request,response);
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request,response);
	}
	
	public void doRequest(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
		//1단계: 요청받기 
		request.setCharacterEncoding("utf-8");
		
		//2단계: 요청을 분석하여, 알맞는 컨트롤러에게 요청을 전달
		String uri = request.getRequestURI();
		System.out.println(uri);
		
		Controller controller = null;
		String className = null;
		className = props.getProperty(uri);
		System.out.println(className);
		
		try {
			Class controllerClass = Class.forName(className);
			controller = (Controller) controllerClass.newInstance();
			
			controller.execute(request, response);
			response.sendRedirect(controller.getViewPage());
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void destroy() {
		if(fis!=null) {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
