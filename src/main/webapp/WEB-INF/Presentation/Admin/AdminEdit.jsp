<!DOCTYPE html>
<html>
<<<<<<< HEAD
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
    <title>Account Management</title>
    <link href="css/employeemgmt.css" rel="stylesheet" type="text/css">

    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round|Open+Sans">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">

    <script src='js/calendarCore/jquery.min.js'></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

    <style>
        body {
            color: #404E67;
            background: #F5F7FA;
            font-family: 'Open Sans', sans-serif;
        }

        .table-wrapper {
            width: 900px;
            margin-top: 30px;
            margin-left: 20%;
            background: #fff;
            padding: 10px;
            box-shadow: 0 1px 1px rgba(0,0,0,.05);
        }

        .table-title {
            padding-bottom: 10px;
            margin: 0 0 10px;
        }

        .table-title h2 {
            margin: 6px 0 0;
            font-size: 22px;
        }
        .table-title .add-new {
            float: right;
            height: 30px;
            font-weight: bold;
            font-size: 12px;
            text-shadow: none;
            min-width: 100px;
            border-radius: 50px;
            line-height: 13px;
        }

        .table-title .add-new i {
            margin-right: 4px;
        }

        table.table {
            table-layout: fixed;
        }

        table.table td i {
            font-size: 15px;
        }

        table.table td input {
            cursor: pointer;
            display: inline-block;
            margin: 0 5px;
            min-width: 24px;
        }

        /* ------- scroll bar style block ------*/
        .table-scroll {
            height:510px;
            overflow:auto;
        }

        /* width */
        .table-scroll::-webkit-scrollbar {
            width: 10px;
        }

        /* Track */
        .table-scroll::-webkit-scrollbar-track {
            background: #f1f1f1;
        }

        /* Handle */
        .table-scroll::-webkit-scrollbar-thumb {
            background: #888;
        }

        /* Handle on hover */
        .table-scroll::-webkit-scrollbar-thumb:hover {
            background: #555;
        }
        /* ------- the end of scroll bar style block ------*/

        /*----- status style -----*/
        .status {
            font-size: 30px;
            margin: 2px 2px 0 0;
            display: inline-block;
            vertical-align: middle;
            line-height: 10px;
        }

        .text-success {
            color: #10c469;
        }

        .text-warning {
            color: #FFC107;
        }
        /*----- the end of status style -----*/

        input[type="submit"] {
            font-family: "Font Awesome 5 Free";
            font-size: 1.3333333333333333em;
            font-weight: 900;
            border: none;
            background: transparent;
        }

        input{
            border: none;
            background: transparent;
        }

        .save{
            color: #27C46B;
        }

        .delete{
            color: #E34724;
        }

    </style>

</head>
<body>
=======

<head>
    <title>Employee Management</title>
    <link href="${pageContext.request.contextPath}/css/employeemgmt.css" rel="stylesheet" type="text/css">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
</head>

<body style="background-color:#d8d8d8;">
>>>>>>> 2f8f94e95d23036d2fa405e4320af1e630ae3af2
<!--Left side Menu -->
<div data-collapse="tiny" data-animation="over-left" data-duration="400" class="navbar-3 w-nav">
    <div class="container-3 w-container">
        <a href="#" class="brand w-nav-brand">
            <div class="div-block-4"><img src="images/buble-tea.png" width="111" alt="" class="w-hidden-tiny"></div>
        </a>
        <nav role="navigation" class="w-nav-menu">
<<<<<<< HEAD
            <a href="#" class="nav-links w-nav-link">Home</a>
            <a href="#" class="nav-links w-nav-link">Settings</a>
            <a href="Validate?logout=logout" class="nav-links w-nav-link">Logout</a>
=======
            <a href="<%=application.getContextPath() %>/ManageScheduleViews" class="nav-links w-nav-link">Home</a>
            <a href="<%=application.getContextPath() %>/ManageEmployees" class="nav-links w-nav-link">Employee Management</a>
            <a href="<%=application.getContextPath() %>/ManagerServices?page=notifications" class="nav-links w-nav-link">Notifications</a>
            <a href="<%=application.getContextPath() %>/ManagerServices" class="nav-links w-nav-link">Settings</a>
            <a href="<%=application.getContextPath() %>/Validate?logout=logout" class="nav-links w-nav-link">Logout</a>
