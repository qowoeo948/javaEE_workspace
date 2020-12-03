/*
 * DAO란 ?
 * - Data Access Object를 의미하는 어플리케이션의 설계 분야 용어
 * - Data Access란 데이터베이스와의 CRUD작업을 전담한다는 의미
 * 					Create(=insert),Read(=select) U(=update)D(=delete)작업
 * 
 * */
package board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.DBManager;

public class NoticeDAO {
	DBManager dbManager = new DBManager();

	// 재사용성 고려하지 않는 swing만의 로직 작성
	public int regist(Notice notice) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "insert into notice(author,title,content) values(?,?,?)";
		int result = 0; // 글 등록 후 그 결과값 보관

		try {
			con = dbManager.getConnection();
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, notice.getAuthor());
			pstmt.setString(2, notice.getTitle());
			pstmt.setString(3, notice.getContent());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.release(con, pstmt);
		}

		return result;

	}
	//모든 레코드 가져오기
	public ArrayList selectAll() {
		Connection con =null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		
		//rs들을 대체할 녀석
		ArrayList<Notice> list = new ArrayList<Notice>();
		
		con = dbManager.getConnection();
		String sql="select *from notice order by notice_id desc";
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			//rs는 레코드가 복수 개 이므로, 즉 여러개 이므로 VO또한 여러개가 필요하고,
			//이 VO를 한꺼번에 모아서 반환해야 하기 때문에 집합형 자료형이 필요하다
			//객체를 모아놓을 프레임웍 CollectionFramework이므로, 이중  ArrayList
			while(rs.next()) {
				Notice notice = new Notice(); //텅빈 empty상태의 vo생성
				//밖에서 하는거보다 안에서해야지 new를 갯수만큼 해줘야되니까
				//각각만들어야 되니까 안에서 인스턴스를 생성해야된다
				
				//notice에 rs의 정보를 모두~~ 옮겨심자!!
				notice.setNotice_id(rs.getInt("notice_id"));
				notice.setAuthor(rs.getString("author"));
				notice.setTitle(rs.getString("title"));
				notice.setContent(rs.getString("content"));
				notice.setRegdate(rs.getString("regdate"));
				notice.setHit(rs.getInt("hit"));
				
				//notice변수가 사라지기전에 얼른 만들어놓은 list에 담자
				list.add(notice);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(con,pstmt,rs);
		}
		
		return list; //rs가 아닌 ArrayList를 반환하자
	}
	
	
	
	
	//게시물 1건 가져오기  (상세보기)
	public Notice select(int notice_id) {
		String sql = "select *from notice where notice_id=?";
		Connection con =null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		Notice notice = null; //rs대신 데이터 1건을 담을 객체
		
		try {
			con = dbManager.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, notice_id);
			rs = pstmt.executeQuery();
			
			//지금 탄생한 rs는 곧 죽는다. 따라서 rs를 대체 할 객체가 필요하다!!
			//rs는 레코드 한건을 담는 객체이므로, 레코드 1건을 담아 전달용으로 사용되는 VO를 이용하자
			if(rs.next()) { //레코드가 존재한다면 !! 따라서 이떄 vo를 올리자!
				notice = new Notice(); //텅빈 empty상태의 vo생성
				//notice에 rs의 정보를 모두~~ 옮겨심자!!
				notice.setNotice_id(rs.getInt("notice_id"));
				notice.setAuthor(rs.getString("author"));
				notice.setTitle(rs.getString("title"));
				notice.setContent(rs.getString("content"));
				notice.setRegdate(rs.getString("regdate"));
				notice.setHit(rs.getInt("hit"));
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(con,pstmt,rs);
		}
		return notice;
		
	}
	
}
