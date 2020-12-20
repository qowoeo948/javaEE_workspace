package com.model2.notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.controller.Controller;
import com.model2.notice.model.NoticeDAO;

public class DeleteController implements Controller{
	NoticeDAO noticeDAO = new NoticeDAO();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String notice_id = request.getParameter("notice_id");
		
		int result = noticeDAO.delete(Integer.parseInt(notice_id));
		
	}

	@Override
	public String getResultView() {
		return "/view/notice/delete";
	}

	@Override
	public boolean isForward() {
		return false;
	}

}
