package Ephemeral.Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Ephemeral.Spring.getAC;
import Ephemeral.webChat.entity.online;
import Ephemeral.webChat.service.massageService;
import Ephemeral.webChat.service.impl.massageServiceImpl;

/**
 * Servlet implementation class do_exit
 */
@WebServlet("/do_exit")
public class do_exit extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public do_exit()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		try
		{
			massageService ms = getAC.ac().getBean("massageService", massageServiceImpl.class);
			ms.alterReaded(ms.checkMassage(Integer.valueOf(session.getAttribute("connection_builded_talking_to_no").toString()), ((online)(session.getAttribute("online"))).getNo()));
			ms.alterReaded(ms.checkMassage(((online)(session.getAttribute("online"))).getNo(), Integer.valueOf(session.getAttribute("connection_builded_talking_to_no").toString())));
		}
		catch (Exception e)
		{
			System.err.println("标记已读信息异常，但完成了。");;
		}
		try
		{
			String[] sessionList = {"talkName", "userName", "user", "talkName", "ip", "online", "allMassage", "connection_builded_talking_to_no", "userAccount"};
			for(int i = 0; i <= sessionList.length; i++)
			{
				session.setAttribute(sessionList[i], null);
				System.out.println("清空 " + sessionList[i]);
			}
		}
		catch(Exception e)
		{
			System.err.println("清空session异常，但完成了。");;
		}
		finally
		{
			response.sendRedirect("./index.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
