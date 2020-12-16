package com.webapp1216.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.webapp1216.board.model.NoticeDAO;

public class DeleteServlet extends HttpServlet{
	NoticeDAO noticeDAO =new NoticeDAO();
	
	//������û�� get? post? ��¥�� �Ķ���Ͱ� notice_id�ۿ� �����Ƿ� get���� �غ���
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String notice_id = request.getParameter("notice_id");
		
		int result = noticeDAO.delete(Integer.parseInt(notice_id));
		
		//��Ʈ�ѷ��� Ŭ���̾�Ʈ�� ���Ե� �޽����� ó���ؾ� �Ѵ�? �ƴϴ�?
		//alert�� ������..
		HttpSession session = request.getSession();
		//result=0; result==0�϶� �������� �����
		
		if(result==0) {
			//������������ �����ش�. �ᱹ ������ ���� �ȳ��� �����ִ� �������� ������ �ΰ� �� �������� �����ش�
			session.setAttribute("msg", "���� �������� �ʾҽ��ϴ�. �����ڿ��� �������ּ���.�� ");
			response.sendRedirect("/error/message.jsp");
		}else {
			response.sendRedirect("/board/list");
		}
	}
}