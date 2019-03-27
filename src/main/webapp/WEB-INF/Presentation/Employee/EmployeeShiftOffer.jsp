<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <a href="<%=application.getContextPath() %>/TestServlet?page=1" class="nav-links selected w-nav-link">Employee Management</a>
            <a href="#" class="nav-links w-nav-link">Notifications</a>
            <a href="#" class="nav-links w-nav-link">Settings</a>
        </nav>
    </div>
</div>
<div class="main">

    <div class="title">
        Shift Offer
    </div>

    <table>
        <tr>
            <th>
                Start Time
            </th>
            <th>
                End Time
            </th>
        </tr>

        <c:forEach var="shifts" items="${requestScope.EmpShifts}" varStatus="i">
            <tr onclick="selectShift()">
                <td>
                        ${shift.getStartTime().toString()}
                </td>
                <td>
                        ${shift.getEndTime().toString()}
                </td>
            </tr>
        </c:forEach>

    </table>



    <table>
        <tr>
            <th>
                Name
            </th>
            <th>
                Position
            </th>
        </tr>
        <c:forEach var="emp" items="${requestScope.empList}" varStatus="i">
            <tr onclick="selectEmp()">
                <td>
                    
                </td>
                <td>

                </td>
            </tr>
        </c:forEach>



    </table>


</div>
</body>
</html>
