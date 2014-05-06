
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" uri="http://www.tagonist.org/tagonist" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<t:action var="user" type="com.voodoodyne.tagonist.demo.GetUser"/>
<t:action var="bookmarks" type="com.voodoodyne.tagonist.demo.GetBookmarks"/>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
	<head>
		<title>Bookmarks</title>
  	</head>
	
	<body>
		<h1>Bookmarks</h1>
		
		<p>
			This is the demonstration from README.html.  It uses two tagonist actions,
			one which gets a User object and one which gets the bookmarks for the user.
		</p>
		
		<p>
			You can also explicitly get bookmarks for
			<a href="bookmarks.jsp?id=1">user id 1</a> and
			<a href="bookmarks.jsp?id=2">user id 2</a>.
		</p>
		
		<p>Here are the bookmarks for ${user.name}:</p>
		<table>
			<c:forEach var="mark" items="${bookmarks}">
				<tr>
					<td>${mark.title}</td>
					<td><a href="${mark.url}">${mark.url}</a></td>
				</tr>
			</c:forEach>
		</table>
</html>


