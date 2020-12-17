<%@ page contentType="text/html; charset=utf-8"%>
<%
	//service메서드 영역 고양이가 이 jsp로 부터 생성한 서블릿 클래스에서는
	//각 종 예외가 throws 처리되어있기 떄문에, jsp스크립틀릿 영역에서는
	//예외처리를 강요하지 않았던 것이다.
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
당신이 선택한 혈액형에 대한 분석결과 <p>
<%=session.getAttribute("msg") %>
<a href="/movie/movie_form.jsp">선택폼으로 가기</a>
</body>
</html>