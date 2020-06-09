package Ephemeral.webChat.service.impl;

import java.io.IOException;

import Ephemeral.Mybatis.manuallySession;
import Ephemeral.Mybatis.sqlSession;
import Ephemeral.Spring.getAC;
import Ephemeral.webChat.dao.infoDao;
import Ephemeral.webChat.dao.impl.findLastIdImpl;
import Ephemeral.webChat.entity.info;
import Ephemeral.webChat.service.infoService;

public class infoServiceImpl implements infoService
{

	public info getInfo(int account_id) throws IOException
	{
		info info = getAC.ac().getBean("info", info.class);
		infoDao infodao = sqlSession.getSqlSession().getMapper(infoDao.class);
		info = infodao.getInfo(account_id);
		sqlSession.closeSqlSession();
		return info;
	}

	public void newInfo(info info) throws IOException
	{
		info.setNo(new findLastIdImpl(manuallySession.getSqlFactory()).Id("no", "info"));
		infoDao infodao = sqlSession.getSqlSession().getMapper(infoDao.class);
		infodao.delInfo(info.getAccount_id());
		infodao.insertInfo(info);
		sqlSession.closeSqlSession();
	}
}
