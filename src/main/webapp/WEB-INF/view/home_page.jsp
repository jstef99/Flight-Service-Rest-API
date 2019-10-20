<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE HTML>
<html>
<body>
<a href="/profile">My profile</a>
<a href="/browse/flights">Browse flights</a>
<a href="/browse/airports">Browse airports</a>
<security:authorize access="hasRole('ADMIN')">
    <br>
    <div>Admin panel</div>
    <div>
        <a href="/admin/users">Users</a>
    </div>
    <div>
        <a href="/admin/airports">Airports</a>
    </div>
    <div>
        <a href="/admin/flights">Flights</a>
    </div>
    <div>
        <a href="/admin/registrations">Registrations</a>
    </div>
</security:authorize>

</body>
</html>