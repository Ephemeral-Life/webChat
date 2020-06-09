<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	<title>登录--by:wzy</title>
	<link href="./css/main-rules.css" rel="stylesheet" />
	<link href="./css/reset.css" rel="stylesheet" />
	<script type="text/javascript">
		function reload()
		{
			document.getElementById("image").src="<%=request.getContextPath() %>/do_verify?date="+new Date().getTime();
			$("#checkcode").val("");
		}
	</script>
</head>
<body>
	<div class="box">
		<form name="form1" id="form1" method="post" action="./do_register">
			<table>
				<tr>
					<td colspan="2" align="center">
						<p>
							<b style="font-size: 40px">
								注册页面
							</b>
						</p>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<p>&nbsp;</p>
					</td>
				</tr>
				<tr>
					<td>
						<p>
							账号：
						</p>
					</td>
					 <td>
						<input name="username" type="text">
					</td>
				</tr>
				<tr>
					<td>
						<p>
							密码:
						</p>
					</td>
					 <td>
						<input name="password" type="password">
					</td>
				</tr>
				<tr>
					<td>
						<p>
							确认密码:
						</p>
					</td>
					 <td>
						<input name="confirm_password" type="password">
					</td>
				</tr>
				<tr>
					<td>
						<p>
							验证码:
						</p>
					</td>
					<td>
						<input type="text" name="checkcode"  id="checkcode" style="width: 68px;"/>
					  	<img src="<%=request.getContextPath() %>/do_verify" alt="验证码" id="image" style="width: 58px; height: 18px;" onclick="javascript:reload();" />
					</td>
				</tr>
				<tr>
					<td width="25%" height="22"></td>
					<td width="75%" height="22">
					<input type="submit" value="注册" style="width: 65px;"> 
					<input type="button" value="返回" style="width: 65px;"
						onclick="javascript:window.location.href='./action/login.jsp'">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>