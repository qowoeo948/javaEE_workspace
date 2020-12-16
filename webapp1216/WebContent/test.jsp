<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%

		//데이터베이스와 연결이 된다면 모든 설정이 완료된 것임
		
		InitialContext context = new InitialContext();		//jndi검색 객체
		
		DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/mysql"); //톰캣 서버에 설정한 리소스를 찾자!

		//찾아낸 커넥션 풀을 이용하여 커넥션 한개를 끄집어내고 주소값이 나오면 성공한 거임
		Connection con = ds.getConnection();
		out.print(con);
%>