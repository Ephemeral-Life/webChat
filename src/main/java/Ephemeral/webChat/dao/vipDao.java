package Ephemeral.webChat.dao;

import org.apache.ibatis.annotations.Param;

import Ephemeral.webChat.entity.vip;

public interface vipDao
{
	vip checkVip(int account_id);
	void newVip(vip vip);
	void moreVip(@Param("account_id")int account_id, @Param("month")int month);
}
