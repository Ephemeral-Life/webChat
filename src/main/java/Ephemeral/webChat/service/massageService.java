package Ephemeral.webChat.service;

import java.io.IOException;
import java.util.ArrayList;

import Ephemeral.webChat.entity.massage;

/**
 * 信息表的服务层接口
 * 实现的功能：
 * 		插入新信息
 * 		查询指定收发人的信息
 * 		修改已读状态
 * @author Ephemeral
 *
 */
public interface massageService
{
	int newMassage(String content, int send_by_online_no, int send_to_online_no) throws IOException;
	ArrayList<massage> checkMassage(int send_by_online_no, int send_to_online_no) throws IOException;
	void alterReaded(ArrayList<massage> readedList) throws IOException;
}
