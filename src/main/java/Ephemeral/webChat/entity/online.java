package Ephemeral.webChat.entity;

import java.io.Serializable;

public class online implements Serializable
{
	private int no;
	private String user_name;
	private int account_id;
	private String ip;
	private int chat_with_no;
	public int getNo()
	{
		return no;
	}
	public void setNo(int no)
	{
		this.no = no;
	}
	public String getUser_name()
	{
		return user_name;
	}
	public void setUser_name(String user_name)
	{
		this.user_name = user_name;
	}
	public int getAccount_id()
	{
		return account_id;
	}
	public void setAccount_id(int account_id)
	{
		this.account_id = account_id;
	}
	public String getIp()
	{
		return ip;
	}
	public void setIp(String ip)
	{
		this.ip = ip;
	}
	public int getChat_with_no()
	{
		return chat_with_no;
	}
	public void setChat_with_no(int chat_with_no)
	{
		this.chat_with_no = chat_with_no;
	}
}
