package Ephemeral.webChat.service.impl;

import java.io.IOException;
import java.util.ArrayList;

import Ephemeral.Mybatis.manuallySession;
import Ephemeral.Mybatis.sqlSession;
import Ephemeral.Spring.getAC;
import Ephemeral.webChat.dao.userDao;
import Ephemeral.webChat.dao.impl.findLastIdImpl;
import Ephemeral.webChat.entity.user;
import Ephemeral.webChat.service.userService;

public class userServiceImpl implements userService
{
	public user login(String username, String password) throws IOException
	{
		userDao	userdao = sqlSession.getSqlSession().getMapper(userDao.class);
		ArrayList<user> List = userdao.login();
		sqlSession.closeSqlSession();
		for(user user:List)
		{
			if(user.getUser_name().equals(username) && user.getPass_word().equals(password))
            {
            	return user;
            }
		}
		return null;
	}

	public void register(String user_name, String pass_word) throws IOException
	{
		userDao	userdao = sqlSession.getSqlSession().getMapper(userDao.class);
		user user = getAC.ac().getBean("user", user.class);
		user.setAccount_id(new findLastIdImpl(manuallySession.getSqlFactory()).Id("account_id", "user"));
		user.setUser_name(user_name);
		user.setPass_word(pass_word);
		user.setVip(0);
		userdao.register(user);
		sqlSession.closeSqlSession();
	}

	public ArrayList<user> getAllUser() throws IOException
	{
		userDao	userdao = sqlSession.getSqlSession().getMapper(userDao.class);
		ArrayList<user> List = userdao.login();
		sqlSession.closeSqlSession();
		return List;
	}
}
