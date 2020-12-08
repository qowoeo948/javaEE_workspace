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
	
	//트랜잭션이란?
	//세부업무가 모두 성공해야, 전체를 성공으로 간주하는 논리적 업무 수행 단위
	
	//답변글
	public int reply(QnA qna) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result=0;
		String sql="update qna set rank=rank+1 where team=? and rank > ?";
		
		con = dbManager.getConnection();
		try {											//원래는 자동 커밋 true로 되어있음
			con.setAutoCommit(false); //자동으로 커밋하지마!!(SQLplus처럼 내가 결정할게!!)
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, qna.getTeam());
			pstmt.setInt(2, qna.getRank());
			
			result = pstmt.executeUpdate();

			sql="insert into qna(writer,title,content,team,rank,depth) values(?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, qna.getWriter());
			pstmt.setString(2, qna.getTitle());
			pstmt.setString(3, qna.getContent());
			pstmt.setInt(4, qna.getTeam());
			pstmt.setInt(5, qna.getRank()+1);	//내본글 다음에 위치할 것이므로 +1
			pstmt.setInt(6, qna.getDepth()+1);	//내본글에 대한 답변이므로 +1
			
			result = pstmt.executeUpdate();
			
			con.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();//두 쿼리문 중 에러가 하나라도 발생하면, 차라리 처음부터 없었던 일 로 하자
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally {
			try {
				con.setAutoCommit(true); //원상복귀
			} catch (SQLException e) {
				e.printStackTrace();
			}
			dbManager.release(con,pstmt);
		}
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
		}finally {
			dbManager.release(con,pstmt,rs);
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
		}finally {
			dbManager.release(con,pstmt,rs);
		}
		
		return qna;
		
	}
	
	//update
	public int update(QnA qna) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "update qna set writer=?, title=?, content=? where qna_id=?";
		int result=0;
		con = dbManager.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, qna.getWriter());
			pstmt.setString(2, qna.getTitle());
			pstmt.setString(3, qna.getContent());
			pstmt.setInt(4, qna.getQna_id());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(con,pstmt);
		}
		
		
		return result;
		
	}
	//delete
	public int delete(int qna_id) {
		Connection con =null;
		PreparedStatement pstmt = null;
		String sql = "delete from qna where qna_id=?";
		int result=0;
		
		con = dbManager.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, qna_id);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(con,pstmt);
		}
		
		return result;
		
	}
}
