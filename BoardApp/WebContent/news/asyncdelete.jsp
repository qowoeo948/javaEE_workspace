<%@page import="board.model.CommentsDAO"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
	//넘겨받은 comments_id를 이용하여 삭제 후, 알맞는 피드백 문자열로 응답!
	String comments_id = request.getParameter("comments_id");
	
	CommentsDAO dao  = new CommentsDAO();
	int result = dao.delete(Integer.parseInt(comments_id));
	
	out.print(result);

%>