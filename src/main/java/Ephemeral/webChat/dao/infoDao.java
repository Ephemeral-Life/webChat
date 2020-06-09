package Ephemeral.webChat.dao;

import Ephemeral.webChat.entity.info;

public interface infoDao
{
	info getInfo(int account_id);
	void delInfo(int account_id);
	void insertInfo(info info);
}
