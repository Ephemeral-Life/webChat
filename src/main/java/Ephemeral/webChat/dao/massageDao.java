package Ephemeral.webChat.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import Ephemeral.webChat.entity.massage;

public interface massageDao
{
	void newMassage(massage massage);
	ArrayList<massage> checkMassage(@Param("send_by_online_no")int send_by_online_no, @Param("send_to_online_no")int send_to_online_no);
	void alterReaded(int readedNo);
}
