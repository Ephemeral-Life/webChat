package Ephemeral.webChat.service;

import java.io.IOException;

import Ephemeral.webChat.entity.info;

public interface infoService
{
	info getInfo(int account_id) throws IOException;
	void newInfo(info info) throws IOException;
}
