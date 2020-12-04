/*
 * ImageBoard 테이블에 대한 CRUD만을 전담하는 DAO정의
 * */
package board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DBManager;

public class ImageBoardDAO {
	DBManager dbManager = new DBManager();
	
	//create(insert)
	public int insert(ImageBoard board) {
		Connection con =null;
		PreparedStatement pstmt=null;
		String sql = "insert into imageboard(author,title,content,filename)";
		sql+=" values(?,?,?,?)";

		int result=0;
		con=dbManager.getConnection();
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, board.getAuthor());
			pstmt.setString(2, board.getTitle());
			pstmt.setString(3, board.getContent());
			pstmt.setString(4, board.getFilename());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(con,pstmt);
		}
		
		return result;
		
		
	}
	//selectAll()
	public void selectAll() {
		String sql = "select *from imageboard";
		
	}
	//select
	public void select() {
		String sql = "select *from imageboard where board_id=?";
		
	}
	//update
	public void update() {
		String sql = "update imageboard set author=?, title=?, content=? where board_id=?";
		
	}
	//delete
	public void delete() {
		String sql = "delete from imageboard where board_id=?";
		
	}
}
