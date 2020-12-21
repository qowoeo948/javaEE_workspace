/*
 * 코멘트 게시판의 리스트 요청을 처리하는 컨트롤러,
 * 이 컨트롤러는 서블릿이 아니다, 단지 서블릿으로 전달받은 요청,응답 객체를
 * 넘겨봤았으므로, 이 객체들을 제어할 수 있을 뿐이다.
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
		System.out.println("코멘트 게시판 목록 요청을 처리합니다.");
		//3단계: 알맞는 로직 객체에 일 시키기
		List boardList = boardDAO.selectAll();
		
		//4단계: 저장할 것이 있기 때문에 request에 저장
		request.setAttribute("boardList", boardList);
	}

	@Override
	public String getResultView() {
		return "/view/board/list";
	}

	@Override
	public boolean isForward() {
		return true;	//저장한 것이 있으므로, 요청은 유지되어야 함. 요청을 유지하려면 포워딩해야함
	}

}
