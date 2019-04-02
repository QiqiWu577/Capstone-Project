<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="sidebar">
    <div class="logo">
        <img src="images/2030.png" alt="2030" height="100" width="100">
    </div>
    <a href="home.html">Home</a>
    <a href="employeemgmt.html">User Management</a>
    <a href="settings.html">Settings</a>
</div>

<div class="main">
    <div class="topbar">
        <a href="notifications.html">
            <img src="res/61073.svg" height="30" width="30">
        </a>
    </div>
    <div class="title">
        Employee Management
    </div>
    <div class="row">
        <div class="columnleft" style="background-color:#d8d8d8;">
            <br>
            <input type="text" placeholder="Search"><br/>
            <div class="table">
            </div>
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
        <div class="column" style="background-color:#d8d8d8;">

            <div class="row">
                <div class="column" style="background-color:#d8d8d8;">
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
                <div class="column" style="background-color:#d8d8d8;">
                    <label for="email">Email Address</label><br/>
                    <input type="text" id="email" placeholder="email@example.com"><br/>
                    <label for="address">Address</label><br/>
                    <input type="text" id="address" placeholder="123 Main Street"><br/>
                    <label for="phone">Phone Number</label><br/>
                    <input type="text" id="phone" placeholder="### - ### - ####"><br/>
                </div>
            </div>

            <div class="bottom">
                <label for="comment">Comments</label>
                <br>
                <textarea name="comment" rows="4" cols="70" form="usrform" placeholder="Example Text"></textarea>
                <div class="btngrp">
                    <input type="button" value="Save" class="btns">
                    <input type="button" value="Clear" class="btns">
                    <input type="button" value="Delete" class="btns">
                </div>

                <div class="row">
                    <table>


                        <tr class="schedule">
                            <td class="day">Sunday</td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                        </tr>

                        <tr class="schedule">
                            <td class="day">Monday</td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                        </tr>

                        <tr class="schedule">
                            <td class="day">Tuesday</td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                        </tr>

                        <tr class="schedule">
                            <td class="day">Wednesday</td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                        </tr>

                        <tr class="schedule">
                            <td class="day">Thursday</td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                        </tr>

                        <tr class="schedule">
                            <td class="day">Friday</td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                        </tr>

                        <tr class="schedule">
                            <td class="day">Saturday</td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                        </tr>

                    </table>



                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
