/*
 * 글 1건 요청을 처리하는 컨트롤러
 * 
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

public class DetailServlet extends HttpServlet{
	NoticeDAO noticeDAO = new NoticeDAO();
	//get:(목록보기,상세보기)				post:(등록,수정요청)
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String notice_id = request.getParameter("notice_id");
		Notice notice = noticeDAO.select(Integer.parseInt(notice_id));
		HttpSession session = request.getSession();
		session.setAttribute("notice", notice);
		//클라이언트가 재접속을 해도, 세션에 담아놓으면 notice에 대해 참조가 가능하다
		
		response.sendRedirect("/board/detail.jsp");
	}
}
