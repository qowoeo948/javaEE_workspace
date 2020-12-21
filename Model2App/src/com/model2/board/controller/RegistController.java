/*
 * 글쓰기 요청을 처리하는 컨트롤러
 * */
package com.model2.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.board.model.BoardDAO;
import com.model2.controller.Controller;
import com.model2.domain.Board;

public class RegistController implements Controller{
	BoardDAO boardDAO = new BoardDAO();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String title=request.getParameter("title");
		String writer=request.getParameter("writer");
		String content=request.getParameter("content");
		
		Board board = new Board();
		board.setTitle(title);
		board.setWriter(writer);
		board.setContent(content);
		
		boardDAO.insert(board);		//3단계: 알맞는 로직 객체에 일시키기.
	
		//4단계: 저장할 것이 없다.
	}

	@Override
	public String getResultView() {
		return "/view/board/regist";
	}

	@Override
	public boolean isForward() {
		//(클라이언트로 하여금 , 지정한 url로 재접속을 하도록 유도하자)
		return false;
	}

}
