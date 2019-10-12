<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<body>

<table border="1">
    <tr>
        <th>Departure place</th>
        <th>Destination</th>
        <th>Deaparture time</th>
        <th>Arrival time</th>
        <th>Current passengers</th>
        <th>Additional</th>
    </tr>
    <c:forEach items="${results}" var="result">
        <tr>
            <td><c:out value="${result.departurePlace.airportName}"/></td>
            <td><c:out value="${result.destination.airportName}"/></td>
            <td><c:out value="${result.departureTime}"/></td>
            <td><c:out value="${result.arrivalTime}"/></td>
            <td><c:out value="${result.currPassengers} / ${result.maxPassengers}"/></td>
            <td><a href="/flight_info/${result.id}">More information</a></td>
        </tr>
    </c:forEach>
</table>
</body>


</html>