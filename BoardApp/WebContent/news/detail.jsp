<%@page import="board.model.Comments"%>
<%@page import="java.util.List"%>
<%@page import="board.model.CommentsDAO"%>
<%@page import="board.model.News"%>
<%@page import="board.model.NewsDAO"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
	String news_id = request.getParameter("news_id");
	
	NewsDAO dao = new NewsDAO();
	News news = dao.select(Integer.parseInt(news_id));
	
	//이 게시물에 딸려있는 모든 코멘트 게시물 가져오기
	CommentsDAO cdao = new CommentsDAO();
	List<Comments> list = cdao.selectAll(Integer.parseInt(news_id));

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>뉴스 등록 폼</title>
<style>
	table{
		width:70%;
	}
	td{
		border:2px solid #cccccc;
	}
	td input, textarea{
		width:97%;
		font-size:1.2em;
	}
	textarea{
		height:150px;
	}
	input[name='msg']{
		width:70%;
	}
	input[name='author']{
		width:15%;
	
	}
	p{
		display:inline-block;
		background:pink;
	}
	.msg{
		width:70%;
	}
	.author{
		width:10%;
	}
	.cdate{
		width:18%;
	}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
$(function(){
	$($("button")[0]).click(function(){ //글 수정
		if(confirm("수정하시겠습니까?")){
			edit();
		}
	});
	$($("button")[1]).click(function(){ //삭제
		if(confirm("삭제하시겠습니까?")){
			del();
		}
	});	
	$($("button")[2]).click(function(){ //목록
		location.href="list.jsp";
		
	});	
	$($("button")[3]).click(function(){ //댓글 등록
		reply();
	});	
	
});

	function edit(){
		$("form").attr({
			method:"post",			
			action:"edit.jsp"
		});
		$("form").submit();
		
	}

	function del(){
		//자식 코멘트가 존재한다면, 업데이트!!
		<%if(list.size()>0){%>
			$("form").attr({
			method:"post",			
			action:"replace.jsp"
		});
			
		<%}else{%>
		
		//자식코멘트가 없다면 그냥 삭제
		$("form").attr({
			method:"post",			
			action:"delete.jsp"
		});
		<%}%>
		$("form").submit();
		
	}
	
	//폼양ㅇ식을 서버에 전송하자!!
	function regist(){
		$("form").attr({
			method:"post",			
			action:"regist.jsp"
		});
		$("form").submit();
		
	}
	
	function reply(){
		$("form").attr({
			method:"post",			
			action:"reply.jsp"
		});
		$("form").submit();
		
		
	}
</script>
</head>
<body>
	<form>
	<input type="hidden" name="news_id" value="<%=news.getNews_id()%>">
	<table align="center">
		<tr>
			<td><input type="text" name="writer" value="<%=news.getWriter()%>"></td>
		</tr>
		<tr>
			<td><input type="text" name="title" value="<%=news.getTitle()%>"></td>
		</tr>
		<tr>
			<td><textarea name="content"><%=news.getContent() %></textarea></td>
		</tr>
		
		<tr>
			<td align="center">
				<button type="button">수정</button>
				<button type="button">삭제</button>
				<button type="button">목록</button>
			</td>
		</tr>
		
		<tr>
			<td>
				<div >
					<input type="text" placeholder="댓글 입력" name="msg">
					<input type="text" placeholder="작성자 입력" name="author">
					<button type="button">등록</button>
				</div>
			</td>
		</tr>		
		
		<tr>
			<!-- 댓글 리스트 영역 -->
			<td>
			<%for(Comments comments : list ){ %>
				<div>
					<p class="msg"><%=comments.getMsg() %></p>
					<p class="author"><%=comments.getAuthor() %></p>
					<p class="cdate"><%=comments.getCdate().substring(0,10) %></p>
				</div>
				<%} %>
			</td>
		</tr>
		
	</table>
	</form>
</body>
</html>