package Ephemeral.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

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

/**
 * Servlet implementation class do_register
 */
@WebServlet("/do_register")
public class do_register extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public do_register()
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		userService us = getAC.ac().getBean("userService", userServiceImpl.class);
		HttpSession session = request.getSession();
		response.setHeader("content-type", "text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		user user = getAC.ac().getBean("user", user.class);
		boolean flag_exist = false;
		String docType = "<!DOCTYPE html> \n";
		PrintWriter out = response.getWriter();
		String userName = request.getParameter("username");
		String passWord = request.getParameter("password");
		String confirm_password = request.getParameter("confirm_password");
		String verifyInput = null;
		String verifyCode = null;
		verifyInput = request.getParameter("checkcode");
		verifyCode = (String) session.getAttribute("verificationCode");
		int verify_flag = 0;
		if(verifyInput.equalsIgnoreCase(verifyCode))
			verify_flag = 1;
		else
			verify_flag = 0;
		if(verify_flag == 1)
		{
			if(userName.equals(""))
			{
				out.println
				(
					docType + "<html>\n" + "<head><title>用户名不能为空</title></head>\n" 
					+ "<body>"
					+ "<script>alert('用户名不能为空。'); window.location='./action/register.jsp' </script>"
					+ "</body>"
					+ "</html>"
				);
			}
			else if(passWord.equals(""))
			{
				out.println
				(
					docType + "<html>\n" + "<head><title>密码不能为空</title></head>\n" 
					+ "<body>"
					+ "<script>alert('密码不能为空。'); window.location='./action/register.jsp' </script>"
					+ "</body>"
					+ "</html>"
				);
			}
			if(passWord.equals(confirm_password))
			{
				user.setUser_name(request.getParameter("username"));
				user.setPass_word(request.getParameter("password"));
				ArrayList <user>list = us.getAllUser();
				for(user userList:list)
				{
					if(userList.getUser_name().equals(user.getUser_name()))
					{
						flag_exist = true;
						break;
					}
				}
				if(!flag_exist)
				{
					us.register(user.getUser_name(), user.getPass_word());
					out.print
					(
						docType + "<html>\n" + "<head><title>完成了</title></head>\n" 
						+ "<body>"
						+ "<script>alert('用户账号注册完成了。'); window.location='./action/login.jsp' </script>"
						+ "</body>"
						+ "</html>"
					);
				}
				else
				{
					out.println
					(
						docType + "<html>\n" + "<head><title>用户名已经存在</title></head>\n" 
						+ "<body>"
						+ "<script>alert('这个用户名已经存在了。'); window.location='./action/register.jsp' </script>"
						+ "</body>"
						+ "</html>"
					);
				}
			}
			else
			{
				out.println
				(
					docType + "<html>\n" + "<head><title>两次输入的密码不一致--by:王泽洋</title></head>\n" 
					+ "<body>"
					+ "<script>alert('两次输入的密码不一致。'); window.location='./action/register.jsp' </script>"
					+ "</body>"
					+ "</html>"
				);
			}
		}
		else
		{
			out.println
			(
				docType + "<html>\n" + "<head><title>验证码错误</title></head>\n" 
				+ "<body>"
				+ "<script>alert('验证码输入错误。'); window.location='../register.jsp' </script>"
				+ "</body>"
				+ "</html>"
			);
		}
	}
}
