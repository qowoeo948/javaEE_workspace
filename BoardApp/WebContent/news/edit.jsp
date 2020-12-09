<%@page import="board.model.News"%>
<%@page import="board.model.NewsDAO"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/inc/lib.jsp" %>
<%
	request.setCharacterEncoding("utf-8");
	String writer = request.getParameter("writer");
	String title= request.getParameter("title");
	String content= request.getParameter("content");
	String news_id= request.getParameter("news_id");
	
	NewsDAO dao = new NewsDAO();
	News news = new News();
	news.setWriter(writer);
	news.setTitle(title);
	news.setContent(content);
	news.setNews_id(Integer.parseInt(news_id));
	
	int result = dao.update(news);
	
	if(result==0){
		out.print(getMsgBack("수정 실패"));
	}else{
		out.print(getMsgURL("수정 성공", "/news/detail.jsp?news_id="+news_id));
	}

%>