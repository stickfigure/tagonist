
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" uri="http://www.tagonist.org/tagonist" %>

<t:action var="model" type="org.tagonist.demo.Signup"/>

<c:choose>
	<c:when test="${empty model.error}">
		<c:redirect url="index.jsp"/>
	</c:when>
	<c:otherwise>
		<jsp:forward page="signup.jsp"/>
	</c:otherwise>
</c:choose>
		