/*
 * �ڸ�Ʈ �Խ����� ����Ʈ ��û�� ó���ϴ� ��Ʈ�ѷ�,
 * �� ��Ʈ�ѷ��� ������ �ƴϴ�, ���� �������� ���޹��� ��û,���� ��ü��
 * �Ѱܺþ����Ƿ�, �� ��ü���� ������ �� ���� ���̴�.
 * */
package com.model2.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.board.model.BoardDAO;
import com.model2.controller.Controller;

public class ListController implements Controller{
	BoardDAO boardDAO = new BoardDAO();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("�ڸ�Ʈ �Խ��� ��� ��û�� ó���մϴ�.");
		//3�ܰ�: �˸´� ���� ��ü�� �� ��Ű��
		List boardList = boardDAO.selectAll();
		
		//4�ܰ�: ������ ���� �ֱ� ������ request�� ����
		request.setAttribute("boardList", boardList);
	}

	@Override
	public String getResultView() {
		return "/view/board/list";
	}

	@Override
	public boolean isForward() {
		return true;	//������ ���� �����Ƿ�, ��û�� �����Ǿ�� ��. ��û�� �����Ϸ��� �������ؾ���
	}

}
