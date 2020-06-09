package Ephemeral.webChat.entity;

import java.io.Serializable;

public class massage implements Serializable
{
	private int no;
	private String content;
	private int send_by_online_no;
	private int send_to_online_no;
	private String send_time;
	private int readed;
	public int getNo()
	{
		return no;
	}
	public void setNo(int no)
	{
		this.no = no;
	}
	public String getContent()
	{
		return content;
	}
	public void setContent(String content)
	{
		this.content = content;
	}
	public int getSend_by_online_no()
	{
		return send_by_online_no;
	}
	public void setSend_by_online_no(int send_by_online_no)
	{
		this.send_by_online_no = send_by_online_no;
	}
	public int getSend_to_online_no()
	{
		return send_to_online_no;
	}
	public void setSend_to_online_no(int send_to_online_no)
	{
		this.send_to_online_no = send_to_online_no;
	}
	public String getSend_time()
	{
		return send_time;
	}
	public void setSend_time(String send_time)
	{
		this.send_time = send_time;
	}
	public int getReaded()
	{
		return readed;
	}
	public void setReaded(int readed)
	{
		this.readed = readed;
	}
}
