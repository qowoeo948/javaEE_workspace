<%@ page contentType="text/html;charset=utf-8"%>
<%
	//메시지를 받아보자
	request.setCharacterEncoding("utf-8");
	String msg = request.getParameter("msg");
	//out.print(msg);
	
	//session.setAttribute("result", msg);
	//지금 요청과 관련된 요청객체에 무언가를 담아보자!!
	//session과 request는 거의 쌍둥이 인데, 단지 살 수 있는 생명력에만 차이가 있다.
	request.setAttribute("result", msg);
	
	//서버상의 또 다른 jsp(서블릿)에 요청을 전달해보자
	RequestDispatcher dis =request.getRequestDispatcher("/test/result.jsp");
	//포워딩 시작
	dis.forward(request, response);
	
	
	
	//클라이언트로 하여금 지정한 url로 다시 접속하라는 명령
	//response.sendRedirect("/test/result.jsp");
%>