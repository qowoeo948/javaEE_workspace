<%@page import="board.model.Comments"%>
<%@page import="java.util.List"%>
<%@page import="board.model.CommentsDAO"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
	//넘겨받은 news_id 파라미터값으로, 하위에 등록된 모든 코멘트 목록 가져온후, 
	//json문자열로 응답하자!!
	String news_id = request.getParameter("news_id");
	CommentsDAO dao = new CommentsDAO();
	List<Comments> list=dao.selectAll(Integer.parseInt(news_id));
	
	StringBuilder sb = new StringBuilder();
	sb.append("{");
	sb.append("\"resultCode\":"+1+", ");
	sb.append("\"commentsList\" : [");
	
	for(int i=0;i<list.size();i++){
		Comments obj=list.get(i);
		sb.append("{");
		sb.append("\"comments_id\":"+obj.getComments_id()+",");
		sb.append("\"author\":\""+obj.getAuthor()+"\",");
		sb.append("\"msg\":\""+obj.getMsg()+"\",");
		sb.append("\"cdate\":\""+obj.getCdate().substring(0,10)+"\"");
		
		if(i <list.size()-1){
			sb.append("},");
		}else{
			sb.append("}");
		}
	}
	sb.append("]");
	sb.append("}");		
	out.print(sb.toString());
%>