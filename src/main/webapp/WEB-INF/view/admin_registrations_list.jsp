<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<body>

<table border="1">
    <tr>
        <th>Registration id</th>
        <th>Owner login</th>
        <th>Flight id</th>
        <th>Children</th>
        <th>Adults</th>
        <th>Total</th>
        <th>Status</th>
        <th>Action1</th>
        <th>Action2</th>
    </tr>
    <c:forEach items="${registrations}" var="registration">
        <tr>
            <td><c:out value="${registration.id}"/></td>
            <td><c:out value="${registration.owner.login}"/></td>
            <td><c:out value="${registration.flight.id}"/></td>
            <td><c:out value="${registration.children}"/></td>
            <td><c:out value="${registration.adults}"/></td>
            <td><c:out value="${registration.total}"/></td>
            <td><c:out value="${registration.status}"/></td>
            <td><a href="/admin/registrations/modify/${registration.id}">Modify</a></td>
            <td><a href="/admin/registrations/delete/${registration.id}">Delete</a></td>
        </tr>
    </c:forEach>
</table>
</body>

</html>