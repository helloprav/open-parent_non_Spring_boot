<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<link rel="stylesheet" type="text/css"  href="${pageContext.request.contextPath}/webjars/global-config/css/layout.css" />
<link rel="stylesheet" type="text/css"  href="${pageContext.request.contextPath}/webjars/global-config/css/jquery.dialogbox.css" />

<script type="text/javascript"
    src="<c:url value="/webjars/global-config/js/jquery-3.2.1.min.js" />"></script>
<script type="text/javascript"
    src="<c:url value="/webjars/global-config/js/app.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/webjars/global-config/js/jquery.dialogBox.js" />"></script>

<title><tiles:getAsString name="title" /></title>
</head>
	
<body>
	<div>
		<div id="header">
			<tiles:insertAttribute name="header" ignore="true" />
		</div>
		
		<div id="menu">
			<tiles:insertAttribute name="menu" ignore="true" />
		</div>

		<div id="content">
			<tiles:insertAttribute name="body" ignore="true" />
		</div>

		<div id="Footer" style="FONT-FAMILY: 'Century Schoolbook L';">
			<tiles:insertAttribute name="footer" ignore="true" />
		</div>
	</div>

</body>

</html>