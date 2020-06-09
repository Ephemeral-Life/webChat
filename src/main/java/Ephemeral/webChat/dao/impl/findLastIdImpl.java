package Ephemeral.webChat.dao.impl;


import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import Ephemeral.Spring.getAC;
import Ephemeral.webChat.dao.findLastId;
import Ephemeral.webChat.entity.findLastIdEntity;

public class findLastIdImpl implements findLastId
{
	private SqlSessionFactory factory;
	
	public findLastIdImpl(SqlSessionFactory factory)
	{
		this.factory = factory;
	}

	public int Id(String listName, String table)
	{
		findLastIdEntity idEntity = getAC.ac().getBean("findLastIdEntity", findLastIdEntity.class);
		idEntity.setListName(listName);
		idEntity.setTable(table);
		int id = 0;
		SqlSession session = factory.openSession(true);
		try
		{
			id = Integer.valueOf(session.selectOne("Ephemeral.webChat.dao.findLastId.Id", idEntity).toString());
		}catch(Exception e)
		{
			id = 0;
		}finally
		{
			session.close();
		}
		return id + 1;
	}
	
}
