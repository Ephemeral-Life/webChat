package filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class do_verify_login
 */
public class do_verify_login implements Filter
{
	private String excludedPage;
	private String[] excludedPages;

	/**
	 * Default constructor.
	 */
	public do_verify_login()
	{
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy()
	{
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException
	{
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		response.setContentType("text/html;charset=gbk");
		String docType = "<!DOCTYPE html> \n";
		HttpSession session = req.getSession();
		if(session.getAttribute("userName") == null)
		{
			PrintWriter out = response.getWriter();
			out.println
			(
				docType + "<html>\n" + "<head><title>你还没登录啊</title></head>\n" 
				+ "<body>"
				+ "<script>alert('你还没登录啊。'); window.location='index.jsp' </script>"
				+ "</body>"
				+ "</html>"
			);
		}
		else
		{
			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException
	{
		// TODO Auto-generated method stub
	}
}
