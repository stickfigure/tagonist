<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" uri="http://www.tagonist.org/tagonist" %>

<t:action var="model" type="com.voodoodyne.tagonist.friendbook.action.Signup"/>

<c:choose>
	<c:when test="${empty model.errors}">
		<c:redirect url="edit.jsp"/>
	</c:when>
	<c:otherwise>
		<jsp:forward page="signup.jsp"/>
	</c:otherwise>
</c:choose>
