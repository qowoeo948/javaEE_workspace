
//설정파일을 읽어들여, 팩토리를 생성하고 쿼리문 수행에 필요한 SqlSession 객체를 반환해주는 객체
package mybatis.config;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import sun.security.jca.GetInstance;

public class MybatisConfigManager {
	private static MybatisConfigManager instance;
	
   InputStream inputStream;
   String resource = "mybatis/config/config.xml";
   SqlSessionFactory sqlSessionFactory;
   
   public MybatisConfigManager() {
      try {
         inputStream = Resources.getResourceAsStream(resource);
         sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
      } catch (IOException e) {
         e.printStackTrace();
      }
      
   }
   
   //생성자를 막았기 떄문에, 오직 이 메서드에 의해서만 인스턴스를 얻어갈 수 있도록 제한하자
   public static MybatisConfigManager getInstance() {
	if(instance==null) {
		instance = new MybatisConfigManager();
	}
	return instance;
}
   
   
   //SqlSession을 반환하는 메서드(쿼리문을 수행하고 싶은자가 호출함)
   public SqlSession getSqlSession() {
      SqlSession sqlSession = null;
      sqlSession = sqlSessionFactory.openSession();
      return sqlSession;
   }
   
   //SqlSession을 닫는 메서드 (SqlSession을 쿼리문 수행 후 이 메서드의 인수로 넘기자)
   public void close(SqlSession sqlSession) {
      sqlSession.close();
   }
}