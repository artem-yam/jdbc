<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title>Вход</title>
</head>
<body>
	<form name="loginForm" method="POST" 
		action="${pageContext.request.contextPath}/FrontController">
		<input type="hidden" name="command" value="firstName" /> Login:<br />
		<input type="text" name="firstName" value="" /> <br />Password:<br />
		<input type="lastName" name="lastName" value="" /> <br />
		${errorLoginPassMessage} <br /> 
		${wrongAction} <br /> 
		${nullPage} <br />
		${sessionTimeout} <br />
		${AnonymousFilterMessage} <br />
		${UserRightsFilterMessage} <br />
		<input type="submit" value="Log in" />
	</form>
	<hr />
</body>
</html>