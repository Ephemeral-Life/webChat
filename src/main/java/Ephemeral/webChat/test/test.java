package Ephemeral.webChat.test;


import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;

import Ephemeral.Mybatis.sqlSession;
import Ephemeral.Spring.getAC;
import Ephemeral.time.getTime;
import Ephemeral.webChat.dao.infoDao;
import Ephemeral.webChat.dao.vipDao;
import Ephemeral.webChat.entity.info;
import Ephemeral.webChat.entity.vip;
import Ephemeral.webChat.service.onlineService;
import Ephemeral.webChat.service.impl.onlineServiceImpl;



public class test
{
	@Test
	public void testChat() throws IOException, ParseException
	{
		onlineService os = getAC.ac().getBean("onlineService", onlineServiceImpl.class);
		infoDao infodao = sqlSession.getSqlSession().getMapper(infoDao.class);
		info info = getAC.ac().getBean("info", info.class);
		infodao.insertInfo(info);
		System.out.println();
	}
}