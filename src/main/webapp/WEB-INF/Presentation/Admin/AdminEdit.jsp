<!DOCTYPE html>
<html>
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
<!--Left side Menu -->
<div data-collapse="tiny" data-animation="over-left" data-duration="400" class="navbar-3 w-nav">
    <div class="container-3 w-container">
        <a href="#" class="brand w-nav-brand">
            <div class="div-block-4"><img src="images/buble-tea.png" width="111" alt="" class="w-hidden-tiny"></div>
        </a>
        <nav role="navigation" class="w-nav-menu">
            <a href="#" class="nav-links w-nav-link">Home</a>
            <a href="#" class="nav-links w-nav-link">Settings</a>
            <a href="Validate?logout=logout" class="nav-links w-nav-link">Logout</a>
        </nav>
    </div>
</div>

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
        </div>
    </div>
</div>

</body>
</html>
