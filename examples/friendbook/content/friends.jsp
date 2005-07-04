
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fb" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="t" uri="http://www.tagonist.org/tagonist" %>

<t:action var="friends" type="org.tagonist.friendbook.action.GetFriends"/>

<fb:inside title="Friends">
	
	<table cellspacing="1" cellpadding="5" border="0" align="center">
		<tr>
			<th>Name</th>
			<th>Address</th>
			<th>Phone Numbers</th>
			<th>Email Addresses</th>
		</tr>
		<c:forEach var="friend" items="${friends}">
			<c:choose>
				<c:when test="${friend.login == auth.me.login}">
					<c:set var="styleAttrs">background-color: yellow</c:set>
				</c:when>
				<c:otherwise>
					<c:set var="styleAttrs"></c:set>
				</c:otherwise>
			</c:choose>
	
			<tr>
				<td style="<c:out value="${styleAttrs}"/>" align="left" valign="top">
					<c:out value="${friend.lastName}"/>,&nbsp;<c:out value="${friend.firstName}"/>
					<c:if test="${friend.login == model.myLogin}">
						<br/>(<a href="edit.jsp">Edit</a>)
					</c:if>
				</td>
				<td style="<c:out value="${styleAttrs}"/>" align="left" valign="top">
					<%-- check for address information before processing --%>
					
					<c:choose>
						<c:when test="${!empty friend.address}">
							<%-- we use the test to exclude extra line breaks --%>
							
							<c:if test="${!empty friend.address.addressLine1}">
								<c:out value="${friend.address.addressLine1}"/><br/>
							</c:if>
							<c:if test="${!empty friend.address.addressLine2}">
								<c:out value="${friend.address.addressLine2}"/><br/>
							</c:if>
							<c:if test="${!empty friend.address.city}">
								<c:out value="${friend.address.city}"/><br/>
							</c:if>
							<c:if test="${!empty friend.address.state}">
								<c:out value="${friend.address.state}"/><br/>
							</c:if>
						</c:when>
						<c:otherwise>
							&nbsp;
						</c:otherwise>
					</c:choose>
				</td>
				<td style="<c:out value="${styleAttrs}"/>" align="left" valign="top">
					<%-- check for phone information before processing --%>
	
					<c:choose>
						<c:when test="${!empty friend.phoneList}">
							<c:forEach var="phone" items="${friend.phoneList}">
								<c:out value="${phone}"/><br/>
							</c:forEach>
						</c:when>
						<c:otherwise>
							&nbsp;
						</c:otherwise>
					</c:choose>
				</td>
				<td style="<c:out value="${styleAttrs}"/>" align="left" valign="top">
					<c:choose>
						<c:when test="${!empty friend.emailList}">
							<c:forEach var="email" items="${friend.emailList}">
								<c:out value="${email}"/><br/>
							</c:forEach>
						</c:when>
						<c:otherwise>
							&nbsp;
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</c:forEach>
	</table>
</fb:inside>