<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" uri="http://www.tagonist.org/tagonist" %>

<%@ attribute name="title" required="true" %>

<%-- Ensures that only logged-in users can access these pages, also provides a way of getting a little info --%>
<t:action var="auth" type="org.tagonist.friendbook.action.AuthRequired"/>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
 
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
	<head>
		<title><c:out value="${title}"/></title>
  	</head>
	
	<body>
		<h1><c:out value="${title}"/></h1>
		
		<table width="100%">
			<tr>
				<td valign="top">
					<h3>Menu</h3>
					<div>
						<a href="friends.jsp">Friends</a>
					</div>
					<div>
						<a href="edit.jsp">Edit My Info</a>
					</div>
					<div>
						<a href="change_pw.jsp">Change Password</a>
					</div>
					<div>
						<a href="logout.jsp">Logout</a>
					</div>
				</td>
				<td>
					<jsp:doBody/>
				</td>
			</tr>
		</table>
	</body>
</html>
