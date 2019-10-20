<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<body>

<table border="1">
    <tr>
        <th>Id</th>
        <th>First name</th>
        <th>Last name</th>
        <th>Login</th>
        <th>Email</th
        <th>Action 1</th>
        <th>Action 2</th>
        <th>Action 3</th>
    </tr>
    <c:forEach items="${users}" var="user">
        <tr>
            <td><c:out value="${user.id}"/></td>
            <td><c:out value="${user.firstName}"/></td>
            <td><c:out value="${user.lastName}"/></td>
            <td><c:out value="${user.login}"/></td>
            <td><c:out value="${user.email}"/></td>
            <td><a href="/admin/users/generate_api/${user.id}"/>Generate API key</td>
            <td><a href="/admin/users/modify/${user.id}"/>Modify</td>
            <td><a href="/admin/users/delete/${user.id}"/>Delete</td>
        </tr>
    </c:forEach>
</table>
</body>

</html>