<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Ephemeral.webChat.entity.user"%>
<%@ page import="Ephemeral.webChat.entity.info"%>
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
	<title>用户信息</title>
	<link href="./css/main-rules.css" rel="stylesheet" />
	<link href="./css/reset.css" rel="stylesheet" />
</head>
<body>
<jsp:useBean id="is" class="Ephemeral.webChat.service.impl.infoServiceImpl"></jsp:useBean>
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
		info info = is.getInfo(user.getAccount_id());
%>
	<div class="box-info">
		<form action="./do_setInfo">
			<table>
				<tr>
					<td colspan="2">
						<p>
							配置个人信息，匹配优质用户
						</p>
					</td>
				</tr>
				<tr>
					<td align="right">
						<p>
							性别：
						</p>
					</td>
					<td>
						<select name="sex" style="display: inline-block; width: 30%">
							<option value="男" style="width: 30%">男性</option>
							<option value="女" style="width: 30%">女性</option>
						</select>
						<%
							if(info != null)
								out.print("<p style='color:black; font-size:20px; display:inline-block;'>&nbsp;当前为："+info.getSex()+"</p>");
						%>
					</td>
				</tr>
				<tr>
					<td align="right">
						<p>
							年龄：
						</p>
					</td>
					<td>
						<select name="age" style="display: inline-block; width: 30%;">
							<%
								for(int i = 18; i <= 55; i++)
								{
									%>
										<option value="<%=i%>" style="width: 30%"><%=i%>岁</option>
									<%
								}
							%>
						</select>
						<%
							if(info != null)
								out.print("<p style='color:black; font-size:20px; display:inline-block;'>&nbsp;当前为："+info.getAge()+"</p>");
						%>
					</td>
				</tr>
				<tr>
					<td align="right">
						<p>
							提交：
						</p>
					</td>
					<td>
						<input type="submit" value="提交" style="width: 30%">
					</td>
				</tr>
			</table>
		</form>
	</div>
	<%
	}
	%>
</body>
</html>