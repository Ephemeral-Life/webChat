package Ephemeral.webChat.entity;

import java.io.Serializable;

public class user implements Serializable
{
	private int account_id;
	private String user_name;
	private String pass_word;
	private int vip;
	public int getAccount_id()
	{
		return account_id;
	}
	public void setAccount_id(int account_id)
	{
		this.account_id = account_id;
	}
	public String getUser_name()
	{
		return user_name;
	}
	public void setUser_name(String user_name)
	{
		this.user_name = user_name;
	}
	public String getPass_word()
	{
		return pass_word;
	}
	public void setPass_word(String pass_word)
	{
		this.pass_word = pass_word;
	}
	public int getVip()
	{
		return vip;
	}
	public void setVip(int vip)
	{
		this.vip = vip;
	}
}
