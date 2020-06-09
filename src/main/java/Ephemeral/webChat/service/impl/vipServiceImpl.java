package Ephemeral.webChat.service.impl;

import java.io.IOException;

import Ephemeral.Mybatis.sqlSession;
import Ephemeral.webChat.dao.vipDao;
import Ephemeral.webChat.entity.vip;
import Ephemeral.webChat.service.vipService;

public class vipServiceImpl implements vipService
{
	public long checkVip(int account_id) throws IOException
	{
		vipDao vipdao = sqlSession.getSqlSession().getMapper(vipDao.class);
		vip vip = vipdao.checkVip(account_id);
		sqlSession.closeSqlSession();
		if(vip == null)
			return 0;
		else
		{
			long left = vip.getVip_end().getTime()-vip.getVip_start().getTime();
			if(left/1000/60/60 <= 0)
				return 1;
			else
				return left/1000/60/60;
		}
	}
	public void newVip(vip vip) throws IOException
	{
		vipDao vipdao = sqlSession.getSqlSession().getMapper(vipDao.class);
		vipdao.newVip(vip);
		sqlSession.closeSqlSession();
	}
	public void moreVip(int account_id, int month) throws IOException
	{
		vipDao vipdao = sqlSession.getSqlSession().getMapper(vipDao.class);
		vipdao.moreVip(account_id, month);
		sqlSession.closeSqlSession();
	}
}
