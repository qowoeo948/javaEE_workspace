<%@page import="board.model.ImageBoard"%>
<%@page import="board.model.ImageBoardDAO"%>
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
	ImageBoardDAO dao=new ImageBoardDAO();
%>
<%
	//실습햇던 예제보다 기능이 더 추가됨, db에 넣어야 함.. DAO이용
	
	//업로드컴포넌트에 대한 설정을 하기 위해 FileItemFactory객체를 이용해야 한다..
	DiskFileItemFactory itemFactory=new DiskFileItemFactory();
	itemFactory.setRepository(new File(saveDir));
	itemFactory.setSizeThreshold(maxSize);
	itemFactory.setDefaultCharset("utf-8");
	
	ServletFileUpload upload=new ServletFileUpload(itemFactory);
	
	//업로드된 정보 분석!!! 각각의 컴포넌트들을  FileItem 단위로 쪼갠다..
	request.setCharacterEncoding("utf-8"); //다국어 인코딩
	List<FileItem> items=upload.parseRequest(request);
	
	ImageBoard board = new ImageBoard();//Empty상태의 VO 생성 
	
	for(FileItem item : items){
		if(item.isFormField()){ //textfield 라면...db에 넣어야지
			//vo 에 텍스트필드들의 값을 담자!!
			if(item.getFieldName().equals("board_id")){//필드명이 board_id 라면...
				board.setBoard_id(Integer.parseInt(item.getString()));
			}if(item.getFieldName().equals("author")){//필드명이 author 라면...
				board.setAuthor(item.getString());
			}else if(item.getFieldName().equals("title")){//필드명이 title 라면...
				board.setTitle(item.getString());
			}else if(item.getFieldName().equals("content")){//필드명이 content 라면...
				board.setContent(item.getString());
			}else if(item.getFieldName().equals("filename")){//필드명이 filename 라면...
				board.setFilename(item.getString());
			}
		}else{// textfield가 아니라면..업로드 처리
			//out.println("업로드한 파일명:"+item.getName().length());
			//사용자가 파일을 업로드 했다면..
			if(item.getName().length()>0){ //파일을 교체한다면, 즉 업로드 하길 원한다면...
				String newName=System.currentTimeMillis()+"."+FileManager.getExtend(item.getName());
				String destFile = saveDir+"/"+newName;
				File file = new File(destFile);
				item.write(file);//물리적 저장 시점!!!	
				board.setFilename(newName);//vo 에 파일명 값을 담자!!			
			}
		}
	}
	int result = dao.update(board); //이 시점에는 채워진 VO를 원함!!
	
	if(result==0){
		out.print(getMsgBack("수정실패"));
	}else{
		out.print(getMsgURL("수정성공", "/imageboard/list.jsp"));
	}
	
%>