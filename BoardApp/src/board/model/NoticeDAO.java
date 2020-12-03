/*
 * DAO�� ?
 * - Data Access Object�� �ǹ��ϴ� ���ø����̼��� ���� �о� ���
 * - Data Access�� �����ͺ��̽����� CRUD�۾��� �����Ѵٴ� �ǹ�
 * 					Create(=insert),Read(=select) U(=update)D(=delete)�۾�
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

	// ���뼺 ������� �ʴ� swing���� ���� �ۼ�
	public int regist(Notice notice) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "insert into notice(author,title,content) values(?,?,?)";
		int result = 0; // �� ��� �� �� ����� ����

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
	//��� ���ڵ� ��������
	public ArrayList selectAll() {
		Connection con =null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		
		//rs���� ��ü�� �༮
		ArrayList<Notice> list = new ArrayList<Notice>();
		
		con = dbManager.getConnection();
		String sql="select *from notice order by notice_id desc";
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			//rs�� ���ڵ尡 ���� �� �̹Ƿ�, �� ������ �̹Ƿ� VO���� �������� �ʿ��ϰ�,
			//�� VO�� �Ѳ����� ��Ƽ� ��ȯ�ؾ� �ϱ� ������ ������ �ڷ����� �ʿ��ϴ�
			//��ü�� ��Ƴ��� �����ӿ� CollectionFramework�̹Ƿ�, ����  ArrayList
			while(rs.next()) {
				Notice notice = new Notice(); //�ֺ� empty������ vo����
				//�ۿ��� �ϴ°ź��� �ȿ����ؾ��� new�� ������ŭ ����ߵǴϱ�
				//���������� �Ǵϱ� �ȿ��� �ν��Ͻ��� �����ؾߵȴ�
				
				//notice�� rs�� ������ ���~~ �Űܽ���!!
				notice.setNotice_id(rs.getInt("notice_id"));
				notice.setAuthor(rs.getString("author"));
				notice.setTitle(rs.getString("title"));
				notice.setContent(rs.getString("content"));
				notice.setRegdate(rs.getString("regdate"));
				notice.setHit(rs.getInt("hit"));
				
				//notice������ ����������� �� �������� list�� ����
				list.add(notice);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(con,pstmt,rs);
		}
		
		return list; //rs�� �ƴ� ArrayList�� ��ȯ����
	}
	
	
	
	
	//�Խù� 1�� ��������  (�󼼺���)
	public Notice select(int notice_id) {
		String sql = "select *from notice where notice_id=?";
		Connection con =null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		Notice notice = null; //rs��� ������ 1���� ���� ��ü
		
		try {
			con = dbManager.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, notice_id);
			rs = pstmt.executeQuery();
			
			//���� ź���� rs�� �� �״´�. ���� rs�� ��ü �� ��ü�� �ʿ��ϴ�!!
			//rs�� ���ڵ� �Ѱ��� ��� ��ü�̹Ƿ�, ���ڵ� 1���� ��� ���޿����� ���Ǵ� VO�� �̿�����
			if(rs.next()) { //���ڵ尡 �����Ѵٸ� !! ���� �̋� vo�� �ø���!
				notice = new Notice(); //�ֺ� empty������ vo����
				//notice�� rs�� ������ ���~~ �Űܽ���!!
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
