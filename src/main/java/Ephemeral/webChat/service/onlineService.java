package Ephemeral.webChat.service;

import java.io.IOException;

import Ephemeral.Mybatis.sqlSession;
import Ephemeral.webChat.dao.onlineDao;
import Ephemeral.webChat.entity.online;

/**
 * 活跃用户表的服务层接口
 * 实现的功能：
 * 		新增活跃用户
 * 		上线活跃用户
 * 		下线活跃用户
 * 		查询聊天连接
 * 		建立聊天连接
 * 		终止聊天连接
 * 		查询指定uid的uname
 * 		查询指定uid的ip
 * @author Ephemeral
 *
 */
public interface onlineService
{
	int newOnlineUser(String user_name, int account_id, String ip) throws IOException;//新的活跃用户
	void pullUpOnlineUser(int no) throws IOException;//上线活跃用户
	void pullDownOnlineUser(int no) throws IOException;//下线活跃用户
	int checkConnect(int no) throws IOException;//查询聊天连接
	int buildConnect(int no) throws IOException;//建立聊天连接
	void disConnect(int no) throws IOException;//终止聊天连接
	String getUserName(int no) throws IOException;//查询指定uid的uname
	String getUserIp(int no) throws IOException;//查询指定uid的ip
}