>>>>>>> 2f8f94e95d23036d2fa405e4320af1e630ae3af2
        </nav>
    </div>
</div>

<<<<<<< HEAD
<div class="container">
    <div class="table-wrapper">
        <div class="table-title">
            <div class="row">
                <div class="col-sm-8"><h2>User <b>Details</b></h2></div>
                <div class="col-sm-4">
                    <button type="button" class="btn btn-info add-new" data-toggle="modal" data-target="#newUser"><i class="fas fa-user-plus"></i></i> Add New</button>
                </div>
            </div>
        </div>

        <div class="table-scroll">

            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>#</th>
                    <th>Name</th>
                    <th>Status</th>
                    <th>Type</th>
                    <th>Action</th>
                </tr>
                </thead>

                <tbody>

                <c:set var="count" value="1" scope="page" />
                <c:forEach var="user" items="${userList}">

                    <form action="AdminServices" method="post">
                        <tr>
                            <td>${count}</td>
                            <td><input type="text" name="name" value="${user.fname} ${user.lname}"></td>

                            <td>
                                <c:choose>
                                    <c:when test="${user.active == true}">
                                        <input type="text" name="status" value="Active">
                                    </c:when>
                                    <c:otherwise>
                                        <input type="text" name="status" value="Inactive">
                                    </c:otherwise>
                                </c:choose>
                            </td>

                            <c:choose>
                                <c:when test="${user.type == 'S'.charAt(0)}">
                                    <td><input type="text" name="type" value="Sever"></td>
                                </c:when>
                                <c:when test="${user.type == 'B'.charAt(0)}">
                                    <td><input type="text" name="type" value="Bartender"></td>
                                </c:when>
                                <c:when test="${user.type == 'K'.charAt(0)}">
                                    <td><input type="text" name="type" value="Kitchen"></td>
                                </c:when>
                                <c:when test="${user.type == 'M'.charAt(0)}">
                                    <td><input type="text" name="type" value="Manager"></td>
                                </c:when>
                                <c:otherwise>
                                    <td><input type="text" name="type" value="Administration"></td>
                                </c:otherwise>
                            </c:choose>

                            <td>
                                <input type="submit" class="save" name="save" title="Save" data-toggle="tooltip" value="&#xf0c7">
                                <input type="submit" class="reset" name="reset" title="Reset Password" data-toggle="tooltip" value="&#xf084">
                                <input type="submit" class="delete" title="Delete" name="delete" data-toggle="tooltip" value="&#xf2ed">
                            </td>
                        </tr>
                        <input type="hidden" name="id" value="${user.empid}">
                    </form>
                    <c:set var="count" value="${count + 1}" scope="page"/>
                </c:forEach>

                </tbody>
            </table>

        </div>
    </div>
</div>

