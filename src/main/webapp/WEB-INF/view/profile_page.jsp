<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<body>
<%--@elvariable id="user" type=""--%>
<form:form action="/logout" modelAttribute="user" method="post">
    <p>Logged as: <c:out value="${user.firstName} ${user.lastName}"/></p>
    <p>Email: ${user.email}</p>
    <button type="submit" >Logout</button>
</form:form>
<form:form action="/user/change_password"  method="get">
    <button type="submit">Change my password</button>
</form:form>
<form:form action="/user/change_email"  method="get">
    <button type="submit">Change my email</button>
</form:form>
<form action="/user/registrations" method="get">
    <button type="submit">My registrations</button>
</form>
</body>
</html>