<%@ page contentType="text/html;charset=utf-8"%>
<%
	//상세보기 페이지의 히든을 통해 전송된 파라미터 중,
	//team,rank,depth를 다시 현재 페이지의 hidden에 넣어두자
		request.setCharacterEncoding("utf-8");
	
		String team = request.getParameter("team");	//내본글 team
		String rank = request.getParameter("rank");	//내본글 rank
		String depth = request.getParameter("depth");	//내본글 depth
	
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
  background-color: #ffdd00;
  padding: 20px;
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
	
	$(function(){ //onload
		CKEDITOR.replace( "subject" ); //textarea에 부여한 id를 넣어야돼
		
		$($("input[type='button']")[0]).click(function(){	
			history.back();
			
		});
		
		$($("input[type='button']")[1]).click(function(){	
			
			$("form").attr({
				method:"post",
				action:"/qna/reply.jsp"
			});
			$("form").submit(); //전송행위
			
		});
		
	});

</script>

</head>
<body>

<h3 style="text-align:center">답변 등록</h3>

<div class="container">
  <form>
    <input type="hidden" name="team" placeholder="team" value="<%=team%>">
    <input type="hidden" name="rank" placeholder="rank" value="<%=rank%>">
    <input type="hidden" name="depth" placeholder="depth" value="<%=depth%>">

    <label for="fname">First Name</label>
    <input type="text" id="fname" name="writer" placeholder="Your name..">


    <label for="lname">title</label>
    <input type="text" id="lname" name="title" placeholder="Your title..">

    <label for="subject">Content</label>
    <textarea id="subject" name="content" placeholder="Write something.." style="height:200px"></textarea>

    <input type="button" value="이전으로">
    <input type="button" value="답글등록">
  </form>
</div>

<div style="text-align:center">
	<%@ include file="/inc/footer.jsp"%>
</div>

</body>
</html>
