<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<%--@elvariable id="user" type=""--%>
<head>
    <style>
        .error {color:red}
    </style>
</head>
<body>
<c:if test="${registrationError != null}">

    <div class="alert alert-danger col-xs-offset-1 col-xs-10">
            ${registrationError}
    </div>

</c:if>
<%--@elvariable id="user" type=""--%>
<form:form action="/user/change_email" method="post" modelAttribute="user">
    <form:input type="hidden" path="id"/>
    <form:input type="hidden" path="firstName"/>
    <form:input type="hidden" path="lastName"/>
    <form:input type="hidden" path="login"/>
    <form:input type="hidden" path="password"/>
    New email:<form:input value="new email" path="email"/>
    <form:input type="hidden" path="roles"/>
    <form:input type="hidden" path="reservations"/>
    <button type="submit">Update</button>
</form:form>
</body>
</html>