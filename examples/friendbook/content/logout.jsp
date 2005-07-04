<%@ taglib prefix="t" uri="http://www.tagonist.org/tagonist" %>
<%@ taglib prefix="fb" tagdir="/WEB-INF/tags" %>

<t:action type="org.tagonist.friendbook.action.Logout"/>

<fb:outside title="Logout">
	<p>
		Bye!
	</p>
	
	<p>
		<a href="index.jsp">Login</a> again?
	</p>
</fb:outside>