/*
 * 모든 하위 컨트롤러를 가리킬 수 있는 최상위 컨트롤러 클래스 정의
 * 
 * */
package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * 추상클래스도 가능은 하지만, 하위 클래스들이 이미 다른 객체를 상속받았을 수도 있으므로 , 다중 상속에 
 * 제한을 받지 않으려면 인터페이스가 훨씬 더 유용하다!!
 * */
public interface Controller {
	
	//알맞는 로직객체에 일시키기
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException ;
	
	//어떤 뷰를 보여줘야 할지
	public String getViewPage();
	
}
