<%@page import="board.model.Notice"%>
<%@page import="java.util.ArrayList"%>
<%@page import="board.model.NoticeDAO"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="db.DBManager"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.PreparedStatement"%>
<%@ page import="java.sql.ResultSet"%>

<%
	NoticeDAO noticeDAO = new NoticeDAO();
	ArrayList<Notice> list = noticeDAO.selectAll();	
	
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
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
	$(function(){
		$("button").on("click",function(){
			//자바스크립트에서 링크 구현?
			location.href="/board/regist_form.jsp";
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
	<%Notice notice = list.get(i); //각 칸에 들어간 vo끄집어 내기 %>
  <tr>
    <td>26</td>
    <td>
		<a href="/board/detail.jsp?notice_id=<%=notice.getNotice_id()%>"><%=notice.getTitle()%></a>

	</td>
    <td><%=notice.getAuthor()%></td>
	<td><%=notice.getRegdate()%></td>
	<td><%=notice.getHit()%></td>
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
