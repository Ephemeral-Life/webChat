package listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MyListener implements ServletContextListener, HttpSessionListener, ServletRequestListener
{

	/*
	 * public void contextInitialized(ServletContextEvent arg0) {
	 * System.out.println("context对象被创建－〉在服务器启动时创建"); } public void
	 * contextDestroyed(ServletContextEvent arg0) {
	 * System.out.println("context对象被销毁－〉在服务器关闭时被销毁"); }
	 * 
	 * public void requestInitialized(ServletRequestEvent arg0) {
	 * System.out.println("request对象被创建－〉在向服务器端发送请求时"); } public void
	 * requestDestroyed(ServletRequestEvent arg0) {
	 * System.out.println("request对象被销毁－〉服务器端向客户端发送响应时"); } public void
	 * sessionCreated(HttpSessionEvent arg0) {
	 * System.out.println("会话开始时创建session对象"); } public void
	 * sessionDestroyed(HttpSessionEvent arg0) {
	 * System.out.println("会话结束时销毁session对象"); }
	 */
	Log log = LogFactory.getLog(getClass()); // 日志记录器
	// 创建sesion的时候被调用

	public void sessionCreated(HttpSessionEvent se)
	{
		// TODO Auto-generated method stub
		HttpSession session = se.getSession();
		log.info("新建的session的ID为：" + session.getId());

	}

	public void sessionDestroyed(HttpSessionEvent se)
	{
		// TODO Auto-generated method stub
		HttpSession session = se.getSession();
		log.info("销毁的session的ID为：" + session.getId());

	}

	// 加载servlet上下文的时候被调用
	public void contextDestroyed(ServletContextEvent sce)
	{
		// TODO Auto-generated method stub
		ServletContext context = sce.getServletContext();
		log.info("即将关闭：" + context.getContextPath());

	}

	public void contextInitialized(ServletContextEvent sce)
	{
		// TODO Auto-generated method stub
		ServletContext context = sce.getServletContext();
		log.info("即将开启：" + context.getContextPath());

	}

	// 创建request的时候被调用
	public void requestInitialized(ServletRequestEvent sre)
	{
		// TODO Auto-generated method stub
		HttpServletRequest httpServletRequest = (HttpServletRequest) sre.getServletRequest();
		String url = httpServletRequest.getRequestURI();// 访问的路径
		url = httpServletRequest.getQueryString() == null ? url : (url + "?" + httpServletRequest.getQueryString());
		httpServletRequest.setAttribute("dateCreate", System.currentTimeMillis());
		log.info("IP地址为：" + httpServletRequest.getRemoteAddr() + "请求" + url);
	}

	public void requestDestroyed(ServletRequestEvent sre)
	{
		// TODO Auto-generated method stub
		HttpServletRequest httpServletRequest = (HttpServletRequest) sre.getServletRequest();
		Long time = System.currentTimeMillis() - (Long) httpServletRequest.getAttribute("dateCreate");
		log.info(httpServletRequest.getRemoteAddr() + "请求结束，耗时：" + time + "毫秒");
	}

}
