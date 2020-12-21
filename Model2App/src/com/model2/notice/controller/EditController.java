package com.model2.notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.controller.Controller;
import com.model2.domain.Notice;
import com.model2.notice.model.NoticeDAO;

public class EditController implements Controller{
	NoticeDAO noticeDAO = new NoticeDAO();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		String notice_id = request.getParameter("notice_id");
		
		Notice notice = new Notice();
		notice.setTitle(title);
		notice.setWriter(writer);
		notice.setContent(content);
		notice.setNotice_id(Integer.parseInt(notice_id));
		
		int result = noticeDAO.update(notice);
	
	}

	@Override
	public String getResultView() {
		return "/view/notice/edit";
	}

	@Override
	public boolean isForward() {
		return false;
	}

}
