<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>msg</title>
</head>
<body>
	<h1>注册成功</h1>
	<s:actionmessage/>
	<table>
		<ol>
			<li><a href="${pageContext.request.contextPath }/ssh_shop/index.action">首页</a></li>
			<li><a href="${pageContext.request.contextPath }/ssh_shop/user_toRegisterPage.action">注册</a></li>
		</ol>
	</table>
</body>
</html>