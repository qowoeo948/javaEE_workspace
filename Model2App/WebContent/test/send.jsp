<%@ page contentType="text/html;charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
</style>
<script>
	function send(){
		var form = document.querySelector("form");
		form.action="/test/receive.jsp";
		form.submit();
	}
</script>
</head>
<body>
	<form>
		<input type="text" name="msg">
		<button type="button" onClick="send()">요청하기</button>
	</form>
</body>
</html>