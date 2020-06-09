<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <base href="<%=basePath%>">
    <title>Ephemeral在线社交平台--by:wzy</title>
	<link href="./css/main-rules.css" rel="stylesheet" />
	<link href="./css/reset.css" rel="stylesheet" />
	<script type="text/javascript" src="./js/chatWindow.js" charset="utf-8"></script>
	<script type="text/javascript" src="./js/coverDiv.js" charset="utf-8"></script>
</head>
<body onbeforeunload="onUnload();">
<%
	session.setAttribute("user", null);
	session.setAttribute("userName", null);
	session.setAttribute("ip", null);
	session.setAttribute("talkName", null);
	session.setAttribute("connection_builded_talking_to_no", 0);
	String userName = "";
%>
	<div class="box">
		<div class="head">
			<div class="logo">
				<h1 class="logo-h">Ephemeral平台</h1>
				<h2 class="license">项目已上传至<a>github</a>，请遵守Apache开源协议，注明代码出处</h2>
			</div>
			<div class="userNameShow">
					<h2 id="userNameShow_h">点击发送按钮，设置你的聊天昵称</h2>
				</div>
			<div class="exitShow">
				<h2>
					<a href="./do_unRegis">退出</a>
				</h2>
			</div>
		</div>
		<div class="navigate" id="navigate">
			<ul class="navigate-ul">
				<li><a href="action/login.jsp" target="iframe_talk_con">账号登录</a></li>
				<li><a href="action/talk_con.jsp" target="iframe_talk_con">聊天窗口</a></li>
				<li><a href="action/info.jsp" target="iframe_talk_con">个人资料</a></li>
				<li><a href="javascript: fillterMove();">条件筛选</a></li>
				<li><a href="action/vip.jsp" target="iframe_talk_con">充值会员</a></li>
				<li><a href="html/author.html" target="iframe_talk_con">关于我自己</a></li>
			</ul>
		</div>
		<div class="content" id="content">
			<div class="filter">
			<form action="#">
				<ul class="filter-ul">
					<li><p>筛选匹配的用户</p></li>
					<li>
						<p style="display: inline-block;width: 50px">&nbsp;性别:</p>
						<select id="sex" style="display: inline-block; width: 56%;">
			                <option>男性</option>
			                <option>女性</option>
		            	</select>
					</li>
					<li>
						<p style="display: inline-block;width: 50px">&nbsp;年龄:</p>
						<select id="sex" style="display: inline-block; width: 56%;">
			                <option>18-25</option>
			                <option>26-35</option>
			                <option>36-45</option>
			                <option>46-55</option>
		            	</select>
					</li>
					<li>
						<p style="display: inline-block;width: 50px;">&nbsp;会员:</p>
						<select id="sex" style="display: inline-block; width: 56%;">
			                <option >要求</option>
			                <option>不要求</option>
		            	</select>
					</li>
					<li>
						<p style="display: inline-block;width: 50px;">&nbsp;<font style="text-decoration:line-through">筛选:</font></p>
						<input type="submit" value="开始筛选" style="background-color: white; width: 56%;" disabled="disabled">
					</li>
				</ul>
			</form>
			</div>
			<iframe name="iframe_talk_con" class="iframe_talk_con" src="<%=basePath%>html/author.html" frameborder="0" scrolling="no">
				
			</iframe>
		</div>
		<div class="float-cover-getName" id="float-cover-getName">
			<div class="cover-float">
				<form action="action/talk_con.jsp" target="iframe_talk_con">
					<table class="getName-t" style="width: 340px;">
						<tr>
							<td>
								<p class="getName-p" >输入你要使用的用户名：</p>
							</td>
							<td>
								<input class="getName-input" name = "talkName" value=""/>
							</td>
						</tr>
						<tr>
							<td>
								<input type="submit" value="提交用户名" onclick="change_cover_getName_re();"/>
							</td>
							<td align="right" >
								<input type="button" value="取消"  onclick="change_cover_getName_re();"/>
							</td>
						</tr>
					</table>
				</form>
			</div> 
		</div>
		<iframe name="exe_jsp" class="exe_jsp" sandbox="allow-scripts">
				
		</iframe>
	</div>
</body>
</html>