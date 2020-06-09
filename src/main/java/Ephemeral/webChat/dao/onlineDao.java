package Ephemeral.webChat.dao;

import java.io.IOException;

import org.apache.ibatis.annotations.Param;

import Ephemeral.webChat.entity.online;

public interface onlineDao
{
	void newOnlineUser(online online);//插入新的在线状态
	void pullUpOnlineUser(int no) throws IOException;//上线活跃用户
	void pullDownOnlineUser(int no) throws IOException;//下线活跃用户
	online checkConnect(int no) throws IOException;//查询聊天连接
	void buildConnect(@Param("chat_with_no")int chat_with_no, @Param("no")int no) throws IOException;//建立聊天连接
	online catchOneToChat(int no) throws IOException;//抓一个人聊天
	void disConnect(int no) throws IOException;//放这个人走
	String getUserName(int no) throws IOException;//查询指定uid的uname
	String getUserIp(int no) throws IOException;//查询指定uid的ip
}
