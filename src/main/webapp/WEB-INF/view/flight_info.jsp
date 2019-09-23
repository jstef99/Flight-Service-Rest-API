<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<body>
    <%--@elvariable id="flight" type=""--%>
    <form:form modelAttribute="flight">
        Flight ID: <c:out value="${flight.id}"/>
        <br>
        From: <c:out value="${flight.departurePlace}"/>
        <br>
        To: <c:out value="${flight.destination}"/>
        <br>
        Departure time: <c:out value="${flight.departureTime}"/>
        <br>
        Arrival time: <c:out value="${flight.arrivalTime}"/>
        <br>
        Duration: <c:out value="${flight.duration}"/>
        <br>
        Current reserved: <c:out value="${flight.currPassengers}"/>
        <br>
        Max reservations: <c:out value="${flight.maxPassengers}"/>
    </form:form>
</body>
</html>