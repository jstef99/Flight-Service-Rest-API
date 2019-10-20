<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML>

<html>
<body>
<%--@elvariable id="flight" type=""--%>
<form:form action="/admin/flights/modify" method="post" modelAttribute="flight">
    <form:input type="hidden" path="id"/>
    Current passengers:<form:input path="currPassengers"/>
    Max passengers:<form:input path="maxPassengers"/>
    Normal ticket price:<form:input path="nPrice"/>
    Discounted ticket price<form:input path="dPrice"/>
    <button type="submit">Update</button>
</form:form>
</body>
</html>