<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<body>
<form action="admin/airports/add" method="get">
    <button type="submit" value="New airport"/>
</form>
<table border="1">
    <tr>
        <th>Id</th>
        <th>Country</th>
        <th>Airport Name</th>
        <th>Action 1</th>
        <th>Action 2</th>
    </tr>
    <c:forEach items="${airports}" var="airport">
        <tr>
            <td><c:out value="${airport.id}"/></td>
            <td><c:out value="${airport.country}"/></td>
            <td><c:out value="${airport.airportName}"/></td>
            <td><a  href="/admin/airports/modify/${airport.id}"/>Modify</td>
            <td><a  href="/admin/airports/delete/${airport.id}"/>Delete</td>
        </tr>
    </c:forEach>
</table>
</body>

</html>