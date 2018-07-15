<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/webjars/global-config/css/layout.css" />

<c:if test="${not empty roleList}">
	<div id="welcomeContainer">
		<h1>Welcome To Hello World</h1>
	</div>
</c:if>
