<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML>

<html>
<body>
<%--@elvariable id="user" type=""--%>
<form:form action="/admin/users/modify" method="post" modelAttribute="user">
    <form:input type="hidden" path="id"/>
    Login:<form:input path="login"/>
    Email:<form:input path="email"/>
    First name:<form:input path="firstName"/>
    Last name:<form:input path="lastName"/>
    <button type="submit">Update</button>
</form:form>
</body>
</html>