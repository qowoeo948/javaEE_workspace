<%@ page contentType="text/html;charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
</style>
<script>
</script>
</head>
<body>
고양이가 발급한 세션 아이디는
<%
/*
 		jsp의 내장객체
 		-request,response,out,application,session
*/
	String id=session.getId();
	out.print(id);
%>

</body>
</html>