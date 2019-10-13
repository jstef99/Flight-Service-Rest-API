<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<body>

<a href="/">Home</a>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Country</th>
        <th>Airport name</th>
    </tr>
    <c:forEach items="${airports}" var="airport">
        <tr>
            <td><c:out value="${airport.id}"/></td>
            <td><c:out value="${airport.country}"/></td>
            <td><c:out value="${airport.airportName}"/></td>
        </tr>
    </c:forEach>
</table>
</body>


</html>