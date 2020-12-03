<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="db.DBManager"%>
<%@ page import="java.sql.*"%>

<%@ include file="/inc/lib.jsp"%>

<%
//수정 요청을 처리하는 jsp..수정 후 상세보기 페이지로 전환할 것 이므로,
//디자인이 필요없고 오직 db로직만 있으면 됨

	request.setCharacterEncoding("utf-8");//전송파라미터들에 대한 인코팅 처리 한글안꺠지게

	String author= request.getParameter("author");
	String title= request.getParameter("title");
	String content= request.getParameter("content");
	String notice_id= request.getParameter("notice_id");

	String sql = "update notice set author=' "+author+" ', title=' "+title+" ', content=' "+content+" ' ";
	sql+=" where notice_id="+notice_id;

	out.print("수정에 사용할 sql문은: "+sql);

	DBManager dbManager = new DBManager();

	Connection con = null;
	PreparedStatement pstmt=null;

	con = dbManager.getConnection();

	pstmt = con.prepareStatement(sql);	//쿼리준비

	int result = pstmt.executeUpdate();

	if(result==0){
		out.print(getMsgBack("수정실패"));
	
	}else{
		out.print(getMsgURL("수정성공","/board/detail.jsp?notice_id="+notice_id));
	}

		dbManager.release(con,pstmt);

%>
