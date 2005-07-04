<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" uri="http://www.tagonist.org/tagonist" %>

<t:action var="model" type="org.tagonist.friendbook.action.SaveInfo"/>

<c:choose>
	<c:when test="${empty model.errors}">
		<c:redirect url="friends.jsp"/>
	</c:when>
	<c:otherwise>
		<jsp:forward page="edit.jsp"/>
	</c:otherwise>
</c:choose>
