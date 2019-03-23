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
                            <th>Start of Shift</th> <!-- -->
                            <th>End of Shift</th> <!-- -->
                            <th>Minimum # of staff</th>
                            <th>Max # of staff</th><!-- -->
                            <th></th>
                            <th></th>
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
                        <tr><td colspan="7"></td><td><button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">Open Modal</button></td></tr>                        </tbody>
                    </table>

                    ${requestScope.shiftMessage}<br/>
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
                            <th>Start of Shift</th> <!-- -->
                            <th>End of Shift</th> <!-- -->
                            <th>Minimum # of staff</th>
                            <th>Max # of staff</th><!-- -->
                            <th></th>
                            <th></th>
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
                        <tr><td colspan="7"></td><td><button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">Open Modal</button></td></tr>                        </tbody>
                    </table>

                    ${requestScope.shiftMessage}<br/>
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
                            <th>Start of Shift</th> <!-- -->
                            <th>End of Shift</th> <!-- -->
                            <th>Minimum # of staff</th>
                            <th>Max # of staff</th><!-- -->
                            <th></th>
                            <th></th>
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
                        <tr><td colspan="7"></td><td><button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">Open Modal</button></td></tr>
                        </tbody>
                    </table>

                    ${requestScope.shiftMessage}<br/>
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
                <h4 class="modal-title">Add Shift</h4>
            </div>
            <div class="modal-body">
                <form action="ManagerSettings">
                    <div class="form-group">
                    <label for="type">Department:</label>
                    <select class="form-control" id="type">
                        <option>Front End</option>
                        <option>Bar</option>
                        <option>Kitchen</option>
                    </select>
                    </div>
                    <div class="form-group">
                        <label for="dayOfWeek">Day of Week:</label>
                        <select class="form-control" id="dayOfWeek">
                            <option>Sunday</option>
                            <option>Monday</option>
                            <option>Tuesday</option>
                            <option>Wednesday</option>
                            <option>Thursday</option>
                            <option>Friday</option>
                            <option>Saturday</option>
                        </select>
                    </div>
                    <input type="text" name="name" value="Opening">
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>

    </div>
</div>

</body>
</html>