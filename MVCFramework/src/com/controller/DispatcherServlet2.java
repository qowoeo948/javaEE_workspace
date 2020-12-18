/*
 * MVC�������� �����ϴٺ���, �þ�� ��Ʈ�ѷ��� ���� ������ ���ΰ����� �����ؾ��Ѵ�.
 * �̶� �ʹ� ���� ������ �����ϱⰡ ��ٷӴ�. ���� ������������ ���� ���ϴ�.
 * ���ǰ� �����ϰ� ���ø����̼ǿ����� ���� ����ó���� Ŭ���̾�Ʈ�� ��û�� ��ٷ� �ش� ��Ʈ�ѷ�����
 * ó���ϰ� ���� �ʰ�, �߰��� ���� ��Ʈ�ѷ��� �ΰ�, ��� ��û�� �� ���� ��Ʈ�ѷ����� ó���Ͽ�
 * ������ ���� ��Ʈ�ѷ����� �����Ű�� ����� �̿��Ѵ�..
 * 
 * �� ��Ʈ�ѷ��� �����ø����̼��� ��~~�� ��û�� 1�������� �޴´�.
 * */
package com.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import blood.controller.BloodController;
import movie.controller.MovieController;
import movie.model.MovieAdvisor;

public class DispatcherServlet2 extends HttpServlet{
	
	FileInputStream fis;
	Properties props;
	
	//init�� ������ �����ֱ⿡��, ���� �����ڿ� ���� ������ �ν��Ͻ��� �����ϸ�, �̿� ���ÿ� �ʱ�ȭ �޼���μ�
	//ȣ��ȴ�.
	@Override
	public void init(ServletConfig config) throws ServletException {
		//getRealPath�� jsp�� ���尴ü �� application�� ���� ������ ���� application���尴ü���� ����
		ServletContext context = config.getServletContext();
		String contextConfigLocation=config.getInitParameter("contextConfigLocation");
		String savePath = context.getRealPath(contextConfigLocation);
		System.out.println(savePath);
		
		try {
			fis = new FileInputStream(savePath);
			props = new Properties();
			props.load(fis);	//��Ʈ���� ������Ƽ�� ����
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);

	}
	//get�̰� post�̰� ������� ��� ��û�� �� �޼��忡�� ó������!
	public void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");//�Ķ���Ϳ� ���� ���ڵ�
		//1�ܰ�: ��û�ޱ�
		System.out.println("���� �޾Ҿ�� �� ��û!");
		
		//Ŭ���̾�Ʈ�� ��ȭ�� ���ϸ� ? -->��ȭ��� ��Ʈ�ѷ����� ����
		//Ŭ���̾�Ʈ�� �������� ���ϸ� ? -->��������� ��Ʈ�ѷ����� ����
		
		//2�ܰ�: ��û�� �м��Ͽ�, �˸´� ��Ʈ�ѷ����� ��û�� �����Ѵ�.
		//�۾���?, ����?, ������?, ��ȭ? �ڵ�������?..
		//Ŭ���̾�Ʈ�� ������ ���ϴ��� �˾ƾ� �Ѵ�.
		//�ش��� Ŭ���̾�Ʈ ��û ��ü�� �ִ�.. �� ��û�� ���� URI�� �� ��û ���а��̴�
		String uri = request.getRequestURI();
		System.out.println("���� ���� ��û��  "+uri);
		
		Controller controller = null;
		
		String className = null;
		
		/*----------------------------------------> if���� ����ϴ� �ٸ� ���Ϸ� ��ü
		if(uri.equals("/movie.do")) {
			className="movie.controller.MovieController";
			
			//System.out.println("��ȭ���� ��Ʈ�ѷ��� MovieController���� �����ҰԿ�");
			//���� ��Ʈ�ѷ� �����ϱ�
			//controller = new MovieController();			
			//3�ܰ� : �˸´� ���� ��ü���� �� ��Ų��.
			//->3�ܰ�,4�ܰ�� controller -> MovieController,BloodController���� ����
			
			//5�ܰ�: Ŭ���̾�Ʈ���� �˸´� ����� �����ش�.
			//Ŭ���̾�Ʈ�� �Ͽ��� ������  url�� �������� ��������!!������� 
			//response.sendRedirect("/movie/movie_result.jsp");
		}else if(uri.equals("/blood.do")) {
			className="blood.controller.BloodController";

			//System.out.println("���������� ��Ʈ�ѷ��� BloodController���� �����ҰԿ�");
			//controller = new BloodController();
		}
		 * */
		
		//if�� ��ſ�, ������Ƽ�� ��ü�� �̿��Ͽ� key������ �޸𸮿� �÷��� ��Ʈ�ѷ��� �̸��� value�� ��ȯ����. 
		className = props.getProperty(uri);
		
		
		try {
			Class controllerClass = Class.forName(className); //Ŭ���� �ε�
			//�ν��Ͻ� ����
			controller=(Controller)controllerClass.newInstance();
			
			//������� 2�ܰ�
			controller.execute(request, response);	//���������� ����ȴ�..(������)
			//5�ܰ�: Ŭ���̾�Ʈ���� �˸´� ����� �����ش�.
			//response.sendRedirect(������Ʈ�ѷ����� �����);
			response.sendRedirect(controller.getViewPage());
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
	}
	
	//������ �����ֱ� �޼��� �� ������ �Ҹ��� �� ȣ��Ǵ� �޼����� destory()��, ��Ʈ�� ���� �ڿ���
	//�ݴ� ó���� ����
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
