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
	
	//insert: ���� ��� (������ ����)
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
			
			//team�� ��� �� ���ڵ忡 ���� �߻��� pk������ ������Ʈ!!
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
	
	//Ʈ������̶�?
	//���ξ����� ��� �����ؾ�, ��ü�� �������� �����ϴ� ���� ���� ���� ����
	
	//�亯��
	public int reply(QnA qna) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result=0;
		String sql="update qna set rank=rank+1 where team=? and rank > ?";
		
		con = dbManager.getConnection();
		try {											//������ �ڵ� Ŀ�� true�� �Ǿ�����
			con.setAutoCommit(false); //�ڵ����� Ŀ��������!!(SQLplusó�� ���� �����Ұ�!!)
			
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
			pstmt.setInt(5, qna.getRank()+1);	//������ ������ ��ġ�� ���̹Ƿ� +1
			pstmt.setInt(6, qna.getDepth()+1);	//�����ۿ� ���� �亯�̹Ƿ� +1
			
			result = pstmt.executeUpdate();
			
			con.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();//�� ������ �� ������ �ϳ��� �߻��ϸ�, ���� ó������ ������ �� �� ����
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally {
			try {
				con.setAutoCommit(true); //���󺹱�
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
