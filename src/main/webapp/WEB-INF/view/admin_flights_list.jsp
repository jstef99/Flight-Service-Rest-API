<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<body>

<table border="1">
    <tr>
        <th>Id</th>
        <th>Airport name</th>
        <th>Destination airport name</th>
        <th>Current passengers</th>
        <th>Max passengers</th>
        <th>Normal ticket price</th>
        <th>Discounted ticket price</th>
        <th>Action 1</th>
        <th>Action 2</th>
    </tr>
    <c:forEach items="${flights}" var="flight">
        <tr>
            <td><c:out value="${flight.id}"/></td>
            <td><c:out value="${flight.departurePlace.airportName}"/></td>
            <td><c:out value="${flight.destination.airportName}"/></td>
            <td><c:out value="${flight.currPassengers}"/></td>
            <td><c:out value="${flight.maxPassengers}"/></td>
            <td><c:out value="${flight.nPrice}"/></td>
            <td><c:out value="${flight.dPrice}"/></td>
            <td><a href="/admin/flights/modify/${flight.id}"/>Modify</td>
            <td><a href="/admin/flights/delete/${flight.id}"/>Delete</td>
        </tr>
    </c:forEach>
</table>
</body>

</html>