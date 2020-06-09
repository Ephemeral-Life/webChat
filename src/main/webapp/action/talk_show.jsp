<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Ephemeral.webChat.*" %>
<%@ page import="Ephemeral.webChat.entity.user"%>
<%@ page import="Ephemeral.webChat.entity.online"%>
<%@ page import="Ephemeral.webChat.entity.massage"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<base href="<%=basePath%>">
		<title>talk_show</title>
		<link href="./css/main-rules.css" rel="stylesheet" />
		<script type="text/javascript" src="./js/chatWindow.js" charset="utf-8"></script>
		<script type="text/javascript" src="./js/coverDiv.js" charset="utf-8"></script>
	</head>
	<body>
		<jsp:useBean id="os" class="Ephemeral.webChat.service.impl.onlineServiceImpl"></jsp:useBean>
		<jsp:useBean id="user" class="Ephemeral.webChat.entity.user"></jsp:useBean>
		<jsp:useBean id="ms" class="Ephemeral.webChat.service.impl.massageServiceImpl"></jsp:useBean>
		<jsp:useBean id="vs" class="Ephemeral.webChat.service.impl.vipServiceImpl"></jsp:useBean>
		<jsp:useBean id="massage" class="Ephemeral.webChat.entity.massage"></jsp:useBean>
		<jsp:useBean id="online" class="Ephemeral.webChat.entity.online"></jsp:useBean>
		<div class="talk_show" id="words">
		</div>
		<%
		try
		{
			ArrayList<massage> allMassage = new ArrayList<massage>();
			int connection_builded_talking_to_no = 0;
			connection_builded_talking_to_no = Integer.valueOf(session.getAttribute("connection_builded_talking_to_no").toString());
			String talkName = session.getAttribute("talkName").toString();
		    String ip = session.getAttribute("ip").toString();
		    online onlineInfo = (online)session.getAttribute("online");
		    long vip = 0;
		    try
		    {
		    	user = (user)session.getAttribute("userAccount");
		    	vip = vs.checkVip(user.getAccount_id());
		    }catch(Exception e)
		    {
		    	vip = 0;
		    }
		    try
		    {
		    	if((ArrayList<massage>)session.getAttribute("allMassage") != null)
		    		allMassage = (ArrayList<massage>)session.getAttribute("allMassage");
		    }catch(NullPointerException e)
		    {
		    	allMassage = new ArrayList<massage>();
		    }
			if(connection_builded_talking_to_no == 0)
			{
				%>
				<script type="text/javascript">
					var Words = document.getElementById("words");
					var str = '<div class="searching_note"><span>正在为你搜索...</span></div>';
					Words.innerHTML = Words.innerHTML + str;
				</script>
				<%
			}
			else
			{
				String connection_builded_talking_to_userName = os.getUserName(connection_builded_talking_to_no);
				String connection_builded_talking_to_ip = os.getUserIp(connection_builded_talking_to_no);
				if(vip == 0)
				{
					%>
					<script type="text/javascript">
						var Words = document.getElementById("words");
						var str = '<div class="searching_note"><span>已为你匹配到名为：<%= connection_builded_talking_to_userName%>的用户</span></div>';
						Words.innerHTML = Words.innerHTML + str;
						gotIt();
					</script>
					<%
				}
				else
				{
					%>
					<script type="text/javascript">
						var Words = document.getElementById("words");
						var str = '<div class="searching_note"><span>已为你匹配到名为：<%= connection_builded_talking_to_userName%>的用户，他的ip为：<%=connection_builded_talking_to_ip%></span></div>';
						Words.innerHTML = Words.innerHTML + str;
						gotIt();
					</script>
					<%
				}
				try
				{
					if(os.checkConnect(onlineInfo.getNo()) != connection_builded_talking_to_no && connection_builded_talking_to_no != 0)
					{
						%>
							<script type="text/javascript">
								var Words = document.getElementById("words");
								var str = '<div class="searching_alert"><span>你匹配到的人溜走了...</span></div>';
								Words.innerHTML = str;
								missIt();
							</script>
						<%
					}
					else
					{
						ArrayList<massage> list = null;
						list = ms.checkMassage(connection_builded_talking_to_no, onlineInfo.getNo());
						if(list != null)
						{
							boolean flag_exist = false;
							for(massage msg : list)
							{
								if(allMassage != null)
								{
									for(massage amsg : allMassage)
									{
										if(amsg.getSend_time().equals(msg.getSend_time())
												&& amsg.getNo() == msg.getNo()
												&& amsg.getContent().equals(msg.getContent()))
											flag_exist = true;
									}
									if(!flag_exist)
										allMassage.add(msg);
									flag_exist = false;
								}
							}
						}
						//ms.alterReaded(list);
						list = ms.checkMassage(onlineInfo.getNo(),connection_builded_talking_to_no);
						if(list != null)
						{
							boolean flag_exist = false;
							for(massage msg : list)
							{
								if(allMassage != null)
								{
									for(massage amsg : allMassage)
									{
										if(amsg.getSend_time().equals(msg.getSend_time())
												&& amsg.getNo() == msg.getNo()
												&& amsg.getContent().equals(msg.getContent()))
											flag_exist = true;
									}
									if(!flag_exist)
										allMassage.add(msg);
									flag_exist = false;
								}
							}
						}
						if(allMassage != null)
						{
							for(massage msg : allMassage)
							{
								if(msg.getSend_to_online_no() == onlineInfo.getNo())
								{
									%>
									<script type="text/javascript">
									    var Words = document.getElementById("words");
									    str = '<div class="atalk"><span><%=connection_builded_talking_to_userName%>:<%=msg.getContent()%></span></div>';
									    Words.innerHTML = Words.innerHTML + str;
									</script>
									<%
								}
								else
								{
									%>
									<script type="text/javascript">
									    var Words = document.getElementById("words");
									    str = '<div class="btalk"><span><%=talkName%>:<%=msg.getContent()%></span></div>';
									    Words.innerHTML = Words.innerHTML + str;
									</script>
									<%
								}
							}
							session.setAttribute("allMassage", allMassage);
						}
						else
						{
							;
						}
					}
				}catch(NullPointerException e)
				{
					e.printStackTrace();
				}
			}
		}catch(Exception e)
		{
			;
		}
		%>
		<script type="text/javascript">
			function refresh()
			{
		        window.location.reload();
			}
		 	setTimeout('refresh()', 3000);
		</script>
	</body>
</html>
