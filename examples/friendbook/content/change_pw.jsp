<%--
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fb:inside title="Change Password">
	<p>
		You can change your password by entering it here:
	</p>
	
	<form method="post" action="submit_pw.jsp">
		<table>
			<tr>
				<td>Old Password:</td>
				<td>
					<input value="<c:out value="${model.oldPassword}"/>" name="oldPassword" id="oldPassword" type="password">
				</td>
				<td>
					<c:out value="${model.errors.oldPassword}"/>
				</td>
			</tr>
			<tr>
				<td>New Password:</td>
				<td>
					<input value="<c:out value="${model.newPassword}"/>" name="newPassword" type="password">
				</td>
				<td>
					<c:out value="${model.errors.newPassword}"/>
				</td>
			</tr>
			<tr>
				<td>New Password Again:</td>
				<td>
					<input value="<c:out value="${model.newPasswordAgain}"/>" name="newPasswordAgain" type="password">
				</td>
				<td>
					<c:out value="${model.errors.newPasswordAgain}"/>
				</td>
			</tr>
			<tr>
				<td></td><td><input value="Change" type="submit"></td>
			</tr>
		</table>
	</form>
</fb:inside>