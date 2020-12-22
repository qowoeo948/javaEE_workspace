package com.model2.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.board.model.BoardDAO;
import com.model2.controller.Controller;

public class DeleteController implements Controller{
	BoardDAO boardDAO = new BoardDAO();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String board_id = request.getParameter("board_id");
		
		boardDAO.delete(Integer.parseInt(board_id));
		
	}

	@Override
	public String getResultView() {
		return "/view/board/delete";
	}

	@Override
	public boolean isForward() {
		return false;
	}

}
