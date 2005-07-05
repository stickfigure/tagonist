
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" uri="http://www.tagonist.org/tagonist" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
	<head>
		<title>Signup</title>
  	</head>
	
	<body>
		<h1>Signup</h1>
		
		<p>
			This is the form processing demonstration from README.html.  It shows how you
			can do MVC form processing, just like Struts, but without the complexity.
			Try entering valid and invalid email addresses.
		</p>
		
		<c:if test="${!empty model.error}">
			<p>Error: ${model.error}<p>
		</c:if>
		
		<form action="submit_signup.jsp">
			Email:  <input type="text" name="email" value="${model.email}"/>
			<input type="submit" value="Submit">
		</form>
</html>
