package com.exception;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ExApp {
	
	//throws는 현재메서드에서 해당예외를 처리하지 않고, 이 메서드를 호출한 자에게 떠넘기는것
	public void insert() throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;

		pstmt=con.prepareStatement("insert~~");
		pstmt.executeUpdate();
		
	}

	public static void main(String[] args) throws SQLException {
		ExApp app = new ExApp();
		app.insert();
	}
	
	//이렇게 계속 넘기고 에러가 발생하면 자바가상머신이 그냥 화면에 예외를 뿌려버림
	//에러가 나지 않으면 뭐 정상처리 됨 
}
