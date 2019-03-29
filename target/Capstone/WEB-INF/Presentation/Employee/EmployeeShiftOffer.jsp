<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019-01-22
  Time: 5:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Shift Offer</title>
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
            <a href="<%=application.getContextPath() %>/EmployeeServices?page=shiftOffer" class="nav-links w-nav-link">Shift Changes</a>
            <a href="#" class="nav-links w-nav-link">Notifications</a>
            <a href="#" class="nav-links w-nav-link">Settings</a>
        </nav>
    </div>
</div>
<div class="main">

    <div class="title">
        Shift Offer
    </div>


    <div class="columnleft" style="background-color:#d8d8d8;">

        <table class="emp">
            <tr class="positions-top">
                <th class="avail">
                    Start Time
                </th>
                <th class="avail">
                    End Time
                </th>
            </tr>
            <c:forEach var="shift" items="${requestScope.empShifts}" varStatus="i">
                <tr onclick="selectShift(${shift.getShiftId()}, this)" class="alt">
                    <td>
                        <fmt:formatDate value="${shift.getStartTime()}" pattern="dd-MMM-yyyy"/>
                    </td>
                    <td>
                        <fmt:formatDate value="${shift.getEndTime()}" pattern="dd-MMM-yyyy"/>
                    </td>
                </tr>
            </c:forEach>

        </table>
    </div>

    <div class="column" style="background-color:#d8d8d8;">

        <table class="emp">
            <tr class="positions-top">
                <th class="avail">
                    Name
                </th>
                <th class="avail">
                    Position
                </th>
            </tr>
            <c:forEach var="emp" items="${requestScope.empList}" varStatus="i">
                <tr onclick="selectEmp(${emp.getEmpid()}, this)" class="alt">
                    <td>
                            ${emp.getFname()} ${emp.getLname()}
                    </td>
                    <td>
                            ${emp.getType()}
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <form action="/EmployeeServices" method="get">
        <input type="hidden" name="shiftId" id="shiftId">
        <input type="hidden" name="empId" id="empId">
        <input type="submit" value="Submit" class="btns">
    </form>


    <script type="text/javascript">
        function selectEmp(empid, tr) {
            var active = document.getElementsByClassName("activeE")[0];
            if(active != null) {
                active.className = active.className.replace(" activeE", "");
            }
            tr.className += " activeE";
            document.getElementById("empId").value = empid;
        }



        function selectShift(shiftid, tr) {
            var active = document.getElementsByClassName("activeS")[0];
            if(active != null) {
                active.className = active.className.replace(" activeS", "");
            }
            tr.className += " activeS";
            document.getElementById("shiftId").value = shiftId;

        }
    </script>

</div>
</body>
</html>
