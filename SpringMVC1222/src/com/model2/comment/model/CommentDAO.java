package com.model2.comment.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.model2.domain.Comment;
import com.model2.mybatis.config.MybatisConfigManager;

public class CommentDAO {
		MybatisConfigManager manager = MybatisConfigManager.getInstance();
	
	public List selectAll(int board_id) {
		List list = null;
		SqlSession sqlSession = manager.getSqlSession();
		list = sqlSession.selectList("Comment.selectAll",board_id);
		manager.close(sqlSession);
		return list;
		
	}
	
	public int insert(Comment comment) {
		int result=0;
		SqlSession sqlSession = manager.getSqlSession();
		result= sqlSession.insert("Comment.insert", comment);
		sqlSession.commit();
		manager.close(sqlSession);
		return result;
		
	}
	
	public int delete(int comment_id) {
		int result=0;
		SqlSession sqlSession = manager.getSqlSession();
		result = sqlSession.delete("Comment.delete",comment_id);
		sqlSession.commit();
		manager.close(sqlSession);
		return result;
	}

}
