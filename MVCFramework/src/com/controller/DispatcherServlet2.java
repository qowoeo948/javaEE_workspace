/*
 * MVC패턴으로 개발하다보면, 늘어나는 컨트롤러에 대해 일일이 매핑과정을 진행해야한다.
 * 이때 너무 많은 매핑은 관리하기가 까다롭다. 따라서 유지보수성이 높지 못하다.
 * 현실과 유사하게 어플리케이션에서도 대형 업무처리시 클라이언트의 요청을 곧바로 해당 컨트롤러에게
 * 처리하게 하지 않고, 중간에 메인 컨트롤러를 두고, 모든 요청을 이 메인 컨트롤러에서 처리하여
 * 적절한 하위 컨트롤러에게 전담시키는 방식을 이용한다..
 * 
 * 이 컨트롤러는 웹어플리케이션의 모~~든 요청을 1차적으로 받는다.
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
	
	//init은 서블릿의 생명주기에서, 최초 접속자에 의해 톰켓이 인스턴스를 생성하며, 이와 동시에 초기화 메서드로서
	//호출된다.
	@Override
	public void init(ServletConfig config) throws ServletException {
		//getRealPath는 jsp의 내장객체 중 application에 대한 정보를 갖는 application내장객체에서 지원
		ServletContext context = config.getServletContext();
		String contextConfigLocation=config.getInitParameter("contextConfigLocation");
		String savePath = context.getRealPath(contextConfigLocation);
		System.out.println(savePath);
		
		try {
			fis = new FileInputStream(savePath);
			props = new Properties();
			props.load(fis);	//스트림과 프로퍼티스 연결
			
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
	//get이건 post이건 상관없이 모든 요청을 이 메서드에서 처리하자!
	public void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");//파라미터에 대한 인코딩
		//1단계: 요청받기
		System.out.println("제가 받았어요 그 요청!");
		
		//클라이언트가 영화를 원하면 ? -->영화담당 컨트롤러에게 전가
		//클라이언트가 혈액형을 원하면 ? -->혈액형담당 컨트롤러에게 전가
		
		//2단계: 요청을 분석하여, 알맞는 컨트롤러에게 요청을 전달한다.
		//글쓰기?, 삭제?, 혈액형?, 영화? 핸드폰수리?..
		//클라이언트가 무엇을 원하는지 알아야 한다.
		//해답은 클라이언트 요청 자체에 있다.. 즉 요청시 사용된 URI가 곧 요청 구분값이다
		String uri = request.getRequestURI();
		System.out.println("지금 들어온 요청은  "+uri);
		
		Controller controller = null;
		
		String className = null;
		
		/*----------------------------------------> if문을 대신하는 다른 파일로 대체
		if(uri.equals("/movie.do")) {
			className="movie.controller.MovieController";
			
			//System.out.println("영화전문 컨트롤러인 MovieController에게 전달할게요");
			//하위 컨트롤러 생성하기
			//controller = new MovieController();			
			//3단계 : 알맞는 로직 객체에게 일 시킨다.
			//->3단계,4단계는 controller -> MovieController,BloodController에서 실행
			
			//5단계: 클라이언트에게 알맞는 결과를 보여준다.
			//클라이언트로 하여금 지정한  url로 재접속을 유도하자!!명령하자 
			//response.sendRedirect("/movie/movie_result.jsp");
		}else if(uri.equals("/blood.do")) {
			className="blood.controller.BloodController";

			//System.out.println("혈액형전문 컨트롤러인 BloodController에게 전달할게요");
			//controller = new BloodController();
		}
		 * */
		
		//if문 대신에, 프로퍼티스 객체를 이용하여 key값으로 메모리에 올려질 컨트롤러에 이름을 value로 반환받자. 
		className = props.getProperty(uri);
		
		
		try {
			Class controllerClass = Class.forName(className); //클래스 로드
			//인스턴스 생성
			controller=(Controller)controllerClass.newInstance();
			
			//여기까지 2단계
			controller.execute(request, response);	//다형적으로 실행된다..(다형성)
			//5단계: 클라이언트에게 알맞는 결과를 보여준다.
			//response.sendRedirect(하위컨트롤러에게 물어본값);
			response.sendRedirect(controller.getViewPage());
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
	}
	
	//서블릿의 생명주기 메서드 중 서블릿이 소멸할 때 호출되는 메서드인 destory()에, 스트림 등의 자원을
	//닫는 처리를 하자
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
