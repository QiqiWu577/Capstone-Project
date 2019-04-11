<!DOCTYPE html>
<html>

<head>
    <title>Login</title>
    <link href="./css/style.css" rel="stylesheet" type="text/css">
</head>

<body>
<div class="header-container">
</div>
<div class="box">
    <h1>New Password</h1>
    <form action="<%=application.getContextPath() %>/Validate?action=new" method="POST">
        <input type="text" name="username" placeholder="Username" class="field" required><br/>

        <input type="password" name="oldPassword" placeholder="Current Password" class="field" required><br/>
        <input type="password" name="password" placeholder="New Password" class="field" required><br/>
        <input type="password" name="passwordMatch" placeholder="Retype New Password" class="field" required><br/>
        <input type="submit" value="Submit" class="btn"><br/>
    </form>
</div>
</body>

</html>

