/*
	기존의 jsp가 담당하고 잇었던 컨트롤러로서의 업무를 현재 클래스로 분리시키자!!
	그래야 jsp는 순수한 디자인이 되기 때문에 유지보수시 교체까지 가능하다..
*/
package blood.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.controller.Controller;

import blood.model.BloodAdvisor;

public class BloodController  implements Controller{
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//파라미터 받기 
		String blood = request.getParameter("blood");
		BloodAdvisor advisor = new BloodAdvisor();
		String msg=advisor.getAdvice(blood);
		
		//결과에 대한 출력은 디자인인 View 가 담당하므로, 이 서블릿에서 처리하면 안된다!!
		//결과 jsp 에서 메세지를 보여주려면, 서버의 메모리에 임시적으로 저장해놓을 필요가 잇다
		//현재로서는 세션에 담자 
		HttpSession session=request.getSession();
		session.setAttribute("msg", msg);
		
	}
	
	@Override
	public String getViewPage() {
		return "/blood/blood_result.jsp";
	}
	
}







