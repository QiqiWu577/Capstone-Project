<!DOCTYPE html>
<html>

<head>
    <title>Employee Management</title>
    <link href="images/employeemgmt.css" rel="stylesheet" type="text/css">
</head>

<body>
<div class="sidebar">
    <div class="logo">
        <img src="images/2030.png" alt="2030" height="100" width="100">
    </div>
    <a href="home.html">Home</a>
    <a href="employeemgmt.html">User Management</a>
    <a href="settings.html">Settings</a>
</div>

<div class="main">
    <div class="topbar">
        <a href="notifications.html">
            <img src="res/61073.svg" height="30" width="30">
        </a>
    </div>
    <div class="title">
        Employee Management
    </div>
    <div class="row">
        <div class="columnleft" style="background-color:#d8d8d8;">
            <br>
            <input type="text" placeholder="Search"><br/>
            <div class="table">
            </div>
            <table>
                <tr class="positions-top">
                    <th>ID</th>
                    <th>Name</th>
                    <th>Position</th>
                </tr>
                <tr class="alt">
                    <td>001</td>
                    <td>Dave Johnson</td>
                    <td>Bartender</td>
                </tr>
                <tr class="alt">
                    <td>002</td>
                    <td>Mark Smith</td>
                    <td>Server</td>
                </tr>
                <tr class="alt">
                    <td>003</td>
                    <td>Ronald McDonald</td>
                    <td>Kitchen</td>
                </tr>
                <tr class="alt">
                    <td>004</td>
                    <td>John Smith</td>
                    <td>Kitchen</td>
                </tr>
                <tr class="alt">
                    <td>005</td>
                    <td>Mark Wahlberg</td>
                    <td>Bartender</td>
                </tr>
                <tr class="alt">
                    <td>006</td>
                    <td>Michael Scott</td>
                    <td>Kitchen</td>
                </tr>

            </table>
        </div>
        <div class="column" style="background-color:#d8d8d8;">

            <div class="row">
                <div class="column" style="background-color:#d8d8d8;">
                    <label for="id">ID</label><br/>
                    <input type="text" id="id"><br/>
                    <label for="fname">First Name</label>
                    <input type="text" placeholder="John" id="fname"><br/>
                    <label for="lname">Last Name</label>
                    <input type="text" placeholder="Doe" id="lname"><br/>
                    <label for="position">Position</label><br/>
                    <select id="position">
                        <option value="Bartender">Bartender</option>
                        <option value="Server">Server</option>
                        <option value="Kitchen">Kitchen</option>
                    </select>
                </div>
                <div class="column" style="background-color:#d8d8d8;">
                    <label for="email">Email Address</label><br/>
                    <input type="text" id="email" placeholder="email@example.com"><br/>
                    <label for="address">Address</label><br/>
                    <input type="text" id="address" placeholder="123 Main Street"><br/>
                    <label for="phone">Phone Number</label><br/>
                    <input type="text" id="phone" placeholder="### - ### - ####"><br/>
                </div>
            </div>

            <div class="bottom">
                <label for="comment">Comments</label>
                <br>
                <textarea name="comment" rows="4" cols="70" form="usrform" placeholder="Example Text"></textarea>
                <div class="btngrp">
                    <input type="button" value="Save" class="btns">
                    <input type="button" value="Clear" class="btns">
                    <input type="button" value="Delete" class="btns">
                </div>

                <div class="row">
                    <table>


                        <tr class="schedule">
                            <td class="day">Sunday</td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                        </tr>

                        <tr class="schedule">
                            <td class="day">Monday</td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                        </tr>

                        <tr class="schedule">
                            <td class="day">Tuesday</td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                        </tr>

                        <tr class="schedule">
                            <td class="day">Wednesday</td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                        </tr>

                        <tr class="schedule">
                            <td class="day">Thursday</td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                        </tr>

                        <tr class="schedule">
                            <td class="day">Friday</td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                        </tr>

                        <tr class="schedule">
                            <td class="day">Saturday</td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                            <td class="hour"></td>
                        </tr>

                    </table>



                </div>
            </div>
        </div>
    </div>
</div>

</body>

</html>
