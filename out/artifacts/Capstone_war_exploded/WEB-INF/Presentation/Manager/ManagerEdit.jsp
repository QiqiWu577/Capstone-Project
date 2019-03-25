<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
<<<<<<< HEAD
    <title>Employee Management</title>
    <link href="employeemgmt.css" rel="stylesheet" type="text/css">
=======
    <title>Home</title>
    <link href="./images/home.css" rel="stylesheet" type="text/css">
>>>>>>> bc0ee6d35042de5e48b2937a844e9c87ddc20214
</head>

<body>
<div class="sidebar">
    <div class="logo">
        <img src="/res/2030.png" alt="2030" height="100" width="100">
    </div>
<<<<<<< HEAD
    <a href="home.html">Home</a>
    <a href="employeemgmt.html">Employee Management</a>
    <a href="WEB-INF/Presentation/Manager/ManagerSetting.jsp">Settings</a>
=======
    <a href="#">Home</a>
    <a href="#">Employees</a>
>>>>>>> bc0ee6d35042de5e48b2937a844e9c87ddc20214
</div>

<div class="main">
    <div class="topbar">
        <img src="res/61073.svg" height="35" width="35">
    </div>

    <div class="row">
        <div class="column" style="background-color:#aaa;">
            <input type="text" value="Search"><br/>
            <div class="table">

                <table border='1' style='border-collapse:collapse'>
                    <tr>
                        <th>ID</th>
                        <th>Last</th>
                        <th>First</th>
                        <th>Position</th>
                    </tr>
                    <c:forEach var="employee" items="${requestScope.employees}">
                        <tr>
                            <form action="EmployeeServices" method="GET">

                                <td>${employee.getEmpID()}</td>
                                <td>${employee.getLastName()}</td>
                                <td>${employee.getFirstname()}</td>
                                <td>${employee.getPosition()}</td>
                            </form>
                        </tr>
                    </c:forEach>
                </table>

            </div>
<<<<<<< HEAD
            <%--<table>--%>
                <%--<tr class="positions-top">--%>
                    <%--<th>ID</th>--%>
                    <%--<th>Name</th>--%>
                    <%--<th>Position</th>--%>
                <%--</tr>--%>
                <%--<tr class="alt">--%>
                    <%--<td>001</td>--%>
                    <%--<td>Dave Johnson</td>--%>
                    <%--<td>Bartender</td>--%>
                <%--</tr>--%>
                <%--<tr class="alt">--%>
                    <%--<td>002</td>--%>
                    <%--<td>Mark Smith</td>--%>
                    <%--<td>Server</td>--%>
                <%--</tr>--%>
                <%--<tr class="alt">--%>
                    <%--<td>003</td>--%>
                    <%--<td>Ronald McDonald</td>--%>
                    <%--<td>Kitchen</td>--%>
                <%--</tr>--%>
                <%--<tr class="alt">--%>
                    <%--<td>004</td>--%>
                    <%--<td>John Smith</td>--%>
                    <%--<td>Kitchen</td>--%>
                <%--</tr>--%>
                <%--<tr class="alt">--%>
                    <%--<td>005</td>--%>
                    <%--<td>Mark Wahlberg</td>--%>
                    <%--<td>Bartender</td>--%>
                <%--</tr>--%>
                <%--<tr class="alt">--%>
                    <%--<td>006</td>--%>
                    <%--<td>Michael Scott</td>--%>
                    <%--<td>Kitchen</td>--%>
                <%--</tr>--%>

            <%--</table>--%>
=======

>>>>>>> bc0ee6d35042de5e48b2937a844e9c87ddc20214
        </div>
        <div class="column" style="background-color:#bbb;">
            <div class="row">
                <div class="column" style="background-color:#bbb;">
                    <label for="id">ID</label><br/>
                    <input type="text" id="id"><br/>
                    <label for="fname">First Name</label>
                    <input type="text" placeholder="John" id="fname"><br/>
                    <label for="lname">Last Name</label>
                    <input type="text" placeholder="Doe" id="lname"><br/>
                    <label for="position">Position</label><br/>
                    <select id="position">
                        <option value="Bartender">Bartender</option>
                        <option value="Server">Server</option>
                        <option value="Kitchen">Kitchen</option>
                    </select>
                </div>
                <div class="column" style="background-color:#bbb;">
                    <label for="email">Email Address</label><br/>
                    <input type="text" id="email"><br/>
                    <label for="address">Address</label><br/>
                    <input type="text" id="address"><br/>
                    <label for="phone">Phone Number</label><br/>
                    <input type="text" id="phone"><br/>
                </div>


            </div>
            Comments:
            <textarea rows="4" cols="30">
                 hi
            </textarea>

            <input type="button" value="Save" name="save" class="btn">
            <input type="button" value="Clear" name="clear" class="btn">
            <input type="button" value="Delete" name="delete" class="btn">
        </div>
    </div>
</div>

</body>

</html>