<!-- dialog -->
<div class="modal fade" id="newUser" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h3 class="modal-title" id="exampleModalLabel">New User</h3>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
=======
<div class="main">
    <div class="title">
        Management
    </div>
    <div class="row">
        <div class="columnleft" style="background-color:#d8d8d8;">
            <br>
            <input type="text" placeholder="Search"><br/>
            <div class="table">
            </div>
            <table class="emp">
                <tr class="positions-top">
                    <th class="avail">ID</th>
                    <th class="avail">Name</th>
                    <th class="avail">Position</th>
                </tr>
                <c:forEach items="${requestScope.employeeList}" var="emp" >
                    <tr class="alt" onclick="populate('${emp.getEmpid()}','${emp.getFname()}','${emp.getLname()}',
                            '${emp.getAddress()}','${emp.getPhoneno()}','${emp.getEmail()}','${emp.getType()}','${emp.getNotes()}','${emp.getEmployeeConstraints().getConstraints()}');selectEmp(this)">
                        <td>${emp.getEmpid()}</td>
                        <td>${emp.getFname()} ${emp.getLname()}</td>
                        <td>${emp.getType()}</td>
                    </tr>

                </c:forEach>
            </table>
        </div>
        <form action="${pageContext.request.contextPath}/ManageEmployees" method="POST" id="myForm">
            <input type="hidden" id="constraints" onsubmit="generateString()" name="constraints">
            <input type="hidden" name="page" value="1">

            <div class="column" style="background-color:#d8d8d8;">

                <div class="row">
                    <div class="column" style="background-color:#d8d8d8;">
                        <label for="id">ID</label><br/>
                        <input type="text" id="id" name="id" value="0" readonly><br/>
                        <label for="fname">First Name</label>
                        <input type="text" placeholder="John" id="fname" name="fname"><br/>
                        <label for="lname">Last Name</label>
                        <input type="text" placeholder="Doe" id="lname" name="lname"><br/>
                        <label for="position">Position</label><br/>
                        <select id="position" name="position">
                            <option value="Bartender">Bartender</option>
                            <option value="Server">Server</option>
                            <option value="Kitchen">Kitchen</option>
                            <option value="Manager">Manager</option>
                            <option value="Admin">Admin</option>
                        </select>
                    </div>
                    <div class="column" style="background-color:#d8d8d8;">
                        <label for="email">Email Address</label><br/>
                        <input type="text" id="email" placeholder="email@example.com" name="email"><br/>
                        <label for="address">Address</label><br/>
                        <input type="text" id="address" placeholder="123 Main Street" name="address"><br/>
                        <label for="phone">Phone Number</label><br/>
                        <input type="text" id="phone" placeholder="### - ### - ####" name="phone"><br/>
                    </div>
                </div>

                <div class="bottom">
                    <label>Comments</label>
                    <br>
                    <textarea name="comment" id="comment" rows="4" cols="70" placeholder="Example Text"></textarea>
                    <div class="btngrp">
                        <input type="submit" name="action" value="Save" onclick="generateString()" class="btns">
                        <input type="button" value="Clear" class="btns" onclick="clearFields()">
                        <input type="submit" name="action" value="Delete" class="btns" onclick="generateString()">
                    </div>
                    ${requestScope.message}


                    <div class="tab">
                        <button type="button" class="tablinks" onclick="openCity(event, 'Availability')">Availability</button>
                        <button type="button" class="tablinks" onclick="openCity(event, 'Preferences')">Preferences</button>
                    </div>

                    <!-- Tab content -->
                    <div id="Availability" class="tabcontent">
                        <div class="goodName">Availability</div><br>
                        <table>
                            <tr><th></th><th>12am-5am</th><th>6am-11am</th><th>12pm-5pm</th><th>6pm-11pm</th></tr>
                            <tr>
                                <th>
                                    <div class="heading">Monday</div>
                                </th>
                                <td style="border-right: 1px solid black;">
                                    <c:forEach begin="0" end="23" varStatus="loop">

                                    <c:if test="${loop.index == 6 || loop.index == 12 || loop.index == 18}">
                                </td><td style="border-right: 1px solid black;">
                                </c:if>
                                <label class="contain">
                                    <input id="box_A_0_${loop.index}" type="checkbox" onmouseover="check(this)" onmousedown="check(this)">
                                    <span class="checkmark"></span>
                                </label>
                                </c:forEach>
                            </td>
                            </tr>


                            <tr>
                                <th>
                                    <div class="heading">Tuesday</div>
                                </th>
                                <td style="border-right: 1px solid black;">
                                    <c:forEach begin="0" end="23" varStatus="loop">
                                    <c:if test="${loop.index == 6 || loop.index == 12 || loop.index == 18}">
                                </td><td style="border-right: 1px solid black;">
                                </c:if>
                                <label class="contain">
                                    <input id="box_A_1_${loop.index}" type="checkbox" onmouseover="check(this)" onmousedown="check(this)">
                                    <span class="checkmark"></span>
                                </label>
                                </c:forEach>
                            </td>
                            </tr>

                            <tr>
                                <th>
                                    <div class="heading">Wednesday</div>
                                </th>
                                <td style="border-right: 1px solid black;">
                                    <c:forEach begin="0" end="23" varStatus="loop">
                                    <c:if test="${loop.index == 6 || loop.index == 12 || loop.index == 18}">
                                </td><td style="border-right: 1px solid black;">
                                </c:if>
                                <label class="contain">
                                    <input id="box_A_2_${loop.index}" type="checkbox" onmouseover="check(this)" onmousedown="check(this)">
                                    <span class="checkmark"></span>
                                </label>
                                </c:forEach>
                            </td>
                            </tr>
                            <tr>
                                <th>
                                    <div class="heading">Thursday</div>
                                </th>
                                <td style="border-right: 1px solid black;">
                                    <c:forEach begin="0" end="23" varStatus="loop">
                                    <c:if test="${loop.index == 6 || loop.index == 12 || loop.index == 18}">
                                </td><td style="border-right: 1px solid black;">
                                </c:if>
                                <label class="contain">
                                    <input id="box_A_3_${loop.index}" type="checkbox" onmouseover="check(this)" onmousedown="check(this)">
                                    <span class="checkmark"></span>
                                </label>
                                </c:forEach>
                            </td>
                            </tr>
                            <tr>
                                <th>
                                    <div class="heading">Friday</div>
                                </th>
                                <td style="border-right: 1px solid black;">
                                    <c:forEach begin="0" end="23" varStatus="loop">
                                    <c:if test="${loop.index == 6 || loop.index == 12 || loop.index == 18}">
                                </td><td style="border-right: 1px solid black;">
                                </c:if>
                                <label class="contain">
                                    <input id="box_A_4_${loop.index}" type="checkbox" onmouseover="check(this)" onmousedown="check(this)">
                                    <span class="checkmark"></span>
                                </label>
                                </c:forEach>
                            </td>
                            </tr>
                            <tr>
                                <th>
                                    <div class="heading">Saturday</div>
                                </th>
                                <td style="border-right: 1px solid black;">
                                    <c:forEach begin="0" end="23" varStatus="loop">
                                    <c:if test="${loop.index == 6 || loop.index == 12 || loop.index == 18}">
                                </td><td style="border-right: 1px solid black;">
                                </c:if>
                                <label class="contain">
                                    <input id="box_A_5_${loop.index}" type="checkbox" onmouseover="check(this)" onmousedown="check(this)">
                                    <span class="checkmark"></span>
                                </label>
                                </c:forEach>
                            </td>
                            </tr>
                            <tr>
                                <th>
                                    <div class="heading">Sunday</div>
                                </th>
                                <td style="border-right: 1px solid black;">
                                    <c:forEach begin="0" end="23" varStatus="loop">
                                    <c:if test="${loop.index == 6 || loop.index == 12 || loop.index == 18}">
                                </td><td style="border-right: 1px solid black;">
                                </c:if>
                                <label class="contain">
                                    <input id="box_A_6_${loop.index}" type="checkbox" onmouseover="check(this)" onmousedown="check(this)">
                                    <span class="checkmark"></span>
                                </label>
                                </c:forEach>
                            </td>
                            </tr>
                        </table>

                    </div>





                    <div id="Preferences" class="tabcontent">
                        <div class="goodName">Preferences</div> <br>
                        <table>
                            <tr><th></th><th>12am-5am</th><th>6am-11am</th><th>12pm-5pm</th><th>6pm-11pm</th></tr>
                            <tr>
                                <th>
                                    <div class="heading">Monday</div>
                                </th>
                                <td style="border-right: 1px solid black;">
                                    <c:forEach begin="0" end="23" varStatus="loop">

                                    <c:if test="${loop.index == 6 || loop.index == 12 || loop.index == 18}">
                                </td><td style="border-right: 1px solid black;">
                                </c:if>
                                <label class="contain">
                                    <input id="box_P_0_${loop.index}" type="checkbox" onmouseover="check(this)" onmousedown="check(this)">
                                    <span class="checkmark"></span>
                                </label>
                                </c:forEach>
                            </td>
                            </tr>


                            <tr>
                                <th>
                                    <div class="heading">Tuesday</div>
                                </th>
                                <td style="border-right: 1px solid black;">
                                    <c:forEach begin="0" end="23" varStatus="loop">
                                    <c:if test="${loop.index == 6 || loop.index == 12 || loop.index == 18}">
                                </td><td style="border-right: 1px solid black;">
                                </c:if>
                                <label class="contain">
                                    <input id="box_P_1_${loop.index}" type="checkbox" onmouseover="check(this)" onmousedown="check(this)">
                                    <span class="checkmark"></span>
                                </label>
                                </c:forEach>
                            </td>
                            </tr>

                            <tr>
                                <th>
                                    <div class="heading">Wednesday</div>
                                </th>
                                <td style="border-right: 1px solid black;">
                                    <c:forEach begin="0" end="23" varStatus="loop">
                                    <c:if test="${loop.index == 6 || loop.index == 12 || loop.index == 18}">
                                </td><td style="border-right: 1px solid black;">
                                </c:if>
                                <label class="contain">
                                    <input id="box_P_2_${loop.index}" type="checkbox" onmouseover="check(this)" onmousedown="check(this)">
                                    <span class="checkmark"></span>
                                </label>
                                </c:forEach>
                            </td>
                            </tr>
                            <tr>
                                <th>
                                    <div class="heading">Thursday</div>
                                </th>
                                <td style="border-right: 1px solid black;">
                                    <c:forEach begin="0" end="23" varStatus="loop">
                                    <c:if test="${loop.index == 6 || loop.index == 12 || loop.index == 18}">
                                </td><td style="border-right: 1px solid black;">
                                </c:if>
                                <label class="contain">
                                    <input id="box_P_3_${loop.index}" type="checkbox" onmouseover="check(this)" onmousedown="check(this)">
                                    <span class="checkmark"></span>
                                </label>
                                </c:forEach>
                            </td>
                            </tr>
                            <tr>
                                <th>
                                    <div class="heading">Friday</div>
                                </th>
                                <td style="border-right: 1px solid black;">
                                    <c:forEach begin="0" end="23" varStatus="loop">
                                    <c:if test="${loop.index == 6 || loop.index == 12 || loop.index == 18}">
                                </td><td style="border-right: 1px solid black;">
                                </c:if>
                                <label class="contain">
                                    <input id="box_P_4_${loop.index}" type="checkbox" onmouseover="check(this)" onmousedown="check(this)">
                                    <span class="checkmark"></span>
                                </label>
                                </c:forEach>
                            </td>
                            </tr>
                            <tr>
                                <th>
                                    <div class="heading">Saturday</div>
                                </th>
                                <td style="border-right: 1px solid black;">
                                    <c:forEach begin="0" end="23" varStatus="loop">
                                    <c:if test="${loop.index == 6 || loop.index == 12 || loop.index == 18}">
                                </td><td style="border-right: 1px solid black;">
                                </c:if>
                                <label class="contain">
                                    <input id="box_P_5_${loop.index}" type="checkbox" onmouseover="check(this)" onmousedown="check(this)">
                                    <span class="checkmark"></span>
                                </label>
                                </c:forEach>
                            </td>
                            </tr>
                            <tr>
                                <th>
                                    <div class="heading">Sunday</div>
                                </th>
                                <td style="border-right: 1px solid black;">
                                    <c:forEach begin="0" end="23" varStatus="loop">
                                    <c:if test="${loop.index == 6 || loop.index == 12 || loop.index == 18}">
                                </td><td style="border-right: 1px solid black;">
                                </c:if>
                                <label class="contain">
                                    <input id="box_P_6_${loop.index}" type="checkbox" onmouseover="check(this)" onmousedown="check(this)">
                                    <span class="checkmark"></span>
                                </label>
                                </c:forEach>
                            </td>
                            </tr>
                        </table>
                    </div>
                    <br>



                    <script type="text/javascript">

                        function clearFields() {
                            document.getElementById("myForm").reset();
                            resetBoxes();
                        }

                        function check(box) {
                            if (mouseDown) {
                                box.checked = 1 - box.checked;
                                if (box.checked) {
                                    box.style.background = "white";
                                } else {
                                    box.style.background = "#A3FF9F";

                                }
                            }
                        }


                        var mouseDown = 0;
                        document.body.onmousedown = function() {
                            ++mouseDown;
                        }
                        document.body.onmouseup = function() {
                            --mouseDown;
                        }


                        function generateString() {
                            var string = "";
                            console.log(true);
                            for(var i =0; i < 7; i++) {
                                for(var x=0; x<24; x++) {
                                    if(document.getElementById("box_A_" + i + "_" + x).checked) {
                                        console.log(true);
                                        string = string + "1";
                                    } else {
                                        console.log(false);
                                        string = string + "0";
                                    }
                                }
                                string += ",";
                                for(var j = 0; j < 24; j++) {
                                    if(document.getElementById("box_P_" + i + "_" + j).checked) {
                                        string = string + "1";
                                    } else {
                                        string = string + "0";
                                    }
                                }
                                string += ",";
                            }
                            document.getElementById("constraints").value = string;
                        }


                        function openCity(evt, cityName) {
                            // Declare all variables
                            var i, tabcontent, tablinks;

                            // Get all elements with class="tabcontent" and hide them
                            tabcontent = document.getElementsByClassName("tabcontent");
                            for (i = 0; i < tabcontent.length; i++) {
                                tabcontent[i].style.visibility = "hidden";
                                tabcontent[i].style.position = "absolute";
                            }

                            // Get all elements with class="tablinks" and remove the class "active"
                            tablinks = document.getElementsByClassName("tablinks");
                            for (i = 0; i < tablinks.length; i++) {
                                tablinks[i].className = tablinks[i].className.replace(" active", "");
                            }

                            // Show the current tab, and add an "active" class to the button that opened the tab
                            document.getElementById(cityName).style.visibility = "visible";
                            document.getElementById(cityName).style.position = "relative";
                            evt.currentTarget.className += " active";
                        }

                        function selectEmp(tr) {
                            var active = document.getElementsByClassName("activeE")[0];
                            if(active != null) {
                                active.className = active.className.replace(" activeE", "");
                            }
                            tr.className += " activeE";
                        }


                        function populate(id,firstname,lastname, address,phoneNo,email,type,comments, constraints) {
                            resetBoxes();
                            document.getElementById("id").value = id;
                            document.getElementById("fname").value = firstname;
                            document.getElementById("lname").value = lastname;
                            document.getElementById("address").value = address;
                            document.getElementById("phone").value = phoneNo;
                            document.getElementById("email").value = email;
                            document.getElementById("comment").value = comments;
                            var selObj = document.getElementById('position');

                            if (type === 'S') {
                                selObj.selectedIndex = 1;
                            } else if (type === 'B') {
                                selObj.selectedIndex = 0;
                            } else if (type === 'K') {
                                selObj.selectedIndex = 2;
                            } else if (type === 'M') {
                                selObj.selectedIndex = 3;
                            } else if (type === 'A') {
                                selObj.selectedIndex = 4;
                            }
                            console.log(constraints);

                            var cons = constraints.split(",");
                            for(var i = 0; i<cons.length; i++) {
                                console.log(cons[i]);
                            }

                            for (var j = 0; j < cons.length; j+=2) {
                                console.log(j);
                                console.log(cons[j]);
                                console.log(cons[j+1]);
                                var index = j/2;
                                for (var i = 0; i < cons[j].length; i++) {
                                    if(cons[j].charAt(i) === '1') {
                                        document.getElementById("box_A_" + index + "_" + i).checked = true;
                                    }
                                }
                                for (var i = 0; i < cons[j+1].length; i++) {
                                    if(cons[j+1].charAt(i) === '1') {
                                        document.getElementById("box_P_" + index + "_" + i).checked = true;
                                    }
                                }
                            }
                        }

                        function resetBoxes() {
                            for(var i =0; i < 7; i++) {
                                for(var x=0; x<24; x++) {
                                    document.getElementById("box_A_" + i + "_" + x).checked = false;
                                }
                                for(var j = 0; j < 24; j++) {
                                    document.getElementById("box_P_" + i + "_" + j).checked = false;
                                }
                            }
                        }


                    </script>
>>>>>>> 2f8f94e95d23036d2fa405e4320af1e630ae3af2

            <div class="modal-body">
                <form action="AdminServices" method="post">
                    <div class="form-group">
                        <label for="fname" class="col-form-label">First Name:</label>
                        <input type="text" class="form-control" id="fname" name="fname">
                    </div>
                    <div class="form-group">
                        <label for="lname" class="col-form-label">Last Name:</label>
                        <input type="text" class="form-control" id="lname" name="lname">
                    </div>
                    <div class="form-group">
                        <label for="status" class="col-form-label">Status:</label>
                        <select class="form-control" id="status" name="status">
                            <option>Active</option>
                            <option>Inactive</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="type" class="col-form-label">Type:</label>
                        <select class="form-control" id="type" name="type">
                            <option>Administrator</option>
                            <option>Manager</option>
                            <option>Server</option>
                            <option>Bartender</option>
                            <option>Kitchen</option>
                        </select>
                    </div>
                    <div class="modal-footer">
                        <button type="submit" name="add" class="btn btn-primary">Create</button>
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                    </div>
                </form>
            </div>
        </form>
    </div>
</div>

</body>

</html>
