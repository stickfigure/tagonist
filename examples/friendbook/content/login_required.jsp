<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fb" tagdir="/WEB-INF/tags" %>

<fb:outside title="Login Required">
	<p>
		You must login first.
	</p>
	
	<form action="submit_login.jsp" method="post">
		<input type="hidden" name="dest" value="<c:out value="${dest}"/>"/>

		<table>
			<tr>
				<td>Username:</td>
				<td>
					<input type="text" name="name""/>
				</td>
			</tr>
			<tr>
				<td>Password:</td>
				<td>
					<input type="password" name="password""/> 
					<input type="submit" value="Login"/>
				</td>
			</tr>
		</table>
	</form>

	<p>
		Would you like to <a href="signup.jsp">create an account</a>?
	</p>
</fb:outside>