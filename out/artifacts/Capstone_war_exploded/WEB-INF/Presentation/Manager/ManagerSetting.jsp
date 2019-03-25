<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <title>Employee Management</title>
    <link href="css/managerSetting.css" rel="stylesheet" type="text/css">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
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
    <div class="container-fluid">
        <ul class="nav nav-tabs">
            <li class="active"><a data-toggle="tab" href="#front">Front End</a></li>
            <li><a data-toggle="tab" href="#bar">Bar</a></li>
            <li><a data-toggle="tab" href="#kitchen">Kitchen</a></li>
        </ul>

        <div class="tab-content">
            <div id="front" class="tab-pane fade in active">
                <h3>Front End</h3>
                <div class="panel-body">

                    <table class="table table-bordered">
                        <thead>
                        <col width='12.5%'>
                        <col width='12.5%'>
                        <col width='12.5%'>
                        <col width='12.5%'>
                        <col width='12.5%'>
                        <col width='12.5%'>
                        <col width='12.5%'>
                        <col width='12.5%'>
                        <tr>
                            <th>Day</th> <!-- -->
                            <th>Name</th> <!-- -->
                            <th>Shift Start</th> <!-- -->
                            <th>Shift End</th> <!-- -->
                            <th>Min. # of Staff</th>
                            <th>Max # of Staff</th><!-- -->
                            <th colspan="2" align="center">Options</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${sessionScope.frontList}" var="shift">
                            <tr>
                                <form action="ManagerSettings">
                                    <input type="hidden" value="${shift.getShiftId()}" name="shiftId">
                                    <input type="hidden" value="true" name="update">
                                    <input type="hidden" value="${shift.getDayOfWeek()}" name="dayOfWeek">
                                    <input type="hidden" value="${shift.getType()}" name="shiftType">
                                    <td>${shift.getDayOfWeek()}</td>
                                    <td><input type="text" name="newName" value="${shift.getName()}"></td>
                                    <td><input type="text" name="newStart" value="${shift.getStartTime()}"></td>
                                    <td><input type="text" name="newEnd" value="${shift.getEndTime()}"></td>
                                    <td><input type="text" name="newMinEmp" value="${shift.getMinNoEmp()}"></td>
                                    <td><input type="text" name="newMaxEmp" value="${shift.getMaxNoEmp()}"></td>
                                    <td><input class="btn btn-info" type='submit' value="Update"></td>
                                </form>
                                <form action="ManagerSettings">
                                    <input type="hidden" value="${shift.getShiftId()}" name="shiftId">
                                    <input type="hidden" value="true" name="delete">
                                    <td><input type="submit" class="btn btn-danger" value="Delete"></td>
                                </form>
                            </tr>
                        </c:forEach>
                        <!-- Trigger the modal with a button -->
                        <tr><td colspan="7"><b>${requestScope.shiftMessage}</b></td><td><input class="btn btn-info" data-toggle="modal" data-target="#myModal" value="Add Shift"></td></tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div id="bar" class="tab-pane fade">
                <h3>Bar</h3>
                <div class="panel-body">

                    <table class="table table-bordered">
                        <thead>
                        <col width='12.5%'>
                        <col width='12.5%'>
                        <col width='12.5%'>
                        <col width='12.5%'>
                        <col width='12.5%'>
                        <col width='12.5%'>
                        <col width='12.5%'>
                        <col width='12.5%'>
                        <tr>
                            <th>Day</th> <!-- -->
                            <th>Name</th> <!-- -->
                            <th>Shift Start</th> <!-- -->
                            <th>Shift End</th> <!-- -->
                            <th>Min. # of Staff</th>
                            <th>Max # of Staff</th><!-- -->
                            <th colspan="2" align="center">Options</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${sessionScope.barList}" var="shift">
                            <tr>
                                <form action="ManagerSettings">
                                    <input type="hidden" value="${shift.getShiftId()}" name="shiftId">
                                    <input type="hidden" value="true" name="update">
                                    <input type="hidden" value="${shift.getDayOfWeek()}" name="dayOfWeek">
                                    <input type="hidden" value="${shift.getType()}" name="shiftType">
                                    <td>${shift.getDayOfWeek()}</td>
                                    <td><input type="text" name="newName" value="${shift.getName()}"></td>
                                    <td><input type="text" name="newStart" value="${shift.getStartTime()}"></td>
                                    <td><input type="text" name="newEnd" value="${shift.getEndTime()}"></td>
                                    <td><input type="text" name="newMinEmp" value="${shift.getMinNoEmp()}"></td>
                                    <td><input type="text" name="newMaxEmp" value="${shift.getMaxNoEmp()}"></td>
                                    <td><input class="btn btn-info" type='submit' value="Edit"></td>
                                </form>
                                <form action="ManagerSettings">
                                    <input type="hidden" value="${shift.getShiftId()}" name="shiftId">
                                    <input type="hidden" value="true" name="delete">
                                    <td><input type="submit" class="btn btn-danger" value="Delete"></td>
                                </form>
                            </tr>
                        </c:forEach>
                        <!-- Trigger the modal with a button -->
                        <tr><td colspan="7"><b>${requestScope.shiftMessage}</b></td><td><input class="btn btn-info" data-toggle="modal" data-target="#myModal" value="Add Shift"></td></tr>                        </tbody>
                    </table>
                </div>
            </div>
            <div id="kitchen" class="tab-pane fade">
                <h3>Kitchen</h3>
                <div class="panel-body">
                    <table class="table table-bordered">
                        <thead>
                        <col width='12.5%'>
                        <col width='12.5%'>
                        <col width='12.5%'>
                        <col width='12.5%'>
                        <col width='12.5%'>
                        <col width='12.5%'>
                        <col width='12.5%'>
                        <col width='12.5%'>
                        <tr>
                            <th>Day</th> <!-- -->
                            <th>Name</th> <!-- -->
                            <th>Shift Start</th> <!-- -->
                            <th>Shift End</th> <!-- -->
                            <th>Min. # of Staff</th>
                            <th>Max # of Staff</th><!-- -->
                            <th colspan="2" align="center">Options</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${sessionScope.kitchenList}" var="shift">
                            <tr>
                                <form action="ManagerSettings">
                                    <input type="hidden" value="${shift.getShiftId()}" name="shiftId">
                                    <input type="hidden" value="true" name="update">
                                    <input type="hidden" value="${shift.getDayOfWeek()}" name="dayOfWeek">
                                    <input type="hidden" value="${shift.getType()}" name="shiftType">
                                    <td>${shift.getDayOfWeek()}</td>
                                    <td><input type="text" name="newName" value="${shift.getName()}"></td>
                                    <td><input type="text" name="newStart" value="${shift.getStartTime()}"></td>
                                    <td><input type="text" name="newEnd" value="${shift.getEndTime()}"></td>
                                    <td><input type="text" name="newMinEmp" value="${shift.getMinNoEmp()}"></td>
                                    <td><input type="text" name="newMaxEmp" value="${shift.getMaxNoEmp()}"></td>
                                    <td><input class="btn btn-info" type='submit' value="Edit"></td>
                                </form>
                                <form action="ManagerSettings">
                                    <input type="hidden" value="${shift.getShiftId()}" name="shiftId">
                                    <input type="hidden" value="true" name="delete">
                                    <td><input type="submit" class="btn btn-danger" value="Delete"></td>
                                </form>
                            </tr>
                        </c:forEach>
                        <!-- Trigger the modal with a button -->
                        <tr><td colspan="7"><b>${requestScope.shiftMessage}</b></td><td><input class="btn btn-info" data-toggle="modal" data-target="#myModal" value="Add Shift"></td></tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>




