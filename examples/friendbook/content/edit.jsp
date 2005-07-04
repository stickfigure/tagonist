<%--
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fb" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="t" uri="http://www.tagonist.org/tagonist" %>

<t:action recycle="true" var="model" type="org.tagonist.friendbook.action.GetInfo"/>

<fb:inside title="Edit Your Info">
	<p>
		Please make your changes:
	</p>
	
	<form action="submit_edit.jsp" method="post">
		<table>
			<tr>
				<td>First Name:</td>
				<td>
					<input name="firstName" value="<c:out value="${model.firstName}"/>"/>
					<c:out value="${model.errors.firstName}"/>
				</td>
			</tr>
			<tr>
				<td>Last Name:</td>
				<td>
					<input name="lastName" value="<c:out value="${model.lastName}"/>"/>
					<c:out value="${model.errors.lastName}"/>
				</td>
			</tr>
	
			<tr>
				<td colspan="3">&nbsp;</td>
			</tr>
			<tr>
				<td>Address:</td>
				<td>
					<input type="text" name="addrLine1" value="<c:out value="${model.addrLine1}"/>"/>
					<c:out value="${model.errors.addrLine1}"/>
				</td>
			</tr>
			<tr>
				<td>(line 2)</td>
				<td>
					<input type="text" name="addrLine2" value="<c:out value="${model.addrLine2}"/>"/>
					<c:out value="${model.errors.addrLine2}"/>
				</td>
			</tr>
			<tr>
				<td>State:</td>
				<td>
					<input type="text" name="addrState" value="<c:out value="${model.addrState}"/>"/>
					<c:out value="${model.errors.addrState}"/>
				</td>
			</tr>
			<tr>
				<td>City:</td>
				<td>
					<input type="text" name="addrCity" value="<c:out value="${model.addrCity}"/>"/>
					<c:out value="${model.errors.addrCity}"/>
				</td>
			</tr>
	
			<tr>
				<td colspan="3">&nbsp;</td>
			</tr>
			<tr>
				<td colspan="3" style="font-weight: bold">Phone numbers:</td>
			</tr>
	
			<%-- list phone numbers --%>
			<c:forEach var="phone" items="${model.phoneList}" varStatus="loop">
				<tr>
					<td>del? <input type="checkbox" name="delPhone" value="${loop.index}"/></td>
					<td>
						<input size="40"
							type="text"
							name="phoneList"
							value="<c:out value="${phone}"/>"/>
					</td>
					<td>
						<c:out value="${model.errors.phoneList[$loop.index]}"/>
					</td>
				</tr>
			</c:forEach>
	
			<tr>
				<td>Add Number</td>
				<td>
					<input size="40" type="text" name="phoneList" value=""/>
				</td>
			</tr>
	
			<tr>
				<td colspan="3" style="font-weight: bold">Email Addresses:</td>
			</tr>
	
			<%-- list emails --%>
			<c:forEach var="email" items="${model.emailList}" varStatus="loop">
				<tr>
					<td>del? <input type="checkbox" name="delEmail" value="${loop.index}"/></td>
					<td>
						<input size="40"
							type="text"
							name="emailList"
							value="<c:out value="${email}"/>"/>
					</td>
					<td>
						<c:out value="${model.errors.emailList[$loop.index]}"/>
					</td>
				</tr>
			</c:forEach>
			<tr>
				<td>Add Email</td>
				<td><input size="40" type="text" name="emailList" value=""/></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="Save"/></td>
			</tr>
		</table>
	</form>
</fb:inside>