package Ephemeral.webChat.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Ephemeral.Mybatis.manuallySession;
import Ephemeral.Mybatis.sqlSession;
import Ephemeral.Spring.getAC;
import Ephemeral.time.getTime;
import Ephemeral.webChat.dao.massageDao;
import Ephemeral.webChat.dao.onlineDao;
import Ephemeral.webChat.dao.impl.findLastIdImpl;
import Ephemeral.webChat.entity.massage;
import Ephemeral.webChat.entity.user;
import Ephemeral.webChat.service.massageService;

public class massageServiceImpl implements massageService
{
	public int newMassage(String content, int send_by_online_no, int send_to_online_no) throws IOException
	{
		massageDao massagedao = sqlSession.getSqlSession().getMapper(massageDao.class);
		massage massage = getAC.ac().getBean("massage", massage.class);
		massage.setContent(content);
		massage.setNo(new findLastIdImpl(manuallySession.getSqlFactory()).Id("no", "massage"));
		massage.setReaded(0);
		massage.setSend_by_online_no(send_by_online_no);
		massage.setSend_to_online_no(send_to_online_no);
		massage.setSend_time(getTime.time());
		massagedao.newMassage(massage);
		sqlSession.closeSqlSession();
		return massage.getNo();
		
	}
	public ArrayList<massage> checkMassage(int send_by_online_no, int send_to_online_no) throws IOException
	{
		massageDao massagedao = sqlSession.getSqlSession().getMapper(massageDao.class);
		ArrayList<massage> List = massagedao.checkMassage(send_by_online_no, send_to_online_no);
		sqlSession.closeSqlSession();
		return List;
	}
	public void alterReaded(ArrayList<massage> readedList) throws IOException
	{
		massageDao massagedao = sqlSession.getSqlSession().getMapper(massageDao.class);
		for(massage massage:readedList)
		{
			massagedao.alterReaded(massage.getNo());
		}
	}
}
