/*
 * DB접속 정보 및 매퍼들에 대한 위치가 지정된 마이바티스의 설정파일을 읽어보자
 * */

package db;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisManager {
	private SqlSessionFactory sqlSessionFactory;//sqlSession의 인스턴스를 얻기 위한 팩토리 객체

	public MybatisManager() {
		String resource = "mybatis/config/config.xml";
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			System.out.println(sqlSessionFactory);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	

	public static void main(String[] args) {
		new MybatisManager();
	}
}
