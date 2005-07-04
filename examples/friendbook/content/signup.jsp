<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fb" tagdir="/WEB-INF/tags" %>

<fb:outside title="Sign Up">
	<p>
		To create an account, just fill out this form:
	</p>
	
	<form action="submit_signup.jsp" method="post">
		<table>
			<tr>
				<td>Username: </td>
				<td>
					<input type="text" name="name" value="<c:out value="${model.name}"/>" />
					<c:if test="${!empty model.errors.name}">
						<c:out value="${model.errors.name}"/>
					</c:if>
				</td>
			</tr>
			<tr>
				<td>Password:</td>
				<td>
					<input type="password" name="password" value="<c:out value="${model.password}"/>"/>
					<c:if test="${!empty model.errors.password}">
						<c:out value="${model.errors.password}"/>
					</c:if>
				</td>
			</tr>
			<tr>
				<td>Password Again:</td>
				<td>
					<input type="password" name="passwordAgain" value="<c:out value="${model.passwordAgain}"/>"/>
					<c:if test="${!empty model.errors.passwordAgain}">
						<c:out value="${model.errors.passwordAgain}"/>
					</c:if>
				</td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="Signup"/></td>
			</tr>
		</table>
	</form>
</fb:outside>
				