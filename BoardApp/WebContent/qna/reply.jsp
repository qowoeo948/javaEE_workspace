<%@page import="board.model.QnA"%>
<%@page import="board.model.QnADAO"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/inc/lib.jsp" %>
<%
	request.setCharacterEncoding("utf-8");
	
	//넘겨받은 파라미터 값을 이용하여 답글 달자
	String writer = request.getParameter("writer");
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	String team = request.getParameter("team");
	String rank = request.getParameter("rank");
	String depth = request.getParameter("depth");
	
	//답글을 달기 위한 쿼리문을 알아야 한다.
	
	//1단계: 후발로 등록된 글이 들어갈 자리 확보 (기존 글들 밑어 내는 효과)
	//String sql = "update qna set rank = rank+1 where team="+team+" and rank>"+rank+"";
	//out.print(sql);
	
	//out.print("<br>");
	//2단계: 내가 본글의 바로 아래쪽에 답변 insert
	//sql = "insert into qna(team,rank,depth) values("+team+","+(rank+1)+","+(depth+1)+")";
	//out.print(sql);

	QnA qna = new QnA();
	qna.setWriter(writer);
	qna.setTitle(title);
	qna.setContent(content);
	qna.setTeam(Integer.parseInt(team));
	qna.setRank(Integer.parseInt(rank));
	qna.setDepth(Integer.parseInt(depth));
	
	QnADAO dao = new QnADAO();
	int result = dao.reply(qna);
	
	if(result==0){
		out.print(getMsgBack("답변등록실패"));
	}else{
		out.print(getMsgURL("답변 등록 성공", "/qna/list.jsp"));
	}
	
	
	
%>