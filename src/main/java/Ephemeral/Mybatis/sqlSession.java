package Ephemeral.Mybatis;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class sqlSession
{
	private static InputStream in;
	private static SqlSession sqlSession;
	private static SqlSessionFactory factory;
	public static SqlSession getSqlSession() throws IOException
	{
		in = Resources.getResourceAsStream("SqlMapConfig.xml");
		factory = new SqlSessionFactoryBuilder().build(in);
		sqlSession = factory.openSession(true);
		return sqlSession;
	}
	public static void closeSqlSession() throws IOException
	{
		sqlSession.close();
		in.close();
	}
}
