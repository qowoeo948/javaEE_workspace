package com.model2.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Zz extends HttpServlet{
		FileReader fis;
		JSONObject controllerMap;
		JSONObject viewMap;
		
	
		@Override
		public void init(ServletConfig config) throws ServletException {
			String contextConfigLocation = config.getInitParameter("contextConfigLocation");
			ServletContext context = getServletContext();
			String realPath = context.getRealPath("contextConfigLocation");
			
			try {
				fis = new FileReader(realPath);
				JSONParser jsonParser = new JSONParser();
				
				JSONObject json = (JSONObject)jsonParser.parse(fis);
				
				controllerMap =  (JSONObject)json.get("controller");
				controllerMap =  (JSONObject)json.get("view");
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
	
		@Override
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			getRequest(request, response);
		}
	
		@Override
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			getRequest(request, response);
		}

		public void getRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
			request.setCharacterEncoding("utf-8");
			
			String uri = request.getRequestURI();
			String controllerName = (String)controllerMap.get(uri);
			Class controllerClass=null;
			Controller controller=null;
			try {
				controllerClass = Class.forName(controllerName);
				controller = (Controller)controllerClass.newInstance();
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			controller.execute(request, response);
			
			String resultKey = controller.getResultView();
			
			String viewPage = (String)viewMap.get(resultKey);
			
			if(controller.isForward()) {
				RequestDispatcher dis = request.getRequestDispatcher(viewPage);
				dis.forward(request, response);
				
			}else {
				response.sendRedirect(viewPage);
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
