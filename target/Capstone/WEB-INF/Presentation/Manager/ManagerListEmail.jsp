<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <title>Home</title>
    <link href="css/home.css" rel="stylesheet" type="text/css">
</head>

<body>
<div class="sidebar">
    <div class="logo">
        <img src="images/2030.png" alt="2030" height="100" width="100">
    </div>
    <a href="#">Home</a>
    <a href="#">Employees</a>
</div>

<div class="main">
    <div class="topbar">
        <img src="res/61073.svg" height="35" width="35">
    </div>

    <div class="row">
        <div class="column" style="background-color:#aaa;">
            <input type="text" placeholder="Search"><br/>
            <div class="table">

                <table border='1' style='border-collapse:collapse'>
                    <tr>
                        <th>Last</th>
                        <th>First</th>
                        <th>Email</th>
                        <th></th>
                    </tr>
                    <c:forEach var="employee" items="${requestScope.employees}">
                        <tr>
                            <form action="EmployeeServices" method="GET">

                                <td>${employee.getLastName()}</td>
                                <td>${employee.getFirstname()}</td>
                                <td>${employee.getEmail()}</td>
                                <td><input type="submit" value="Message"class='btn'></td>
                                <%--<a href="mailto:someone@example.com?Subject=Hello%20again" target="_top">Send Mail</a>--%>
                            </form>
                        </tr>
                    </c:forEach>
                </table>

            </div>

        </div>
    </div>
</div>

</body>

</html>
