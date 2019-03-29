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
    <title>Title</title>
</head>
<body>
<div class="sidebar">
    <div class="logo">
        <img src="res/2030%20logo.png" alt="2030" height="100" width="100">
    </div>
    <a href="home.html">Home</a>
    <a href="employeemgmt.html">Employee Management</a>
    <a href="settings.html">Settings</a>
</div>

<div class="main">
    <div class="topbar">
        <a href="notifications.html">
            <img src="images/61073.svg" height="30" width="30">
        </a>
    </div>
    <div class="title">
        Notifications
    </div>
    <div class="column" style="width:100%">

        <div class="notification">
            <div class="left">
                <img src="images/bell.png" height="100" width="100">
            </div>
            <br />

            <strong>Notification Title</strong>
            <p>This is the description for the notification.</p>

            <div class="btngrp">
                <input type="button" value="Dismiss" class="btns">
            </div>

        </div>

    </div>

</div>

</body>
</html>
