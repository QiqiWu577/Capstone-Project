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
    <script src="https://ajax.googleapis.com/ajax/libs/webfont/1.4.7/webfont.js" type="text/javascript"></script>
    <script type="text/javascript">WebFont.load({  google: {    families: ["Open Sans:300,300italic,400,400italic,600,600italic,700,700italic,800,800italic"]  }});</script>
    <!-- [if lt IE 9]><script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js" type="text/javascript"></script><![endif] -->
    <script type="text/javascript">!function(o,c){var n=c.documentElement,t=" w-mod-";n.className+=t+"js",("ontouchstart"in o||o.DocumentTouch&&c instanceof DocumentTouch)&&(n.className+=t+"touch")}(window,document);</script>
    <link href="https://daks2k3a4ib2z.cloudfront.net/img/favicon.ico" rel="shortcut icon" type="image/x-icon">
    <link href="https://daks2k3a4ib2z.cloudfront.net/img/webclip.png" rel="apple-touch-icon">

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

    <script>
        $( function() {
            $( ".widget a" ).button();
        } );
    </script>
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


<script src="js/webflow.js" type="text/javascript"></script>
<!-- [if lte IE 9]><script src="https://cdnjs.cloudflare.com/ajax/libs/placeholders/3.0.2/placeholders.min.js"></script><![endif] -->
</body>
</html>
