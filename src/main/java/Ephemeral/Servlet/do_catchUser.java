package Ephemeral.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Ephemeral.webChat.service.onlineService;
import Ephemeral.webChat.service.impl.onlineServiceImpl;

/**
 * Servlet implementation class do_redirecter
 */
@WebServlet("/do_catchUser")
public class do_catchUser extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public do_catchUser()
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
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		int no = 0;
		int connect_no = 0;
		HttpSession session = request.getSession();
		no = Integer.valueOf(session.getAttribute("onlineNo").toString());
		onlineService os = new onlineServiceImpl();
		os.pullUpOnlineUser(no);
		for(;connect_no == 0;)
		{
			connect_no = os.checkConnect(no);
			if(connect_no == 0)
				connect_no = os.buildConnect(no);
			try
			{
				Thread.sleep(1000);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		session.setAttribute("connection_builded_talking_to_no", connect_no);
		response.sendRedirect("./do_blank");
	}
}
