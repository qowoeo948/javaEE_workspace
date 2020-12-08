<%@page import="board.model.QnA"%>
<%@page import="board.model.QnADAO"%>
<%@page import="board.model.Notice"%>
<%@page import="board.model.NoticeDAO"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="java.sql.*"%>

<%
	String qna_id = request.getParameter("qna_id");

	String sql="select *from qna where qna_id="+qna_id;
	
	out.print("실행될 예정 SQL은 "+sql);

	QnADAO qnaDAO = new QnADAO();
	QnA qna = qnaDAO.select(Integer.parseInt(qna_id));

%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {font-family: Arial, Helvetica, sans-serif;}
* {box-sizing: border-box;}

input[type=text], select, textarea {
  width: 100%;
  padding: 12px;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
  margin-top: 6px;
  margin-bottom: 16px;
  resize: vertical;
}

input[type=button] {
  background-color: #4CAF50;
  color: white;
  padding: 12px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

input[type=button]:hover {
  background-color: #45a049;
}

.container {
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 20px;
}

textarea{
	background-color:yellow;
}

</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdn.ckeditor.com/4.15.1/standard/ckeditor.js"></script>
<script>
/*
	GET : Http프로토콜에서 헤더 정보에 데이터를 실어 나른다. 아무래도 헤더이다보니, 
				전송할 수 있는 그 양이 미미하다.
				현실비유) 편지봉투에 데이터 노출하여 보내는 꼴임
				
	POST: Http프로토콜에서 바디영역에 데이터를 실어 나른다.몸체이다 보니 전송량에 한계가 없다..
			현실비유) 편지지에 데이터를 숨겨 보내는 꼴임
*/

	$(function(){
		CKEDITOR.replace( "subject" ); 
		
		$($("input[type='button']")[0]).click(function(){	//목록으로
		location.href="/qna/list.jsp";
		});
	
		$($("input[type='button']")[1]).click(function(){	//수정요청
			if(confirm("수정하시겠어요?")){
			$("form").attr({
				method:"post",
				action:"/qna/edit.jsp"
			});
			$("form").submit(); //전송행위
			}	
		});
		
	
		$($("input[type='button']")[2]).click(function(){	//삭제요청
			
			if(confirm("삭제하시겠습니까?")){
			$("form").attr({
				method:"post",
				action:"/qna/delete.jsp"
			});
			$("form").submit(); //전송행위
			}
		});
		
		$($("input[type='button']")[3]).click(function(){	//삭제요청
			
			$("form").attr({
				method:"post",
				action:"/qna/reply_form.jsp"
			});
			$("form").submit(); //전송행위
			
		});
		

	});


</script>

</head>
<body>

<h3 style="text-align:center">Contact Form</h3>

<div class="container">
  <form>
	 <input  type="hidden" name="qna_id" value="<%=qna.getQna_id()%>">
	 <input  type="hidden" name="team" value="<%=qna.getTeam()%>">
	 <input  type="hidden" name="rank" value="<%=qna.getRank()%>">
	 <input  type="hidden" name="depth" value="<%=qna.getDepth()%>">

	<label for="fname">First Name</label>
    <input type="text" id="fname" name="writer" value="<%=qna.getWriter()%>">

    <label for="lname">title</label>
    <input type="text" id="lname" name="title" value="<%=qna.getTitle()%>">

	<label for="subject">Content</label>
    <textarea id="subject" name="content" placeholder="Write something.." style="height:200px"><%=qna.getContent()%></textarea>
   
	<input type="button" value="목록으로">
	<input type="button" value="수정하기">
	<input type="button" value="삭제하기">
	<input type="button" value="답글달기">

  </form>
</div>

<div style="text-align:center">
		<%@ include file="/inc/footer.jsp"%>
</div>

</body>
</html>
