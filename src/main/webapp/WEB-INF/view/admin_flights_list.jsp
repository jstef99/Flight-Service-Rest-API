<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<body>

<table border="1">
    <tr>
        <th>Id</th>
        <th>Stub</th>
    </tr>
    <c:forEach items="${flights}" var="flight">
        <tr>
            <td><c:out value="${reservation.id}"/></td>
            <td>Stub</td>
        </tr>
    </c:forEach>
</table>
</body>

</html>