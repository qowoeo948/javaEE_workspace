package com.model2.board.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.model2.domain.Board;
import com.model2.mybatis.config.MybatisConfigManager;

public class BoardDAO {
	MybatisConfigManager manager = MybatisConfigManager.getInstance();
	
	public List selectAll() {
		List list=null;
		SqlSession sqlSession = manager.getSqlSession();
		list=sqlSession.selectList("Board.selectAll");
		manager.close(sqlSession);
		return list;
	}
	
	public int insert(Board board) {
		int result=0;
		SqlSession sqlSession = manager.getSqlSession();
		result = sqlSession.insert("Board.insert", board);
		sqlSession.commit();
		manager.close(sqlSession);
		return result;
		
	}
	
	public Board select(int board_id) {
		Board board=null;
		SqlSession sqlSession = manager.getSqlSession();
		board = sqlSession.selectOne("Board.select", board_id);
		manager.close(sqlSession);
		return board;
		
	}
	
	public int update(Board board) {
		int result=0;
		SqlSession sqlSession = manager.getSqlSession();
		result=sqlSession.update("Board.update",board);
		sqlSession.commit();
		manager.close(sqlSession);
		return result;
	}
	public int delete(int board_id) {
		int result=0;
		SqlSession sqlSession = manager.getSqlSession();
		result = sqlSession.delete("Board.delete",board_id);
		sqlSession.commit();
		manager.close(sqlSession);
		return result;
	}
	
}
