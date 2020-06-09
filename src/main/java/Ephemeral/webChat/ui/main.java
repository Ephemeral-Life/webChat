package Ephemeral.webChat.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Ephemeral.Spring.getAC;
import Ephemeral.webChat.entity.massage;
import Ephemeral.webChat.service.massageService;
import Ephemeral.webChat.service.onlineService;
import Ephemeral.webChat.service.impl.massageServiceImpl;
import Ephemeral.webChat.service.impl.onlineServiceImpl;

public class main
{
	public static void main(String[] args) throws IOException
	{
		String user_name = "游客";
		int no = 0;
		Scanner input = new Scanner(System.in);
		onlineService os = getAC.ac().getBean("onlineService", onlineServiceImpl.class);
		massageService ms = getAC.ac().getBean("massageService", massageServiceImpl.class);
		int succeedConnectedNo = 0;
		boolean getMassage = true;
		ArrayList<massage> massageList = null;
		String content = "";
		System.out.println("======================================================");
		System.out.println("Ephemeral平台---by:wzy");
		System.out.print("输入/start开始一次新的匹配，随时输入/start-over退出聊天:");
		if(input.next().equalsIgnoreCase("/start"))
		{
			if(user_name.equals("游客"))
			{
				System.out.print("输入你这次要使用的用户名：");
				user_name = input.next();
			}
			System.out.println("开始匹配...");
			no = os.newOnlineUser(user_name, 0, "ip功能停用");
			for(;succeedConnectedNo == 0;)
			{
				if(os.checkConnect(no) != 0)
				{
					succeedConnectedNo = os.checkConnect(no);
					break;
				}
				succeedConnectedNo = os.buildConnect(no);
			}
			System.out.println("成功与用户:" + os.getUserName(succeedConnectedNo) + "建立连接");
		}
		else
		{
			System.out.println("输入有误");
			System.exit(0);
		}
		for(;;)
		{
			System.out.print(user_name + ":");
			content = input.next();
			if(content.equalsIgnoreCase("/start-over"))
			{
				break;
			}
			ms.newMassage(content, no, succeedConnectedNo);
			for(;getMassage;)
			{
				if(os.checkConnect(no) == 0)
				{
					System.out.println("你匹配的人溜走了！");
					break;
				}
				massageList = ms.checkMassage(succeedConnectedNo, no);
				for(massage massage : massageList)
				{
					if(massageList != null)
					{
						System.err.println(os.getUserName(succeedConnectedNo) + ":" + massage.getContent());
						getMassage = false;
					}
				}
			}
			ms.alterReaded(massageList);
			getMassage = true;
		}
		System.out.println("都结束了..");
		os.disConnect(no);
		System.exit(0);
	}
}
