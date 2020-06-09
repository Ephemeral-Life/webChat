package Ephemeral.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Ephemeral.Mybatis.manuallySession;
import Ephemeral.Spring.getAC;
import Ephemeral.time.getTime;
import Ephemeral.webChat.dao.impl.findLastIdImpl;
import Ephemeral.webChat.entity.user;
import Ephemeral.webChat.entity.vip;
import Ephemeral.webChat.service.userService;
import Ephemeral.webChat.service.vipService;
import Ephemeral.webChat.service.impl.userServiceImpl;
import Ephemeral.webChat.service.impl.vipServiceImpl;



@WebServlet("/do_moreVip")
public class do_moreVip extends HttpServlet
{

	/**
	 * Constructor of the object.
	 */
	public do_moreVip()
	{
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy()
	{
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request  the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException      if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		vipService vs = getAC.ac().getBean("vipService", vipServiceImpl.class);
		response.setHeader("content-type", "text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		String docType = "<!DOCTYPE html> \n";
		PrintWriter out = response.getWriter();
		int account_id = Integer.valueOf(request.getParameter("account_id"));
		int month = Integer.valueOf(request.getParameter("month"));
		vs.moreVip(account_id, month);
		out.println
		(
			docType + "<html>\n" + "<head><title>续费成功</title></head>\n" 
			+ "<body>"
			+ "<script>alert('完成了，你已成功续费"+month+"个月会员！'); window.location='./action/talk_con.jsp' </script>"
			+ "</body>"
			+ "</html>"
		);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request  the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException      if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}
	
	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException
	{
		// Put your code here
	}

}
