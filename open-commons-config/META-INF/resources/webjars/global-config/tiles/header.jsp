<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/webjars/global-config/css/header.css" />

<div id="header">
	<a id="logo" href="${pageContext.request.contextPath}" target="_blank"><img
		src="<%=request.getContextPath()%>/webjars/global-config/images/logo.png" height="50" width="50"/></a>
	<div id="rightSide">
		<p id="welcome">Welcome To App Config Page: ${userPrincipal}</p>
	</div>
</div>
<div id="color_line"></div>

    <div id="timeoutDialog"></div>
    <div id="alertDialog"></div>
    <div id="confirrmDialog"></div>
