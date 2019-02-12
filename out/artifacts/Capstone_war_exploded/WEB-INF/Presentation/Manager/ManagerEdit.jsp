<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019-01-22
  Time: 6:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html data-wf-page="5c084e5e4714bf77d896bc2d" data-wf-site="5bfd62d9457454d30221aa10">
<head>
    <meta charset="utf-8">
    <title>User Management</title>
    <meta content="User Management" property="og:title">
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
</head>
<body class="body-2">
<div data-collapse="tiny" data-animation="over-left" data-duration="400" class="navbar-3 w-nav">
    <div class="container-3 w-container">
        <a href="#" class="brand w-nav-brand">
            <div class="div-block-4"><img src="images/buble-tea.png" width="111" alt="" class="w-hidden-tiny"></div>
        </a>
        <nav role="navigation" class="w-nav-menu"><a href="#" class="nav-links selected w-nav-link">User Management</a><a href="#" class="nav-links w-nav-link">Backup</a><a href="#" class="nav-links w-nav-link">Restore</a></nav>
        <div class="menu-button w-nav-button"><img src="images/Hamburger_icon.svg.png" alt="" class="image-3"></div>
    </div>
</div>
<div class="section-4">
    <div class="w-container">
        <h1 class="heading">User Management</h1>
    </div>
    <div class="w-row">
        <div class="w-col w-col-6">
            <div class="div-block-5">
                <div class="w-form">
                    <form id="email-form-2" name="email-form-2" data-name="Email Form 2"><input type="text" class="w-input" maxlength="256" name="name-2" data-name="Name 2" placeholder="Search" id="name-2"></form>
                    <div class="w-form-done">
                        <div>Thank you! Your submission has been received!</div>
                    </div>
                    <div class="w-form-fail">
                        <div>Oops! Something went wrong while submitting the form.</div>
                    </div>
                </div>
                <div class="columns-2 w-row">
                    <div class="resultcolumn w-col w-col-4">
                        <div class="columnheading">
                            <div>ID</div>
                        </div>
                        <div class="columnblock">
                            <div>001</div>
                        </div>
                        <div class="columnblock2">
                            <div>002</div>
                        </div>
                        <div class="columnblock">
                            <div>003</div>
                        </div>
                        <div class="columnblock2">
                            <div>004</div>
                        </div>
                        <div class="columnblock">
                            <div>005</div>
                        </div>
                    </div>
                    <div class="resultcolumn w-col w-col-4">
                        <div class="columnheading">
                            <div>Name</div>
                        </div>
                        <div class="columnblock">
                            <div>Alec Gralewski</div>
                        </div>
                        <div class="columnblock2">
                            <div>Matthew Kellerman</div>
                        </div>
                        <div class="columnblock">
                            <div>Jason Sy</div>
                        </div>
                        <div class="columnblock2">
                            <div>James Finch</div>
                        </div>
                        <div class="columnblock">
                            <div>Alex Gralewski</div>
                        </div>
                    </div>
                    <div class="resultcolumn w-col w-col-4">
                        <div class="columnheading">
                            <div>Position</div>
                        </div>
                        <div class="columnblock">
                            <div>Bartender</div>
                        </div>
                        <div class="columnblock2">
                            <div>Server</div>
                        </div>
                        <div class="columnblock">
                            <div>Kitchen</div>
                        </div>
                        <div class="columnblock2">
                            <div>Server</div>
                        </div>
                        <div class="columnblock">
                            <div>Server</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="column-2 w-col w-col-6">
            <div class="w-row">
                <div class="column-3 w-col w-col-6">
                    <div class="w-form">
                        <form id="email-form" name="email-form" data-name="Email Form" class="form-2"><label for="name">ID</label><input type="text" id="name" name="name" data-name="Name" maxlength="256" class="w-input"><label for="email">First Name</label><input type="email" class="w-input" maxlength="256" name="email" data-name="Email" placeholder="John" id="email" required=""><label for="email-2">Last Name</label><input type="text" class="w-input" maxlength="256" name="field" data-name="Field" placeholder="Smith" id="field" required=""><label for="email-2">Type</label><select id="field-5" name="field-5" class="w-select"><option value="">Select one...</option><option value="First">Admin</option><option value="Second">Manager</option><option value="Third">Employee</option></select></form>
                        <div class="w-form-done">
                            <div>Thank you! Your submission has been received!</div>
                        </div>
                        <div class="w-form-fail">
                            <div>Oops! Something went wrong while submitting the form.</div>
                        </div>
                    </div>
                </div>
                <div class="w-col w-col-6">
                    <div class="w-form">
                        <form id="email-form" name="email-form" data-name="Email Form"><label for="name-2">Email Address</label><input type="email" class="w-input" maxlength="256" name="field-2" data-name="Field 2" placeholder="example@company.com" id="field-2" required=""><label for="email-2">Address</label><input type="text" class="w-input" maxlength="256" name="field-3" data-name="Field 3" placeholder="123 Example Drive" id="field-3" required=""><label for="email-2">Phone Number</label><input type="text" class="w-input" maxlength="256" name="field-3" data-name="Field 3" placeholder="###-###-####" id="field-3" required=""></form>
                        <div class="w-form-done">
                            <div>Thank you! Your submission has been received!</div>
                        </div>
                        <div class="w-form-fail">
                            <div>Oops! Something went wrong while submitting the form.</div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="w-form">
                <form id="email-form-3" name="email-form-3" data-name="Email Form 3"><label>Comments:</label><textarea id="field-4" name="field-4" placeholder="Example Text" maxlength="5000" class="w-input"></textarea></form>
                <div class="w-form-done">
                    <div>Thank you! Your submission has been received!</div>
                </div>
                <div class="w-form-fail">
                    <div>Oops! Something went wrong while submitting the form.</div>
                </div>
            </div>
            <div class="container-4 w-container"><a href="#" class="button w-button">Save</a><a href="#" class="button w-button">Clear</a><a href="#" class="button w-button">Delete</a></div>
        </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.min.js" type="text/javascript" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>
<script src="js/webflow.js" type="text/javascript"></script>
<!-- [if lte IE 9]><script src="https://cdnjs.cloudflare.com/ajax/libs/placeholders/3.0.2/placeholders.min.js"></script><![endif] -->
</body>
</html>
