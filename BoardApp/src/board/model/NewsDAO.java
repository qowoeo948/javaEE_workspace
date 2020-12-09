package board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBManager;

public class NewsDAO {
	DBManager dbManager = new DBManager();
	
	public List selectAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder sb = new StringBuilder();
		sb.append("select  n.news_id as news_id, writer, title , regdate,hit, count(comments_id) as cnt");
		//sb.append(" from news n , comments c");
		//sb.append(" where n.news_id = c.news_id"); 
		sb.append(" from news n left outer join comments c");
		sb.append(" on n.news_id = c.news_id"); 
		sb.append(" group by n.news_id, writer, title, regdate, hit order by n.news_id desc");

		//String sql = "select *from news order by news_id desc";
		
		ArrayList<News> list = new ArrayList<News>();
		
		con = dbManager.getConnection();
		try {
			pstmt = con.prepareStatement(sb.toString());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				News news = new News();
				
				news.setNews_id(rs.getInt("news_id"));
				news.setWriter(rs.getString("writer"));
				news.setTitle(rs.getString("title"));
				news.setRegdate(rs.getString("regdate"));
				news.setHit(rs.getInt("hit"));
				news.setCnt(rs.getInt("cnt"));
				
				list.add(news);
			}
	
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(con,pstmt,rs);
		}
		
		return list;
		
	}
	
	public News select(int news_id) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		News news=null;
		
		con=dbManager.getConnection();
		String sql="select * from news where news_id=?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, news_id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				news = new News();
				news.setNews_id(rs.getInt("news_id"));
				news.setWriter(rs.getString("writer"));
				news.setTitle(rs.getString("title"));
				news.setContent(rs.getString("content"));
				news.setRegdate(rs.getString("regdate"));
				news.setHit(rs.getInt("hit"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(con, pstmt, rs);
		}		
		return news;
	}
	
	
	public int insert(News news) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "insert into news(writer,title,content) values(?,?,?)";
		int result=0;
		
		con = dbManager.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, news.getWriter());
			pstmt.setString(2, news.getTitle());
			pstmt.setString(3, news.getContent());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(con,pstmt);
		}
		
		return result;
	}
	
	public int update(News news) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "update news set writer=?, title=?, content=? where news_id=?";
		int result=0;
		
		con = dbManager.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, news.getWriter());
			pstmt.setString(2, news.getTitle());
			pstmt.setString(3, news.getContent());
			pstmt.setInt(4, news.getNews_id());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(con,pstmt);
		}
		
		return result;
	}
	
	
	
	public int delete(int news_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "delete from news where news_id=?";
		con = dbManager.getConnection();
		int result=0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, news_id);
			result = pstmt.executeUpdate();
					
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(con,pstmt);
		}
		
		return result;
	}
	
	//게시물 지우지 않고, 삭제된 게시물이라는 표시 처리
	public int replace(int news_id) {
		Connection con =null;
		PreparedStatement pstmt = null;
		String sql = "update news set title='작성자에 의해 삭제된 게시물입니다.', writer='', content='' where news_id=?" ;
		int result=0;
		
		con= dbManager.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, news_id);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			dbManager.release(con,pstmt);
		}
		
			
		return result;
	}
	
}
