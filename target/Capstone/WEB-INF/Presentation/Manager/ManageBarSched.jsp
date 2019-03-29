<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>manager schedule</title>
    <meta content="manager schedule" property="og:title">
    <meta content="width=device-width, initial-scale=1" name="viewport">
    <meta content="Webflow" name="generator">
    <link href="css/webflow.css" rel="stylesheet" type="text/css">
    <link href="css/matthews-cool-project-2c37b7.webflow.css" rel="stylesheet" type="text/css">

    <!-- fullcalendar' need -->
    <link href='css/calendarCSS/fullcalendar.min.css' rel='stylesheet' />
    <link href='css/calendarCSS/fullcalendar.print.min.css' rel='stylesheet' media='print' />
    <script src='js/calendarCore/moment.min.js'></script>
    <script src='js/calendarCore/jquery.min.js'></script>
    <script src='js/calendarCore/fullcalendar.min.js'></script>
    <script src='js/calendarMaintain/maintainSerEvent.js'></script>

    <!-- fullcalendar' jquery theme -->
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
    <link href="https://code.jquery.com/ui/1.12.1/themes/black-tie/jquery-ui.css" rel="stylesheet" />

    <style>
        .mainBody{
            margin-left: 25%;
        }

        .widget{
            padding-top: 10px;
        }

        #calendar {
            max-width: 900px;
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
            <a href="<%=application.getContextPath() %>/ManageScheduleViews" class="nav-links w-nav-link">Home</a>
            <a href="<%=application.getContextPath() %>/ManageEmployees" class="nav-links w-nav-link">Employee Management</a>
            <a href="#" class="nav-links w-nav-link">Notifications</a>
            <a href="<%=application.getContextPath() %>/ManagerServices" class="nav-links w-nav-link">Settings</a>
            <a href="<%=application.getContextPath() %>/Validate?logout=logout" class="nav-links w-nav-link">Logout</a>

        </nav>
    </div>
</div>

<div class="mainBody">
    <!--Different views for calendar-->
    <div class="widget">
        <a href="ManageScheduleViews?message=server">Server</a>
        <a href="ManageScheduleViews?message=bartender">Bartender</a>
        <a href="ManageScheduleViews?message=kitchen">Kitchen</a>
    </div>
    <br/>

    <!--Calendar Schedule -->
    <div id='calendar'></div>
</div>

<div id="dialog" title="Create new shift">
    <p class="validateTips"></p>

    <form>
        <fieldset>
            <label for="employee">Employee</label>
            <input type="text" name="employee" id="employee" class="text ui-widget-content ui-corner-all">
            <label for="start">Event Start Time</label>
            <input type="text" name="start" id="start" class="text ui-widget-content ui-corner-all">
            <label for="end">Event Start Time</label>
            <input type="text" name="end" id="end" class="text ui-widget-content ui-corner-all">

            <input type="submit" tabindex="-1" style="position:absolute; top:-1000px">
        </fieldset>
    </form>
</div>

<!-- div id="error" title="Notice">
    <p>One shift is only for one employee! Please change the shift!</p>
</div-->

<script>
    //style the button in the page




    $( ".widget a" ).button();

    // dialog = $( "#error" ).dialog({
    //     autoOpen: false,
    //     modal: true,
    //     buttons: {
    //         text: "Ok",
    //         icon: "ui-icon-heart",
    //         click: function() {
    //             $( this ).dialog( "close" );
    //         }
    //     }
    // });

    // $("#dialog").dialog({
    //     autoOpen: false,
    //     height: 400,
    //     width: 350,
    //     modal: true,
    //     buttons: {
    //         "Confirm": addShift,
    //         Cancel: function(){
    //             dialog.dialog("close");
    //         }
    //     },
    //     close: function () {
    //         form[0].reset();
    //         allFields.removeClass("ui-state-error");
    //     }
    // });




</script>
</body>
</html>
