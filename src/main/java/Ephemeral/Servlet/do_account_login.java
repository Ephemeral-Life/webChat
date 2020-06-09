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
import Ephemeral.webChat.entity.user;
import Ephemeral.webChat.service.userService;
import Ephemeral.webChat.service.impl.userServiceImpl;



@WebServlet("/do_account_login")
public class do_account_login extends HttpServlet
{

	/**
	 * Constructor of the object.
	 */
	public do_account_login()
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
		response.setContentType("text/html;charset=gbk");
		String docType = "<!DOCTYPE html> \n";
		PrintWriter out = response.getWriter();
		out.println
		(
			docType + "<html>\n" + "<head><title>不要打破第四面墙</title></head>\n" 
			+ "<body>"
			+ "<script>alert('为防止暴力破解，get提交被禁止。'); window.location='../index.jsp' </script>"
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
		userService us = getAC.ac().getBean("userService", userServiceImpl.class);
		HttpSession session = request.getSession();
		response.setHeader("content-type", "text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		String docType = "<!DOCTYPE html> \n";
		PrintWriter out = response.getWriter();
		user user = getAC.ac().getBean("user", user.class);
		String name = request.getParameter("username");
		String pwd = request.getParameter("password");
		String verifyInput = request.getParameter("checkcode");
		String verifyCode = (String) session.getAttribute("verificationCode");
		if(verifyInput.equalsIgnoreCase(verifyCode))
		{
				user = us.login(name, pwd);
				if(user != null)
				{
					session.setAttribute("userName", user.getUser_name());
					session.setAttribute("passWord", user.getPass_word());
					session.setAttribute("userAccount", user);
				}
				else
				{
					session.setAttribute("userName", null);
					session.setAttribute("passWord", null);
					session.setAttribute("userAccount", null);
				}
				response.sendRedirect("./do_redirecter");
		}
		else
		{
			out.println
			(
				docType + "<html>\n" + "<head><title>验证码错误</title></head>\n" 
				+ "<body>"
				+ "<script>alert('验证码输入错误。'); window.location='./action/login.jsp' </script>"
				+ "</body>"
				+ "</html>"
			);
		}
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
