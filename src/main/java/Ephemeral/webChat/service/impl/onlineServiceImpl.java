package Ephemeral.webChat.service.impl;

import java.io.IOException;


import Ephemeral.Mybatis.manuallySession;
import Ephemeral.Mybatis.sqlSession;
import Ephemeral.Spring.getAC;
import Ephemeral.webChat.dao.onlineDao;
import Ephemeral.webChat.dao.impl.findLastIdImpl;
import Ephemeral.webChat.entity.online;
import Ephemeral.webChat.service.onlineService;

public class onlineServiceImpl implements onlineService
{
	public int newOnlineUser(String user_name, int account_id, String ip) throws IOException
	{
		online online = getAC.ac().getBean("online", online.class);
		online.setAccount_id(account_id);
		online.setChat_with_no(-1);
		online.setIp(ip);
		online.setNo(new findLastIdImpl(manuallySession.getSqlFactory()).Id("no", "online"));
		online.setUser_name(user_name);
		onlineDao onlinedao = sqlSession.getSqlSession().getMapper(onlineDao.class);
		onlinedao.newOnlineUser(online);
		sqlSession.closeSqlSession();
		return online.getNo();
	}
	public void pullUpOnlineUser(int no) throws IOException
	{
		onlineDao onlinedao = sqlSession.getSqlSession().getMapper(onlineDao.class);
		onlinedao.pullUpOnlineUser(no);
		sqlSession.closeSqlSession();
	}
	public void pullDownOnlineUser(int no) throws IOException
	{
		onlineDao onlinedao = sqlSession.getSqlSession().getMapper(onlineDao.class);
		onlinedao.pullDownOnlineUser(no);
		sqlSession.closeSqlSession();
	}
	public int buildConnect(int no) throws IOException
	{
		synchronized(online.class)
		{
			int checkConnect_chat_with_no = 0;
			int catchOne_chat_with_no = 0;/*抓到的空闲用户*/
			onlineDao onlinedao = sqlSession.getSqlSession().getMapper(onlineDao.class);
			try
			{
				checkConnect_chat_with_no = onlinedao.checkConnect(no).getNo();
			}catch (NullPointerException e)
			{
				checkConnect_chat_with_no = 0;
			}
			if(onlinedao.checkConnect(no) == null)//没有尝试与当前用户建立连接的账号
			{
				/*
				 * update online set chat_with_no = #{chat_with_no} where no = #{no}
				 * void buildConnect(@Param("chat_with_no")int chat_with_no, @Param("no")int no)
				 * */
				try
				{
					catchOne_chat_with_no = onlinedao.catchOneToChat(no).getNo();//尝试捕捉空闲用户
				}catch(Exception e)//没抓到
				{
					sqlSession.closeSqlSession();
					return 0;
				}
				onlinedao.buildConnect(no/*当前用户*/, catchOne_chat_with_no);
				onlinedao.buildConnect(catchOne_chat_with_no/*抓到的空闲用户*/, no/*当前用户*/);
				sqlSession.closeSqlSession();
				return catchOne_chat_with_no;
			}
			else
			{
				onlinedao.buildConnect(checkConnect_chat_with_no/*抓到的空闲用户*/, no/*当前用户*/);
				sqlSession.closeSqlSession();
				return catchOne_chat_with_no;
			}
		}
	}
	public int checkConnect(int no) throws IOException
	{
		onlineDao onlinedao = sqlSession.getSqlSession().getMapper(onlineDao.class);
		online online = onlinedao.checkConnect(no);
		sqlSession.closeSqlSession();
		if(online == null)
		{
			return 0;
		}
		else
		{
			return online.getNo();
		}
	}
	public void disConnect(int no) throws IOException
	{
		onlineDao onlinedao = sqlSession.getSqlSession().getMapper(onlineDao.class);
		onlinedao.disConnect(no);
		sqlSession.closeSqlSession();
	}
	public String getUserName(int no) throws IOException
	{
		String userName = null;
		onlineDao onlinedao = sqlSession.getSqlSession().getMapper(onlineDao.class);
		userName = onlinedao.getUserName(no);
		sqlSession.closeSqlSession();
		return userName;
	}
	public String getUserIp(int no) throws IOException
	{
		String ip = null;
		onlineDao onlinedao = sqlSession.getSqlSession().getMapper(onlineDao.class);
		ip = onlinedao.getUserIp(no);
		sqlSession.closeSqlSession();
		return ip;
	}
}
