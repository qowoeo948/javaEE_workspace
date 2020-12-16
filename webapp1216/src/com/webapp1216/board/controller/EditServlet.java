/*
 * 수정 요청을 처리하는 컨트롤러 
 * */
package com.webapp1216.board.controller;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.webapp1216.board.model.Notice;
import com.webapp1216.board.model.NoticeDAO;
public class EditServlet extends HttpServlet{
	NoticeDAO noticeDAO = new NoticeDAO();
	
	//post방식으로 전송되어 온다..
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");//파라미터에 대한 인코딩처리 
		String title=request.getParameter("title");
		String writer=request.getParameter("writer");
		String content=request.getParameter("content");
		String notice_id=request.getParameter("notice_id");
		
		Notice notice = new Notice();//empty vo 생성 
		notice.setTitle(title);
		notice.setWriter(writer);
		notice.setContent(content);
		notice.setNotice_id(Integer.parseInt(notice_id));
		
		//DAO에게 수정일 시키자!!
		int result = noticeDAO.update(notice);
		
		HttpSession session = request.getSession();
		
		if(result==0) {
			session.setAttribute("msg", "수정에 실패하였습니다");
			response.sendRedirect("/error/message.jsp");
		}else {
			//response.sendRedirect("/board/detail?notice_id="+notice.getNotice_id());
			response.sendRedirect("/board/list");
		}
	}
}

