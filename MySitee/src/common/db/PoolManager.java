/*
 * 커넥션 풀 사용을 보다 편하게 처리해야 DAO들에서 고생 안한다!
 * */
package common.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class PoolManager {
	//JNDI검색을 담당하는 객체
	InitialContext context;
	DataSource ds; //커넥션 풀
	private static PoolManager instance;
	
	//이 시점부터는 아무도 new 할 수 없다
	private PoolManager() {
		try {
			context = new InitialContext();	//검색객체 생성
			ds = (DataSource)context.lookup("java:comp/env/jdbc/myoracle"); //찾기 성공 and 풀반환
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
	}
	
	//하지만 클래스는 쓰라고 만든것이기 때문에 인스턴스를 가져갈 기회를 현재 클래스에서 부담하자
	public static PoolManager getInstance() {
		if(instance==null) {
			instance = new PoolManager();	//오직 나만이 new할수있다!!
		}
		return instance;
	}
	
	
	//커넥션이 필요한 자에게 connection을 반환(대여)해주는 메서드
	public Connection getConnection() {
		Connection con = null;
		
		try {
			con = ds.getConnection();	//대여!!
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return con;
	}
	
	//반납
	public void release(Connection con) {
		if(con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void release(Connection con,PreparedStatement pstmt) {
		if(con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(pstmt!=null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void release(Connection con,PreparedStatement pstmt,ResultSet rs) {
		if(con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(pstmt!=null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
