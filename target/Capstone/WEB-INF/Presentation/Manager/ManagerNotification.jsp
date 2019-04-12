<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Manager Notifications</title>
    <meta charset="utf-8">
    <title>Settings</title>
    <link href="css/jaycss.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
    <link href="https://code.jquery.com/ui/1.12.1/themes/black-tie/jquery-ui.css" rel="stylesheet" />
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>

<body>
<!--Left side Menu needs to be updated-->
<div data-collapse="tiny" data-animation="over-left" data-duration="400" class="navbar-3 w-nav">
    <div class="container-3 w-container">
        <a href="#" class="brand w-nav-brand">
            <div class="div-block-4"><img src="images/buble-tea.png" width="111" alt="" class="w-hidden-tiny"></div>
        </a>
        <nav role="navigation" class="w-nav-menu">
<<<<<<< HEAD
            <a href="<%=application.getContextPath() %>/ManageScheduleViews" class="nav-links w-nav-link">Home</a>
            <a href="<%=application.getContextPath() %>/ManageEmployees" class="nav-links w-nav-link">Employee Management</a>
            <a href="<%=application.getContextPath() %>/ManagerServices?page=notifications&notifications=notifications" class="nav-links selected w-nav-link">Notifications</a>
            <a href="<%=application.getContextPath() %>/ManagerServices?page=setting" class="nav-links w-nav-link">Settings</a>
            <a href="<%=application.getContextPath() %>/Validate?logout=logout" class="nav-links w-nav-link">Logout</a>
=======
            <a href="ManageScheduleViews" class="nav-links w-nav-link">Home</a>
            <a href="ManageEmployees" class="nav-links w-nav-link">Employee Management</a>
            <a href="ManagerServices?page=notifications" class="nav-links selected w-nav-link">Notifications</a>
            <a href="ManagerServices?page=settings" class="nav-links w-nav-link">Settings</a>
            <a href="Validate?logout=logout" class="nav-links w-nav-link">Logout</a>
>>>>>>> d794742ca2d7b6421c773077f3decb7d5fda2a0e
        </nav>
    </div>
</div>

