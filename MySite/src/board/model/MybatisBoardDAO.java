/*
 * 기존 전통적인 JDBC방식으로 작성했던 DAO의 CRUD메서드를 mybatis프레임웍을 이용
 * 하여 코드를 간략화 시켜보자
 * */
package board.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import mybatis.config.MybatisConfigManager;

public class MybatisBoardDAO {
	MybatisConfigManager configManager=MybatisConfigManager.getInstance(); 
	
	public int insert(Board board) {
		int result=0;
		SqlSession sqlSession = configManager.getSqlSession();
		result = sqlSession.insert("mybatis.mappers.BoardMapper.insert",board);
		sqlSession.commit();
		configManager.close(sqlSession);
		return result;
	}
	
	public List selectAll() {
		List list = null;
		SqlSession sqlSession = configManager.getSqlSession();
		list = sqlSession.selectList("mybatis.mappers.BoardMapper.selectAll");
		configManager.close(sqlSession);
		return list;
	}
	
	
	public Board select(int board_id) {
		Board board = null;
		SqlSession sqlSession = configManager.getSqlSession();
		board=sqlSession.selectOne("mybatis.mappers.BoardMapper.select",board_id);
		configManager.close(sqlSession);
		return board;
	}
	
	public int update(Board board) {
		int result=0;
		SqlSession sqlSession = configManager.getSqlSession();
		result = sqlSession.update("mybatis.mappers.BoardMapper.update", board);
		sqlSession.commit();
		configManager.close(sqlSession);
		return result;
	}
	
	public int delete(int board_id) {
		int result=0;
		SqlSession sqlSession = configManager.getSqlSession();
		result = sqlSession.delete("mybatis.mappers.BoardMapper.delete", board_id);
		sqlSession.commit();
		configManager.close(sqlSession);
		return result;
	}
	
	
	
	
}
