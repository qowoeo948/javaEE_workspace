<%@page import="java.util.List"%>
<%@page import="board.model.QnA"%>
<%@page import="board.model.QnADAO"%>
<%@page import="board.model.Notice"%>
<%@page import="java.util.ArrayList"%>
<%@page import="board.model.NoticeDAO"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="db.DBManager"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.PreparedStatement"%>
<%@ page import="java.sql.ResultSet"%>

<%
	QnADAO qnaDAO = new QnADAO();
	List<QnA> list = qnaDAO.selectAll();	
	
%>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
table {
  border-collapse: collapse;
  border-spacing: 0;
  width: 100%;
  border: 1px solid #ddd;
}

th, td {
  text-align: left;
  padding: 16px;
}

tr:nth-child(even) {
  background-color: #f2f2f2;
}
a{
	text-decoration:none;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
	$(function(){
		$("button").on("click",function(){
			//자바스크립트에서 링크 구현?
			location.href="/qna/regist_form.jsp";
		});
	});//onload
	

</script>
</head>
<body>

<table>
  <tr>
    <th>No</th>
    <th>제목</th>
    <th>작성자</th>
	<th>등록일</th>
    <th>조회수</th>
    
  </tr>
  	
	<%for(int i =0;i<list.size();i++){%>
	<%QnA qna = list.get(i); //각 칸에 들어간 vo끄집어 내기 %>
  <tr>
    <td>26</td>
    <td>
    <%if(qna.getDepth()>0){ //depth가 0보다 큰 경우 답변으로 판단%>
    	<img src="/images/reply.png" style="margin-left:<%=30*qna.getDepth()%>px">
		<%} %>
		<a href="/qna/detail.jsp?qna_id=<%=qna.getQna_id()%>"><%=qna.getTitle()%></a>

	</td>
    <td><%= qna.getWriter()%></td>
	<td><%=qna.getRegdate().substring(0,10)%></td>
	<td><%=qna.getHit()%></td>
  </tr>
  	<%}%>

  	<tr>
		<td colspan="5" style="text-align:center">
			<button>글등록</button>
		</td>
	</tr>
	  	<tr>
		<td colspan="5" style="text-align:center">
			<%@ include file="/inc/footer.jsp"%>
	
		</td>
	</tr>

</table>

</body>
</html>
