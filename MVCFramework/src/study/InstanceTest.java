/*
 * 자바에서 클래스의 인스턴스를 생성하는 방법에는 new연산자만 있는것은 아니다!!
 * */
package study;

import java.lang.reflect.Method;

public class InstanceTest {
	public static void main(String[] args) {
		try {
			Class dogClass = Class.forName("study.Dog"); //클래스 로드
			System.out.println("로드 성공");
			Method[] methods = dogClass.getMethods();
			
			for(Method method : methods) {
				System.out.println(method.getName());
			}
			
			//Dog클래스를 .new연산자 쓰지 않고 올려보자
			Dog dog = (Dog)dogClass.newInstance();
			System.out.println(dog.getName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("로드 실패");
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}