<div style="margin-left: 20%; margin-top: 10px">
    <div class="container-fluid">
        <ul class="nav nav-tabs">
            <li class="active"><a data-toggle="tab" href="#all">All</a></li>
            <li><a data-toggle="tab" href="#inbox">Inbox</a></li>
            <li><a data-toggle="tab" href="#sent">Sent</a></li>
            <li><a data-toggle="tab" href="#new">New</a></li>
        </ul>
        <div class="tab-content">
            <div id="all" class="tab-pane fade in active">
                <h3>All Notifications</h3>
                <div class="panel-body">
                    <div class="table-responsive">
                    <table class="table table-bordered">
                        <thead>
                        <col width='10%'><%--Date--%>
                        <col width='5%'><%--Sender Name--%>
                        <col width='5%'><%--Recipient Name--%>
                        <col width='10%'><%--Type--%>
                        <col width='50%'><%--Content--%>
                        <col width='10%'><%--Note--%>
                        <col width='10%'><%--Delete--%>
                        <tr class="active">
                            <th>Date:</th>
                            <th>From:</th>
                            <th>To:</th>
                            <th>Type:</th>
                            <th colspan="3">Message:</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${sessionScope.manList}" var="msg">
                            <c:choose>
                                <c:when test="${msg.getStatus() eq 'A'.charAt(0)}">
                                    <tr class="success">
                                </c:when>
                                <c:when test="${msg.getStatus() eq 'D'.charAt(0)}">
                                    <tr class="warning">
                                </c:when>
                                <c:when test="${msg.getStatus() eq 'W'.charAt(0)}">
                                    <tr class="info">
                                </c:when>
                                <c:when test="${msg.getStatus() eq 'I'.charAt(0)}">
                                    <tr class="danger">
                                </c:when>
                                <c:otherwise>
                                    <tr>
                                </c:otherwise>
                            </c:choose>
                            <td>${msg.getDate()}</td>
                            <td>${msg.getSenderName()}</td>
                            <td>${msg.getRecipientName(msg.getRecipient())}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${msg.getNotiftype() eq 'A'.charAt(0)}">
                                        Everyone
                                    </c:when>
                                    <c:when test="${msg.getNotiftype() eq 'P'.charAt(0)}">
                                        Personal
                                    </c:when>
                                    <c:when test="${msg.getNotiftype() eq 'S'.charAt(0)}">
                                        Shift
                                    </c:when>
                                    <c:when test="${msg.getNotiftype() eq 'D'.charAt(0)}">
                                        Department
                                    </c:when>
                                    <c:otherwise>
                                        Unknown
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>${msg.getContent()}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${msg.getStatus() eq 'W'.charAt(0)}">
                                        Request
                                    </c:when>
                                    <c:when test="${msg.getStatus() eq 'D'.charAt(0)}">
                                        Declined
                                    </c:when>
                                    <c:when test="${msg.getStatus() eq 'A'.charAt(0)}">
                                        Accepted
                                    </c:when>
                                    <c:when test="${msg.getStatus() eq 'I'.charAt(0)}">
                                        Important
                                    </c:when>
                                </c:choose>
                            </td>
                            <td>
                                <form action="NotificationServices">
                                    <input type="hidden" name="noteId" value="${msg.getNotifid()}">
                                    <input type="hidden" name="delete" value="true">
                                    <input type="submit" class="btn btn-danger btn-block" value="Delete">
                                </form>
                            </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    </div>
                </div>
            </div>

            <div id="inbox" class="tab-pane fade">
                <h3>Inbox</h3>
                <div class="panel-body">
                    <table class="table table-bordered">
                        <thead>
                        <col width='10%'><%--Date--%>
                        <col width='10%'><%--Sender Name--%>
                        <col width='10%'><%--Type--%>
                        <col width='50%'><%--Content--%>
                        <col width='10%'><%--Buttons--%>
                        <col width='10%'><%--Delete--%>
                        <tr class="active">
                            <th>Date:</th>
                            <th>From:</th>
                            <th>Type:</th>
                            <th colspan="3">Message:</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${sessionScope.receiveList}" var="msg">
                            <c:choose>
                                <c:when test="${msg.getStatus() eq 'A'.charAt(0)}">
                                    <tr class="success">
                                </c:when>
                                <c:when test="${msg.getStatus() eq 'D'.charAt(0)}">
                                    <tr class="warning">
                                </c:when>
                                <c:when test="${msg.getStatus() eq 'W'.charAt(0)}">
                                    <tr class="info">
                                </c:when>
                                <c:when test="${msg.getStatus() eq 'I'.charAt(0)}">
                                    <tr class="danger">
                                </c:when>
                                <c:otherwise>
                                    <tr>
                                </c:otherwise>
                            </c:choose>
                            <td>${msg.getDate()}</td>
                            <td>${msg.getSenderName()}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${msg.getNotiftype() eq 'A'.charAt(0)}">
                                        Everyone
                                    </c:when>
                                    <c:when test="${msg.getNotiftype() eq 'P'.charAt(0)}">
                                        Personal
                                    </c:when>
                                    <c:when test="${msg.getNotiftype() eq 'S'.charAt(0)}">
                                        Shift
                                    </c:when>
                                    <c:when test="${msg.getNotiftype() eq 'D'.charAt(0)}">
                                        Department
                                    </c:when>
                                    <c:otherwise>
                                        Unknown
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>${msg.getContent()}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${msg.getStatus() eq 'W'.charAt(0)}">
                                        Request
                                    </c:when>
                                    <c:when test="${msg.getStatus() eq 'D'.charAt(0)}">
                                        Declined
                                    </c:when>
                                    <c:when test="${msg.getStatus() eq 'A'.charAt(0)}">
                                        Accepted
                                    </c:when>
                                    <c:when test="${msg.getStatus() eq 'I'.charAt(0)}">
                                        Important
                                    </c:when>
                                </c:choose>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${msg.getStatus() eq 'W'.charAt(0)}">
                                        <form action="NotificationServices">
                                            <input type="hidden" name="noteId" value="${msg.getNotifid()}">
                                            <input type="submit" class="btn btn-success btn-block" name="accept" value="Accept">
                                            <input type="submit" class="btn btn-warning btn-block" name="decline" value="Decline">
                                        </form>
                                    </c:when>
                                    <c:otherwise>
                                        <form action="NotificationServices">
                                            <input type="hidden" name="noteId" value="${msg.getNotifid()}">
                                            <input type="hidden" name="delete" value="true">
                                            <input type="submit" class="btn btn-danger btn-block" value="Delete">
                                        </form>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

            <div id="sent" class="tab-pane fade">
                <h3>Sent</h3>
                <div class="panel-body">
                    <table class="table table-bordered">
                        <thead>
                        <col width='10%'><%--Date--%>
                        <col width='10%'><%--Sender Name--%>
                        <col width='10%'><%--Type--%>
                        <col width='70%'><%--Content--%>
                        <tr class="active">
                            <th>Date:</th>
                            <th>To:</th>
                            <th>Type:</th>
                            <th>Message:</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${sessionScope.sentList}" var="msg">
                            <tr>
                                <td>${msg.getDate()}</td>
                                <td>${msg.getRecipientName(msg.getRecipient())}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${msg.getNotiftype() eq 'A'.charAt(0)}">
                                            Everyone
                                        </c:when>
                                        <c:when test="${msg.getNotiftype() eq 'P'.charAt(0)}">
                                            Personal
                                        </c:when>
                                        <c:when test="${msg.getNotiftype() eq 'S'.charAt(0)}">
                                            Shift
                                        </c:when>
                                        <c:when test="${msg.getNotiftype() eq 'D'.charAt(0)}">
                                            Department
                                        </c:when>
                                        <c:otherwise>
                                            Unknown
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>${msg.getContent()}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <div id="new" class="tab-pane fade">
                <h3>New Notification</h3>
                <div class="panel-body">
                    <form action="NotificationServices">
                        <div class="form-group">
                            <label for="type">Type of Notification:</label>
                            <select class="form-control" id="type" name="type" onchange="enableMenu(this.value)">
                                <option value="A">Everyone</option>
                                <option value="D">Department</option>
                                <option value="P">Personal</option>
                                <option value="S">Shift</option>
                            </select>
                        </div>

                        <div class="form-group">
                            <label for="dept">Select Department:</label>
                            <select class="form-control" id="dept" name="dept">
                                <option></option>
                                <option value="front">Front</option>
                                <option value="bar">Bar</option>
                                <option value="kitchen">Kitchen</option>
                            </select>
                        </div>

                        <div class="form-group">
                            <label for="to">If Personal Notification, Select Employee:</label>
                            <select class="form-control" id="to" name="to">
                                <option></option>
                                <c:forEach items="${sessionScope.empList}" var="emp">
                                    <option value="${emp.getEmpid()}">${emp.getFname()}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-inline">
                            <label>Mark Important: <input type="checkbox" name="important" value="true"></label>
                        </div>
                        <div class="form-group">
                            <label for="comment">Comment:</label>
                            <textarea class="form-control" rows="5" id="comment" name="comment"></textarea>
                        </div>
                        <input type="hidden" name="send" value="true">
                        <input type="hidden" name="status" value="N">
                        <div style="width:100px;height:30px;">
                            <input type="submit" class="btn btn-info" size="5" value="Submit">
                        </div>
                    </form>
                    <script>
                        function enableMenu(type) {
                            if(type.equals("D")) {
                                document.getElementById("dept").disabled = false;
                            }
                            else if (type.equals("P")) {
                                document.getElementById("to").disabled = false;
                            }
                            else {
                                return;
                            }
                        }
                    </script>
                </div>
            </div>
        </div>
    </div>
</div>


</body>
</html>
