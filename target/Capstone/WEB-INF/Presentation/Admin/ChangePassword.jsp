<!DOCTYPE html>
<html>

<head>
    <title>Forgot Password</title>
    <link href="./css/style.css" rel="stylesheet" type="text/css">
</head>

<body>
<div class="header-container">
</div>
<div class="box">
    <h1>Forgot Password</h1>
    <form action="<%=application.getContextPath() %>/Validate?action=reset" method="POST">
        <input type="text" name="username" placeholder="Username" class="field" required><br/>

        <input type="submit" value="Submit" class="btn"><br/>
    </form>
</div>
</body>

</html>

