<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<body>
<%--@elvariable id="flight" type=""--%>
<form:form action="/browse" method="post" modelAttribute="flight">
    <form:input type="text" path="departurePlace" placeholder="From"/>
    <br>
    <form:input type="text" path="destination" placeholder="To"/>
    <br>
    <form:input type="text" path="departureTime" placeholder="Date"/>
    <button type="submit">Search for flight</button>
</form:form>
</body>
</html>