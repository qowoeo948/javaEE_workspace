<%@page import="admin.member.Admin"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/inc/lib.jsp" %>
<%
	String admin_id="scott";
	String admin_pass="1234";
	/*
	원래는 데이터베이스에서 조회를 해야 하지만, 추 후 하기로 하고
	일단은 스트링으로 비교 해본다.
	*/
	
	String mid = request.getParameter("mid");
	String password = request.getParameter("password");
	
	//아이디와 같고, 비밀번호까지 같다면 ... &&조건
	if(mid.equals(admin_id)&&password.equals(admin_pass)){
	//로그인 성공에 대한 보상을 해주자!! 관리자 페이지 보여주기
	//js의 location.href와 동일한 기능의 jsp 기능 이용해보자
	Admin admin = new Admin();
	//관리자 정보를 vo에 담자
	admin.setMid(mid);
	admin.setPassword(password);
	
	response.sendRedirect("/admin/index.jsp?admin_id="+admin.getMid());	//클라이언트로 하여금 지정한 url로 요청을 시도하는 기능
			
	}else{	//로그인 실패
		out.print(getMsgBack("로그인 정보가 옳지 않습니다."));
	}
		


%>