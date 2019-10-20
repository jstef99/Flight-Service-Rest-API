<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML>

<html>
<body>
<%--@elvariable id="airport" type=""--%>
<form:form action="/admin/airports/modify" method="post" modelAttribute="airport">
    <form:input type="hidden" path="id"/>
    Airport name:<form:input path="airportName"/>
    Country:<form:input path="country"/>

    <button type="submit">Update</button>
</form:form>
</body>
</html>