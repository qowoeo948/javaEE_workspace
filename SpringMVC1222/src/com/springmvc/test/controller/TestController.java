package com.springmvc.test.controller;

import java.lang.annotation.Annotation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
/*
 * 이 클래스는 요청을 실제적으로 처리하는 컨트롤러 임을 명시
 * 추구하는 목표: pojo(Plain Old Java Object) 기반으로 가려는 경향이 강함..
 * */

public class TestController implements Controller{
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//3단계: 알맞는 로직객체에 일시킨다..
		String msg="안뇽";
		//4단계: 저장할 것이 있다면 request객체에 저장!
		ModelAndView mav = new ModelAndView();
		mav.addObject("msg",msg);	//모델
		//형님 컨트롤러가 어떤 페이지를 보여줘야 할지에 대한 정보는 여전히 
		mav.setViewName("/test/result");	//뷰

		return mav;
		
	}
	
}
