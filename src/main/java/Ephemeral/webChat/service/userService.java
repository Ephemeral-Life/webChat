package Ephemeral.webChat.service;

import java.io.IOException;
import java.util.ArrayList;

import Ephemeral.webChat.entity.user;

/**
 * 用户表的服务层接口
 * 实现的功能：
 * 		登录
 * 		注册
 * 		返回所有用户
 * @author Ephemeral
 *
 */
public interface userService
{
	user login(String user_name, String pass_word) throws IOException;//用户名密码登录
	void register(String user_name, String pass_word) throws IOException;//用户名密码注册
	ArrayList<user> getAllUser() throws IOException;//返回所有用户
}
