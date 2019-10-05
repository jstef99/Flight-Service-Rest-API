<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML>
<html>
<%--@elvariable id="user" type=""--%>
<head>
    <style>
        .error {color:red}
    </style>
</head>
<body>

<form:form action="/user/change_password" method="post" modelAttribute="user">
    <form:input type="hidden" path="id"/>
    <form:input type="hidden" path="firstName"/>
    <form:input type="hidden" path="lastName"/>
    <form:input type="hidden" path="login"/>
    <form:errors path="password" cssClass="error" />
    <br>
    New password<form:input type="text"   value="New password"  path="password"/>
    <form:input type="hidden" path="email"/>
    <form:input type="hidden" path="roles"/>
    <form:input type="hidden" path="reservations"/>
    <button type="submit">Change</button>
</form:form>
</body>
</html>