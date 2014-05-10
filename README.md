# Tagonist

Tagonist is a laughably simple java web application framework.

## History
Tagonist is a very old but very simple web framework, composed of two JSP tags. It is not
being actively developed but it doesn't need to be, it's only a handful of lines of code. If you like
it, go ahead and use it. It has been moved to github (from tigris.org - that's how hold this is) and
placed in Maven Central to preserve its history. Note that package has changed from org.tagonist to
com.voodoodyne.tagonist.

## Releases
Releases are available in Maven Central and can be downloaded from http://search.maven.org/

```xml
<dependency>
	<groupId>com.voodoodyne.tagonist</groupId>
	<artifactId>tagonist</artifactId>
	<version>1.4</version>
</dependency>
```
	
## Overview
Tagonist is an ultra-lightweight MVC webapp framework, similar in principle to
Struts, WebWork, or Maverick.  Like its cousins, Tagonist offers:

* Clean separation of code and content - no need for scriptlets
* Form processing by conveniently populating beans from the HTTP request attributes

However, *unlike* its cousins, Tagonist:

* Is trivial to learn and use.  One look at the examples and you will understand how to write your webapp immediately.  There are just two custom tags.
* Is trivial to configure.  There no servlets to configure and no XML to muck with. Just drop the tagonist.jar in your WEB-INF/lib and start using the two tagonist custom tags in your JSPs.
* Integrates seamlessly into any JSP-based portal or even other webapp frameworks. Your JSP includes tagonist actions, not the other way around.
* Encourages development of fine-grained actions which can be composed arbitrarily on a page.  You don't need to have one action class per screen.
* Allows many HTML developers to work on a project without fighting over checkouts for some sort of XML sitemap configuration file.
* Is composed of 8 java classes and less than 500 lines of code (including whitespace). Anyone can see how it works.

## Simple JSP Pages
In essence, tagonist turns traditional MVC frameworks inside-out.  Here is a simple
tagonist example which displays a list of bookmarks for a user:

```html
<%-- bookmarks.jsp --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" uri="http://www.tagonist.org/tagonist" %>

<t:action var="user" type="com.example.action.GetUser"/>
<t:action var="bookmarks" type="com.example.action.GetBookmarks"/>

<html>
	<head><title>Bookmarks</title></head>
	<body>
		<p>Here are the bookmarks for ${user.name}:</p>
		<table>
			<c:forEach var="mark" items="${bookmarks}">
				<tr><td>${mark.title}</td><td>${mark.url}</td></tr>
			</c:forEach>
		</table>
	</body>
</html>
```
	
This page uses the `action` tag to instantiate and execute two actions.
The first obtains information about a user, including the user's name.  The second obtains
a list of bookmarks for a user.  Standard JSTL tags and expressions are used to render
the data as HTML.

Here is what the `GetUser` class might look like:

```java
/** GetUser.java:  Gets a UserDetail object and sets it as the model */
public class GetUser extends AbstractAction
{
	Long userId;
	public void setUserId(Long value) { this.userId = value; }
	
	public void execute() throws Exception
	{
		UserDetail detail = getUserDetail(this.userId);
			
		this.getCtx().setModel(detail);
	}
}
```
	
If you are familiar with Struts, WebWork, or Maverick, the Action class should look familiar. The key details:

* A fresh instance of the action class is instantiated every time the action tag is invoked.
* Bean properties on the action instance are populated from the HTTP request parameters.  In this example, the URL would be invoked like this:  http://www.example.com/bookmarks.jsp?userId=42
* An `ActionContext` is available to the action, which provides access to the servlet objects
* The action sets the object that is to become the resultant model.  If no model is explicitly set, the action itself becomes the model, and its getter methods are available in the JSP.

Note that this is only one pattern of action class available; many others are possible
by implementing the `Action` interface instead of extending `AbstractAction`.

## Simple Form Processing
The typical web Model-View-Controller pattern goes something like this:

1. A front servlet (the controller) receives your request
2. The servlet looks up configuration in an XML sitemap file to determine which action class to execute
3. The action class is executed, builds a data model from POJOs, and selects a view for rendering (usually just Success or Error)
4. The servlet determines which JSP is associated with the view (again, using the XML sitemap) and forwards

Tagonist form processing is similar, but replaces the front controller and XML sitemap
with simple JSP files.  This is a much more intuitive approach:

```html
<%-- signup.jsp --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
	<head><title>Signup</title></head>
	<body>
		<c:if test="${!empty model.error}">
			<p>Error: ${model.error}<p>
		</c:if>
		
		<form action="submit_signup.jsp">
			Email:  <input type="text" name="email" value="${model.email}"/>
			<input type="submit" value="Submit">
		</form>
	</body>
</html>
```

```html
<%-- signup_submit.jsp --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" uri="http://www.tagonist.org/tagonist" %>

<t:action var="model" type="com.example.action.Signup"/>

<c:choose>
	<c:when test="${empty model.error}">
		<c:redirect url="thanks.jsp"/>
	</c:when>
	<c:otherwise>
		<jsp:forward page="signup.jsp"/>
	</c:otherwise>
</c:choose>
```

```java
/** Signup.java:  Form processing action for signup */
public class Signup extends AbstractAction
{
	String email;
	public String getEmail() { return this.email; }
	public void setEmail(String value) { this.email = value; }
	
	String error;
	public String getError() { return this.error; }
	
	public void execute() throws Exception
	{
		if (this.email == null || this.email.indexOf('@') < 0)
			this.error = "That is not a valid email address";
		else
			signupPerson(this.email);
	}
}
```
	
Chronologically, this is what happens:

1. User points their browser at signup.jsp
2. signup.jsp is processed.  There is no model, so form renders normally
3. User fills out form and hits submit
4. Browser goes to submit_signup.jsp, passing the http request parameter `email`
5. submit_signup.jsp instantiates and executes the action class `Signup`
6. The `Signup` action validates the data and performs the signup business logic. The resulting model will be the action object itself.
7. If there were no errors, submit_signup.jsp redirects to thanks.jsp
8. If there were any errors in the model, submit_signup.jsp forwards back to signup.jsp. The model contains both the error message and the old input for pre-loading the form.

## Conclusion
This is just a broad overview of what is possible with Tagonist.  In the
sample applications, you will see examples of:

* Passing parameters to action classes from JSPs
* Pre-populating a form with data
* Using form bean classes declared separately from the action class
* Issuing redirects and forwards from action classes by throwing exceptions

## Revisions
* 1.0 - Initial release
* 1.1 - Replaced "recycle" attribute with "force", which has inverted meaning.  AbstractAction now populates bean properties with action params.  Added Propertizer and example.
* 1.2 - Improved error management. Use JDK5 generics features.
* 1.3 - Removed propertize (use Project Lombok instead), uses slf4j instead of commons-logging.
* 1.4 - Mavenized, moved to github
* 1.4.1 - Built for Java6 (1.4 had moved to Java7 perhaps prematurely)
