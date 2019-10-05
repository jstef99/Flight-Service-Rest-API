<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<body>

<table border="1">
    <tr>
        <th>Registration id</th>
        <th>From</th>
        <th>To</th>
        <th>Deaparture time</th>
        <th>Arrival time</th>
        <th>Current passengers</th>
        <th>Status</th>
    </tr>
    <c:forEach items="${reservations}" var="reservation">
        <tr>
            <td><c:out value="${reservation.id}"/></td>
            <td><c:out value="${reservation.departurePlace}"/></td>
            <td><c:out value="${reservation.destination}"/></td>
            <td><c:out value="${reservation.departureTime}"/></td>
            <td><c:out value="${reservation.arrivalTime}"/></td>
            <td><c:out value="${reservation.currPassengers} / ${result.maxPassengers}"/></td>
            <td><c:out value="${reservation.status}"/></td>
        </tr>
    </c:forEach>
</table>
</body>


</html>