<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	<meta charset="UTF-8">
	<base href="<%=basePath%>">
	<title>come get your vip upgrade</title>
	<link href="./css/main-rules.css" rel="stylesheet" />
	<link href="./css/reset.css" rel="stylesheet" />
	<jsp:useBean id="vs" class="Ephemeral.webChat.service.impl.vipServiceImpl"></jsp:useBean>
</head>
<body>
	<%
		String userName = null;
		try
		{
			userName = session.getAttribute("userName").toString();
		}
		catch(Exception e)
		{
			userName = null;
		}
		if(userName == null)
		{
			%>
			<script type="text/javascript">
				alert("你还没登录！");
				window.location='./action/login.jsp';
			</script>
			<%
		}
		else
		{
			user user = (user)session.getAttribute("userAccount");
			if(vs.checkVip(user.getAccount_id()) == 0)
			{
				%>
				<div class="box">
					<table>
						<tr>
							<td>
								<p style="font-size: 20px">
									你正在访问一个测试项目，你当前还没有vip权限，
								</p>
							</td>
						</tr>
						<tr>
							<td>
								<p style="font-size: 20px">
									<a href="./do_newVip?account_id=<%=user.getAccount_id()%>&month=3" style="color: purple;">点击这里</a>获取3个月vip权限。
								</p>
							</td>
						</tr>
					</table>
				</div>
				<%	
			}
			else if(vs.checkVip(user.getAccount_id()) <= 2160)
			{
				%>
				<div class="box">
					<table>
						<tr>
							<td>
								<p style="font-size: 20px">
									你正在访问一个测试项目，你当前的vip有效期小于3个月，
								</p>
							</td>
						</tr>
						<tr>
							<td>
								<p style="font-size: 20px">
									<a href="./do_moreVip?account_id=<%=user.getAccount_id()%>&month=3" style="color: purple;">点击这里</a>续费3个月vip权限。
								</p>
							</td>
						</tr>
					</table>
				</div>
				<%	
			}
			else
			{
				%>
				<script type="text/javascript">
					alert("用户<%=userName%>，你已经拥有了足够长的vip有效期！");
					window.location='./action/talk_con.jsp';
				</script>
				<%	
			}
		}
	%>
</body>
</html>