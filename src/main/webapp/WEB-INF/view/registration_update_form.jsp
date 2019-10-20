<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML>

<html>
<body>
<%--@elvariable id="registration" type=""--%>
<form:form action="/admin/registrations/modify" method="post" modelAttribute="registration">
    <form:input type="hidden" path="id"/>
    Adults:<form:input path="adults"/>
    Children:<form:input path="children"/>
    Total:<form:input path="total"/>

    <button type="submit">Update</button>
</form:form>
</body>
</html>