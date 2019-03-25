<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019-01-22
  Time: 6:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html data-wf-page="5c096a2e368c33a2069e1a42" data-wf-site="5bfd62d9457454d30221aa10">
<head>
    <meta charset="utf-8">
    <title>manager schedule</title>
    <meta content="manager schedule" property="og:title">
    <meta content="width=device-width, initial-scale=1" name="viewport">
    <meta content="Webflow" name="generator">
    <link href="css/normalize.css" rel="stylesheet" type="text/css">
    <link href="css/webflow.css" rel="stylesheet" type="text/css">
    <link href="css/matthews-cool-project-2c37b7.webflow.css" rel="stylesheet" type="text/css">
    <link href="https://daks2k3a4ib2z.cloudfront.net/img/webclip.png" rel="apple-touch-icon">

    <!-- fullcalendar' need -->
    <link href='css/calendarCSS/fullcalendar.min.css' rel='stylesheet' />
    <link href='css/calendarCSS/fullcalendar.print.min.css' rel='stylesheet' media='print' />
    <script src='js/calendarCore/moment.min.js'></script>
    <script src='js/calendarCore/jquery.min.js'></script>
    <script src='js/calendarCore/fullcalendar.min.js'></script>
    <script src='js/calendarMaintain/maintainSerEvent.js'></script>

    <!-- fullcalendar' jquery theme -->
    <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>
    <link href="https://code.jquery.com/ui/1.12.1/themes/black-tie/jquery-ui.css" rel="stylesheet" />

    <style>

        body {
            margin: 30px 10px;
            padding: 0;
        }

        #calendar {
            max-width: 900px;
            margin: 0 auto;
            margin-left: 25%;
        }

        .widget{
            margin-left: 25%;
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
            <a href="#" class="nav-links selected w-nav-link">Home</a>
            <a href="<%=application.getContextPath() %>/TestServlet" class="nav-links w-nav-link">Employee Management</a>
            <a href="#" class="nav-links w-nav-link">Notifications</a>
            <a href="#" class="nav-links w-nav-link">Settings</a>
        </nav>
        <div class="menu-button w-nav-button">
            <img src="images/Hamburger_icon.svg.png" alt="" class="image-3">
        </div>
    </div>
</div>

<!--Different views for calendar-->
<div class="widget">
    <a href="ManageScheduleViews?message=server">Server</a>
    <a href="ManageScheduleViews?message=bartender">Bartender</a>
    <a href="ManageScheduleViews?message=kitchen">Kitchen</a>
</div>
<br/>

<!--Calendar Schedule -->
<div id='calendar'></div>

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
    $( function() {
        var dialog;
        function displayErrors(){
            <% String str= (String) session.getAttribute("message"); %>
            var s="<%=str%>";
            if(s === "sameEmp"){
                alert("test!");
            }
        }

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
    } );



</script>
</body>
</html>
