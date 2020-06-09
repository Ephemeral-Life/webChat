package Ephemeral.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Ephemeral.Spring.getAC;
import Ephemeral.webChat.entity.info;
import Ephemeral.webChat.entity.user;
import Ephemeral.webChat.service.infoService;
import Ephemeral.webChat.service.userService;
import Ephemeral.webChat.service.impl.infoServiceImpl;
import Ephemeral.webChat.service.impl.userServiceImpl;



@WebServlet("/do_setInfo")
public class do_setInfo extends HttpServlet
{

	/**
	 * Constructor of the object.
	 */
	public do_setInfo()
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
		infoService is = getAC.ac().getBean("infoService", infoServiceImpl.class);
		HttpSession session = request.getSession();
		response.setHeader("content-type", "text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		String docType = "<!DOCTYPE html> \n";
		PrintWriter out = response.getWriter();
		info info = getAC.ac().getBean("info", info.class);
		user user = getAC.ac().getBean("user", user.class);
		user = (user)session.getAttribute("userAccount");
		info.setNo(0);
		info.setAccount_id(user.getAccount_id());
		info.setSex(request.getParameter("sex"));
		String age = request.getParameter("age");
		info.setAge(Integer.valueOf(age));
		is.newInfo(info);
		out.println
		(
			docType + "<html>\n" + "<head><title>设置个人信息</title></head>\n" 
			+ "<body>"
			+ "<script>alert('个人信息成功设置。'); window.location='./action/talk_con.jsp' </script>"
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
