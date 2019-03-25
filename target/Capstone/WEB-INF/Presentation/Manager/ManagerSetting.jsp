<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html data-wf-page="5c0860abe5f863ca38dfd5cd" data-wf-site="5bfd62d9457454d30221aa10">
<head>
    <meta charset="utf-8">
    <title>Settings</title>
    <link href="css/webflow.css" rel="stylesheet" type="text/css">
    <link href="css/matthews-cool-project-2c37b7.webflow.css" rel="stylesheet" type="text/css">
    <link href="css/employeemgmt.css" rel="stylesheet" type="text/css">
</head>
<body class="body-2">
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

    <div class="section-4-copy">
        <div class="w-container">
            <h1>Settings</h1>
        </div>
        <div class="w-container">
            <h3>Hours of Operation</h3>
        </div>

        <div class="w-row">

            <table>

                <tr><th></th><th>12am-5am</th><th>6am-11am</th><th>12pm-5pm</th><th>6pm-11pm</th></tr>
                <tr>
                    <th>
                        <div  class="heading">Monday</div>
                    </th>
                    <td style="border-right: 1px solid black;">
                        <c:forEach begin="0" end="23" varStatus="loop">

                        <c:if test="${loop.index == 6 || loop.index == 12 || loop.index == 18}">
                    </td>
                    <td style="border-right: 1px solid black;">
                    </c:if>
                    <label class="contain">
                        <input class="input" id="box_A_0_${loop.index}" type="checkbox" onclick="check(this)">
                        <span class="checkmark"></span>
                    </label>
                    </c:forEach>
                    </td>
                </tr>

                <tr>
                    <th>
                        <div class="heading">Tuesday</div>
                    </th>
                    <td style="border-right: 1px solid black;">
                        <c:forEach begin="0" end="23" varStatus="loop">
                        <c:if test="${loop.index == 6 || loop.index == 12 || loop.index == 18}">
                    </td><td style="border-right: 1px solid black;">
                    </c:if>
                    <label class="contain">
                        <input id="box_A_1_${loop.index}" type="checkbox">
                        <span class="checkmark"></span>
                    </label>
                    </c:forEach>
                </td>
                </tr>
            </table>

            <script type="text/javascript">

                function check(box) {
                    console.log("check");
                    console.log(mouseDown);
                    if (mouseDown) {
                        box.checked = 1 - box.checked;
                        if (box.checked) {
                            box.style.background = "white";
                        } else {
                            box.style.background = "#A3FF9F";

                        }
                    }
                }

                var mouseDown = 0;
                document.body.onmousedown = function() {
                    ++mouseDown;
                }
                document.body.onmouseup = function() {
                    --mouseDown;
                }

                function generateString() {
                    var string = "";
                    console.log(true);
                    for(var i =0; i < 7; i++) {
                        for(var x=0; x<24; x++) {
                            if(document.getElementById("box_A_" + i + "_" + x).checked) {
                                console.log(true);
                                string = string + "1";
                            } else {
                                console.log(false);
                                string = string + "0";
                            }
                        }
                        string += ",";
                        for(var j = 0; j < 24; j++) {
                            if(document.getElementById("box_P_" + i + "_" + j).checked) {
                                string = string + "1";
                            } else {
                                string = string + "0";
                            }
                        }
                        string += ",";
                    }
                    document.getElementById("constraints").value = string;
                }
            </script>

        </div>
    </div>

    <div class="section-7">
        <div class="container-9 w-container">
            <div class="w-form">
                <form id="email-form" name="email-form" data-name="Email Form">
                    <div class="w-checkbox"><input type="checkbox" id="checkbox" name="checkbox" data-name="Checkbox" class="w-checkbox-input"><label for="checkbox" class="checkbox-label w-form-label"><strong>Require approval for shift changes</strong></label></div>
                </form>
                <div class="w-form-done">
                    <div>Thank you! Your submission has been received!</div>
                </div>
                <div class="w-form-fail">
                    <div>Oops! Something went wrong while submitting the form.</div>
                </div>
            </div>
        </div>
        <h3>Manage Shifts</h3>
        <div data-duration-in="300" data-duration-out="100" class="w-tabs">
            <div class="w-tab-menu">
                <a data-w-tab="Tab 1" class="w-inline-block w-tab-link w--current">
                    <div>Server</div>
                </a>
                <a data-w-tab="Tab 2" class="w-inline-block w-tab-link">
                    <div>Bartender</div>
                </a>
                <a data-w-tab="Tab 3" class="w-inline-block w-tab-link">
                    <div>Kitchen</div>
                </a>
            </div>
            <div class="w-tab-content">
                <div data-w-tab="Tab 1" class="w-tab-pane w--tab-active">
                    <div class="div-block-17">
                        <div class="div-block-16">
                            <div>Monday<br></div>
                        </div>
                        <div class="div-block-16">
                            <div>Open<br>8AM - 6PM<br><br>Min Employees:6<br>‍</div>
                        </div>
                        <div class="div-block-16">
                            <div>Middle<br>12PM - 8PM<br><br>Min Employees:6</div>
                        </div>
                        <div class="div-block-16">
                            <div>End<br>4PM - 11PM<br><br>Min Employees:6</div>
                        </div>
                        <div class="div-block-16"><a href="#" class="w-button">Add Shift</a></div>
                    </div>
                </div>
                <div data-w-tab="Tab 2" class="w-tab-pane"></div>
                <div data-w-tab="Tab 3" class="w-tab-pane"></div>
            </div>
        </div>

        <div name="shifts">

            <div class="tab-content">
                <div id="front" class="tab-pane fade in active">
                    <h3>Front End</h3>
                    <p>table here</p>
                </div>
                <div id="bar" class="tab-pane fade">
                    <h3>Bar</h3>
                    <p>Some content in menu 1.</p>
                </div>
                <div id="kitchen" class="tab-pane fade">
                    <h3>Kitchen</h3>
                    <p>Some content in menu 2.</p>
                </div>
            </div>
        </div>
    </div>

</body>
</html>