<!-- Modal -->
<div id="myModal" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h2 class="modal-title">Add Shift</h2>
            </div>
            <form action="ManagerSettings">
                <input type="hidden" value="true" name="add">
            <div class="modal-body">
                <div class="form-group">
                <label for="type">Department:</label>
                <select class="form-control" id="type" name="type">
                    <option>Front End</option>
                    <option>Bar</option>
                    <option>Kitchen</option>
                </select>
                </div>
                <div class="form-group">
                    <label for="dayOfWeek">Day of Week:</label>
                    <select class="form-control" id="dayOfWeek" name="dayOfWeek">
                        <option>Sunday</option>
                        <option>Monday</option>
                        <option>Tuesday</option>
                        <option>Wednesday</option>
                        <option>Thursday</option>
                        <option>Friday</option>
                        <option>Saturday</option>
                    </select>
                </div>
                <label for="name">Name of Shift:</label>
                <input type="text" class="form-control form-control-sm" name="name" id="name" placeholder="Ex: Opening">
                <br>
                <label for="duration">Shift Duration:</label>
                <div class="form-inline" id="duration">
                    <div class="form-group">
                        <input data-date-format="HH:mm:ss" class="form-control form-control-sm" type="time" value="00:00:00" id="start" name="start">
                    </div>
                    <div class="form-group">
                        <label for="end">To</label>
                        <input data-date-format="HH:mm:ss" class="form-control form-control-sm" type="time" value="12:00:00" id="end" name="end">
                    </div>
                </div>
                <br>
                <label for="numberEmp">Number of Employees:</label>
                <div class="form-inline" id="numberEmp">
                    <div class="form-group">
                        <label for="min">Minimum:</label>
                        <input type="number" class="form-control form-control-sm" name="min" id="min">
                    </div>
                    <div class="form-group">
                        <label for="max">Maximum:</label>
                        <input type="number" class="form-control form-control-sm" name="max" id="max">
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="submit" class="btn btn-info">Add Shift</button>
            </form>
                <button type="button" class="btn btn-danger" data-dismiss="modal">Cancel</button>
            </div>
        </div>
    </div>
</div>

</body>
</html>