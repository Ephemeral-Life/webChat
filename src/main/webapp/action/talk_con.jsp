<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Ephemeral.webChat.*" %>
<%@ page import="Ephemeral.webChat.entity.user"%>
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
		<title>talk_con</title>
		<link href="./css/main-rules.css" rel="stylesheet" />
		<script type="text/javascript" src="./js/chatWindow.js" charset="utf-8"></script>
		<script type="text/javascript" src="./js/coverDiv.js" charset="utf-8"></script>
	</head>
	<body>
	<jsp:useBean id="os" class="Ephemeral.webChat.service.impl.onlineServiceImpl"></jsp:useBean>
	<jsp:useBean id="user" class="Ephemeral.webChat.entity.user"></jsp:useBean>
	<jsp:useBean id="online" class="Ephemeral.webChat.entity.online"></jsp:useBean>
		<%
			int connection_builded_talking_to_no = 0;
			connection_builded_talking_to_no = Integer.valueOf(session.getAttribute("connection_builded_talking_to_no").toString());
			String talkName = null;
			String ip = request.getHeader("x-forwarded-for");
			boolean checkIP = true;
			if (ip == null || ip.length() == 0 || "unkown".equalsIgnoreCase(ip) || ip.split(".").length != 4)
			{
				checkIP = false;
			}
		    if (!checkIP)
		    {
		        ip = request.getHeader("Proxy-Client-IP");
		    }
		    if (!checkIP)
		    {
		        ip = request.getHeader("WL-Proxy-Client-IP");
		    }
		    if (!checkIP)
		    {
		        ip = request.getRemoteAddr();
		    }
		    session.setAttribute("ip", ip);
		    talkName = request.getParameter("talkName");
		    session.setAttribute("talkName", talkName);
		    try
			{
		    	talkName = session.getAttribute("talkName").toString();
		    	user = (user)session.getAttribute("user");
		    	if(user==null)
		    	{
		    		session.setAttribute("onlineNo", os.newOnlineUser(talkName, 0, ip));
		    		online.setAccount_id(0);
		    	}
		    	else
		    	{
		    		session.setAttribute("onlineNo", os.newOnlineUser(talkName, user.getAccount_id(), ip));
		    		online.setAccount_id(user.getAccount_id());
		    	}
		    	online.setIp(ip);
		    	online.setUser_name(talkName);
		    	online.setNo(Integer.valueOf(session.getAttribute("onlineNo").toString()));
		    	session.setAttribute("online", online);
		%>
			<script type="text/javascript">
				window.parent.document.getElementById('userNameShow_h').innerHTML="当前用户名：<%=talkName%>";
			</script>
		<%
			}catch(NullPointerException e)
			{
			}
		%>
		<div class="talk_con">
			<iframe id="talk_show_iframe" class="talk_show_iframe" name="talk_show" sandbox="allow-scripts" frameborder="0" scrolling="no">
			
			</iframe>
			<div class="talk_input">
				<button id="talk_start" class="talk_start" onclick="catchUser('{<%= talkName%>}', '<%=basePath%>')" disabled="disabled">SEARCH</button>
				<input type="text" class="talk_word" id="talkwords" onclick="checkTalkNameByInput('{<%= talkName%>}')">
				<input type="button" value="发送" class="talk_sub" id="talksub" onclick="checkTalkName('{<%= talkName%>}');">
			</div>
		</div>
		<iframe name="exe_jsp" class="exe_jsp" sandbox="allow-scripts">
				
		</iframe>
		<%
			if(talkName!=null)
			{
				%>
					<script type="text/javascript">
						document.getElementById("talk_start").disabled=false;
					</script>
				<%
			}
		%>
	</body>
</html>
