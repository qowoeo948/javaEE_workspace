<%@page import="board.model.ImageBoardDAO"%>
<%@page import="board.model.ImageBoard"%>
<%@page import="common.FileManager"%>
<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@page import="java.util.List"%>
<%@page import="java.io.File"%>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/inc/lib.jsp" %>
<%!
	String saveDir="C:/eclipse-workspace/javaee_workspace/BoardApp/WebContent/data";
	int maxSize=3*1024*1024; //3M byte
	//실습햇던 예제보다 기능이 더 추가됨, db에 넣어야 함.. DAO이용
	ImageBoardDAO dao = new ImageBoardDAO();
%>
<%
	
	//업로드컴포넌트에 대한 설정을 하기 위해 FileItemFactory객체를 이용해야 한다..
	DiskFileItemFactory itemFactory=new DiskFileItemFactory();
	itemFactory.setRepository(new File(saveDir));
	itemFactory.setSizeThreshold(maxSize);
	
	ServletFileUpload upload=new ServletFileUpload(itemFactory);
	
	request.setCharacterEncoding("utf-8");
	//업로드된 정보 분석!!! 각각의 컴포넌트들을  FileItem 단위로 쪼갠다..
	List<FileItem> items=upload.parseRequest(request);
	
	ImageBoard board = new ImageBoard();	//이떄는 Empty상태 vo생성

	for(FileItem item : items){
		if(item.isFormField()){ //textfield 라면...db에 넣어야지
			if(item.getFieldName().equals("author")){ //필드명이 author라면~
			board.setAuthor(item.getString());
				
			}else if(item.getFieldName().equals("title")){
			board.setTitle(item.getString());
				
			}else if(item.getFieldName().equals("content")){
			board.setContent(item.getString());
				
			}
			
		}else{ // textfield가 아니라면..업로드 처리
			String newName=System.currentTimeMillis()+"."+FileManager.getExtend(item.getName());
			String destFile = saveDir+"/"+newName;
			File file = new File(destFile);
			item.write(file);//물리적 저장 시점!!!	
			
			out.print("업로드 완료");
			//vo에 파일명 값을 담자
			board.setFilename(newName);
			
		}
	}
		//반복문을 지나친 이 시점에서는 vo데이터가 이미 채워진 상태일 것이다.!
		int result = dao.insert(board); //이 시점에서는 채워진 VO를 원함!!
		if(result==0){
			out.print(getMsgBack("등록실패"));
		}else{
			out.print(getMsgURL("등록성공","/imageboard/list.jsp"));
			
		}
%>

