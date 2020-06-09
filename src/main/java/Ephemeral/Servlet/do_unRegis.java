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
@WebServlet("/do_unRegis")
public class do_unRegis extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public do_unRegis()
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
		HttpSession session = request.getSession();
		onlineService os = new onlineServiceImpl();
		try
		{
			os.pullDownOnlineUser(Integer.valueOf(session.getAttribute("onlineNo").toString()));
		}
		catch (Exception e)
		{
			;	
		}
	    response.sendRedirect("./do_exit");
	}
}
