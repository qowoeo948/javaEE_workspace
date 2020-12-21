/*
 * ��� ��Ͽ�û�� ó���ϴ� ��Ʈ�ѷ�
 * */
package com.model2.comment.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.comment.model.CommentDAO;
import com.model2.controller.Controller;
import com.model2.domain.Comment;

public class RegistController implements Controller{
	CommentDAO commentDAO = new CommentDAO();
	
	//����� ����� �񵿱��û���� ������ ������, ���� ������ �������� �����ִ°� �ƴ϶�,
	//�����͸� �����ؾ� ��!
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//�Ķ���� ���� �Ѿ������ ���� Ȯ���غ���
		String msg = request.getParameter("msg");
		String author = request.getParameter("author");
		String board_id = request.getParameter("board_id");
		
//		System.out.println("msg: "+msg);
//		System.out.println("author: "+author);
//		System.out.println("board_id: "+board_id);
		
		//vo�� ���
		Comment comment = new Comment();
		
		comment.setMsg(msg);
		comment.setAuthor(author);
		comment.setBoard_id(Integer.parseInt(board_id));
		
		int result = commentDAO.insert(comment);
		
		//4�ܰ�:DML �������� �����ϰڴ�.
		request.setAttribute("result", result); //boxing..
		
	}

	@Override
	public String getResultView() {
		return "/view/comment/regist"; //��� ����� Ŭ���̾�Ʈ���� ������ jsp
	}

	@Override
	public boolean isForward() {
		return true;
	}

}
