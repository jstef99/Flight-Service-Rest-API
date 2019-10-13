<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<body>
<%--@elvariable id="message" type=""--%>
<form:form action="/" modelAttribute="message" method="get">
    <p><c:out value="${message}"/></p>
    <button type="submit">Back home</button>
</form:form>
</body>
</html>