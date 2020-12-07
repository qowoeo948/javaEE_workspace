package board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBManager;

public class QnADAO {
	DBManager dbManager = new DBManager();
	
	//insert: 원글 등록 (팀장이 쓴글)
	public int insert(QnA qna) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "insert into qna(writer,title,content) values(?,?,?)";
		int result = 0;
		
		con = dbManager.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, qna.getWriter());
			pstmt.setString(2, qna.getTitle());
			pstmt.setString(3, qna.getTitle());
			result = pstmt.executeUpdate();
			
			//team을 방금 들어간 레코드에 의해 발생한 pk값으로 업데이트!!
			sql="update qna set team=(select last_insert_id()) where qna_id=(select last_insert_id())";
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(con,pstmt);
		}
		
		return result;
		
	}
	//답변글
	public int reply() {
		int result=0;
		
		return result;
		
	}
	
	
	//selectAll
	public ArrayList selectAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select *from qna order by team asc, rank asc";
		ArrayList<QnA> list = new ArrayList<QnA>();
		con = dbManager.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				QnA qna = new QnA();
				qna.setQna_id(rs.getInt("qna_id"));
				qna.setWriter(rs.getString("writer"));
				qna.setTitle(rs.getString("title"));
				qna.setContent(rs.getString("content"));
				qna.setRegdate(rs.getString("regdate"));
				qna.setHit(rs.getInt("hit"));
				qna.setRank(rs.getInt("rank"));
				qna.setTeam(rs.getInt("team"));
				qna.setDepth(rs.getInt("depth"));
				
				list.add(qna);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	//select
	public QnA select(int qna_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		QnA qna =null;
		String sql = "select *from qna where qna_id=?";
		con = dbManager.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, qna_id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				qna = new QnA();
				qna.setQna_id(rs.getInt("qna_id"));
				qna.setWriter(rs.getString("writer"));
				qna.setTitle(rs.getString("title"));
				qna.setContent(rs.getString("content"));
				qna.setRegdate(rs.getString("regdate"));
				qna.setHit(rs.getInt("hit"));
				qna.setRank(rs.getInt("rank"));
				qna.setTeam(rs.getInt("team"));
				qna.setDepth(rs.getInt("depth"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return qna;
		
	}
	
	//update
	public int update() {
		int result=0;
		
		return result;
		
	}
	//delete
	public int delete() {
		int result=0;
		
		return result;
		
	}
}
