/*
 * 댓글 목록 요청을 처리하는 컨트롤러
 * 댓글목록은 클라이언트에게 페이지 재접속을 유도하면 안되며, 클라이언트가 받아서 화면에 출력할
 * 데이터만 보내야 한다!!
 * */
package com.model2.comment.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.comment.model.CommentDAO;
import com.model2.controller.Controller;

public class ListController implements Controller{
	CommentDAO commentDAO = new CommentDAO();
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//3단계: 코멘트 목록 가져오기
		String board_id = request.getParameter("board_id");
		List commentList = commentDAO.selectAll(Integer.parseInt(board_id));
		
		//4단계: 저장할 것이 있다면, 결과 저장(request에)
		request.setAttribute("commentList", commentList);
	}

	@Override
	public String getResultView() {
		return "/view/comment/list";
	}

	@Override
	public boolean isForward() {
		return true;
	}

}
