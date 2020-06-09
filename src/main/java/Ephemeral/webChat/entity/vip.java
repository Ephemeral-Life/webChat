package Ephemeral.webChat.entity;

import java.io.Serializable;
import java.util.Date;

public class vip implements Serializable
{
	private int no;
	private int account_id;
	private Date vip_start;
	private Date vip_end;
	public int getNo()
	{
		return no;
	}
	public void setNo(int no)
	{
		this.no = no;
	}
	public int getAccount_id()
	{
		return account_id;
	}
	public void setAccount_id(int account_id)
	{
		this.account_id = account_id;
	}
	public Date getVip_start()
	{
		return vip_start;
	}
	public void setVip_start(Date vip_start)
	{
		this.vip_start = vip_start;
	}
	public Date getVip_end()
	{
		return vip_end;
	}
	public void setVip_end(Date vip_end)
	{
		this.vip_end = vip_end;
	}
}
