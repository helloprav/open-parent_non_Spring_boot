<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
#header {
	background-color: lightblue;
	width: 100%;
	height: 50px;
	text-align: center;
}

#sidebar-left {
	float: left;
	width: 15%;
	background-color: grey;
}

#main {
	float: left;
	width: 70%;
	background-color: lightgray;
}

#footer {
	clear: both;
	height: 50px;
	width: 100%;
	text-align: center;
	background-color: lightblue;
}

#sidebar-left, #main, #sidebar-right {
	min-height: 600px
}
</style>
</head>
<body>Hello World: <%=new java.util.Date() %> <br>
<div id="header">Header</div>
        <div id="sidebar-left">Left</div>
        <div id="main">Main</div>
        <div id="footer">Footer</div>
</body>
</html>