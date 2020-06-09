package Ephemeral.webChat.service;

import java.io.IOException;

import Ephemeral.webChat.entity.vip;

/**
 * 会员表的服务层接口
 * 实现的功能：
 * 		查询会员剩余小时数
 * 		新增会员资格
 * 		续费会员时间
 * @author Ephemeral
 *
 */
public interface vipService
{
	long checkVip(int account_id) throws IOException;
	void newVip(vip vip) throws IOException;
	void moreVip(int account_id, int month) throws IOException;
}
