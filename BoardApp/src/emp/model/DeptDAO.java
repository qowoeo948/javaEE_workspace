/*
 * 지금까지는 DAO의 코드 기술을 JDBC로 이용하였기 때문에
 * 쿼리문보다 그 외의 코드가 더 장황했었다.
 * 따라서 이번 DAO에서는 mybatis 프레임웍을 도입하여, 코드를 보다 간결하게 작성해보겠다. 
 * */

package emp.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import db.MybatisManager;

public class DeptDAO {
	//DAO에서 SQL문이 들어있는 xml을 호출하자, 이 때 어떤 쿼리수행을 원하는지 구분하기 위해서는
	//xml 태그에 부여한 ID를 이용하면 된다.
	//xml태그를 호출하기 위해서는 mybatis의 sqlSession이 필요하고, 현재는
	//MybatisManager클래스의 멤버로 두었다
	
	MybatisManager manager = new MybatisManager();
	SqlSessionFactory factory;
	
	public DeptDAO() {
		factory = manager.getSqlSessionFactory();
		
	}
	
	//모든 데이터 가져오기
	public List selectAll() {
		SqlSession session=factory.openSession(); //쿼리문 수행객체 생성
		
		return session.selectList("mybatis.config.Dept.selectAll");
	}
}
