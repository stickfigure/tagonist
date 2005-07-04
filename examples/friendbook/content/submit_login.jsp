<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" uri="http://www.tagonist.org/tagonist" %>

<t:action var="model" type="org.tagonist.friendbook.action.Login"/>

<c:choose>
	<c:when test="${empty model.errors}">
		<c:choose>
			<c:when test="${empty model.dest}">
				<c:redirect url="friends.jsp"/>
			</c:when>
			<c:otherwise>
				<c:redirect url="${model.dest}"/>
			</c:otherwise>				
		</c:choose>
	</c:when>
	<c:otherwise>
		<jsp:forward page="index.jsp"/>
	</c:otherwise>
</c:choose>
