<%@ page contentType="text/html;charset=utf-8"%>
<%
	int count=0;

	for(int i=0;i<1000;i++){
		count++;
	}
	
		out.print("서버의 실행결과"+count);	//응답객체가 보유한 스트림에 데이터 적재
	
%>