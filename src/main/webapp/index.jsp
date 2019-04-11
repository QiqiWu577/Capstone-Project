<!DOCTYPE html>
<html>

<head>
    <title>Login</title>
    <link href="css/style.css" rel="stylesheet" type="text/css">
</head>

<body>
<div class="header-container">
</div>
<div class="box">
    <img src="./images/2030.png" alt="2030" height="250" width="250">
        <form action="<%=application.getContextPath() %>/Validate" method="POST">

        <input type="text" name="username" placeholder="Username" class="field" required autofocus><br/>
        <input type="password" name="password" placeholder="Password" class="field" required><br/>
        <input type="submit" value="Login" class="btn"><br/>
    </form>
    <a href="<%=application.getContextPath() %>/Validate?action=forgot" class="forgot">Forgot Password</a>
     </br>
    ${requestScope.message}
</div>
</body>

</html>
