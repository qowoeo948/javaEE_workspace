<%@ page contentType="text/html;charset=utf-8"%>
<%!
	//앞으로 재사용성이 높은 jsp코드를 이 파일에 메서드로 모아놓겠다!!
	
	//선언부에는 멤버변수와 메서드를 정의할 수 있다.
	
	public String getMsgBack(String msg){	//욕하고, 이전페이지 보여주기
		StringBuilder sb = new StringBuilder();
		sb.append("<script>");
		sb.append("alert(' "+msg+" ');");
		sb.append("history.back();");
		sb.append("</script>");
		
		return sb.toString();
	}

	///board/detail.jsp?notice_id="+notice_id+" 
	public String getMsgURL(String msg, String url){ //욕하고 원하는 페이지 요청
		StringBuilder sb = new StringBuilder();
		sb.append("<script>");
		sb.append("alert(' "+msg+" ');");
		sb.append("location.href=' "+url+" ';");
		sb.append("</script>");
		
		return sb.toString();
	}

%>