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
        <th>Adults</th>
        <th>Children</th>
        <th>Total cost</th>
        <th>Status</th>
        <th>Action</th>
    </tr>
    <c:forEach items="${reservations}" var="reservation">
        <tr>
            <td><c:out value="${reservation.id}"/></td>
            <td><c:out value="${reservation.flight.departurePlace.airportName}"/></td>
            <td><c:out value="${reservation.flight.destination.airportName}"/></td>
            <td><c:out value="${reservation.flight.departureTime}"/></td>
            <td><c:out value="${reservation.flight.arrivalTime}"/></td>
            <td><c:out value="${reservation.adults}"/></td>
            <td><c:out value="${reservation.children}"/></td>
            <td><c:out value="${reservation.total}"/></td>
            <td><c:out value="${reservation.status}"/></td>
            <td><a href="/">Finalize</a></td>
        </tr>
    </c:forEach>
</table>
</body>


</html>