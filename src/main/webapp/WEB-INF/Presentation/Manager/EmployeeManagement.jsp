<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <title>Employee Management</title>
    <link href="${pageContext.request.contextPath}/css/employeemgmt.css" rel="stylesheet" type="text/css">
</head>

<body>
<!--Left side Menu -->
<div data-collapse="tiny" data-animation="over-left" data-duration="400" class="navbar-3 w-nav">
    <div class="container-3 w-container">
        <a href="#" class="brand w-nav-brand">
            <div class="div-block-4"><img src="images/buble-tea.png" width="111" alt="" class="w-hidden-tiny"></div>
        </a>
        <nav role="navigation" class="w-nav-menu">
            <a href="<%=application.getContextPath() %>/ManageScheduleViews" class="nav-links w-nav-link">Home</a>
            <a href="<%=application.getContextPath() %>/TestServlet?page=1" class="nav-links selected w-nav-link">Employee Management</a>
            <a href="#" class="nav-links w-nav-link">Notifications</a>
            <a href="#" class="nav-links w-nav-link">Settings</a>
        </nav>
    </div>
</div>

<div class="main">
    <div class="title">
        Employee Management
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
                            '${emp.getAddress()}','${emp.getPhoneno()}','${emp.getEmail()}','${emp.getType()}','${emp.getNotes()}','${emp.getEmployeeConstraints().getConstraints()}')">
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
                            }
                            console.log(constraints);

                            var cons = constraints.split(",");


                            for (var j = 0; j < 7; j++) {
                                for (var i = 0; i < cons[j].length; i++) {
                                    if(constraints.charAt(i) === '1') {
                                        document.getElementById("box_A_" + j + "_" + i).checked = true;
                                    }
                                }
                                for (var i = 0; i < cons[j+1].length; i++) {
                                    if(constraints.charAt(i) === '1') {
                                        document.getElementById("box_P_" + j + "_" + i).checked = true;
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

                </div>
            </div>
        </form>
    </div>
</div>

</body>

</html>
