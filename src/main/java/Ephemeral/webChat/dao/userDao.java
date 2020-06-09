package Ephemeral.webChat.dao;

import java.util.ArrayList;

import Ephemeral.webChat.entity.user;

public interface userDao
{
	ArrayList<user> login();//返回所有账号对象
	void register(user user);//注册
}
