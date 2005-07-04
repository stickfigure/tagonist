<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fb" tagdir="/WEB-INF/tags" %>

<fb:outside title="Welcome">
	<p>
		Welcome to the Friendbook example.  This is a simple contact-list
		application which demonstrates how to create a membership-based
		website with Tagonist.
	</p>
	
	<form action="submit_login.jsp" method="post">
		<input type="hidden" name="dest" value="<c:out value="${dest}"/>"/>
		
		<table>
			<tr>
				<td>Username:</td>
				<td>
					<input type="text" name="name" value="<c:out value="${model.name}"/>"/>
					<c:if test="${!empty model.errors.name}">
						<c:out value="${model.errors.name}"/>
					</c:if>
				</td>
			</tr>
			<tr>
				<td>Password:</td>
				<td>
					<input type="password" name="password" value="<c:out value="${model.password}"/>"/> 
					<input type="submit" value="Login"/>
					<c:if test="${!empty model.errors.password}">
						<c:out value="${model.errors.password}"/>
					</c:if>
				</td>
			</tr>
		</table>
	</form>

	<p>
		Would you like to <a href="signup.jsp">create an account</a>?
	</p>
</fb:outside>