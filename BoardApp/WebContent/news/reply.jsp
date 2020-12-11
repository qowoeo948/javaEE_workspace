<%@page import="board.model.CommentsDAO"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/inc/lib.jsp" %>
<%
	request.setCharacterEncoding("utf-8");

	//String news_id=request.getParameter("news_id");
	//String author=request.getParameter("author");
	//String msg=request.getParameter("msg");

	//String sql = "insert into comments(news_id,author,msg) values("+news_id+",'"+author+"','"+msg+"')";
	//out.print(sql);
	
	//CommentsDAO dao = new CommentsDAO();
	//dao.insert(comments);

%>
<jsp:useBean id="comments" class="board.model.Comments"/>
<jsp:setProperty property="*" name="comments"/>
<%
	if(new CommentsDAO().insert(comments)==0){
			out.print(getMsgBack("댓글 등록 실패"));
	}else{
		out.print(getMsgURL("댓글 등록 성공", "detail.jsp?news_id="+comments.getNews_id()));
	}

%>