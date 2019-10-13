<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<body>
<%--@elvariable id="transaction" type=""--%>
<form:form action="/flight/confirm_registration" method="post" modelAttribute="transaction">

    <form:input type="text" path="adults" placeholder="adults"/>
    <br>
    <form:input type="text" path="children" placeholder="children"/>
    <br>
    <form:hidden path="flightId"/>
    <form:hidden path="normalTicketPrice"/>
    <form:hidden path="discountTicketPrice"/>
    <button type="submit">Submit registration</button>
</form:form>
</body>
</html>