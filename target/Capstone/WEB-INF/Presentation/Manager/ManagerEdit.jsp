<!DOCTYPE html>
<html>

<head>
    <title>Home</title>
    <link href="./images/home.css" rel="stylesheet" type="text/css">
</head>

<body>
<div class="sidebar">
    <div class="logo">
        <img src="/res/2030.png" alt="2030" height="100" width="100">
    </div>
    <a href="#">Home</a>
    <a href="#">Employees</a>
</div>

<div class="main">
    <div class="topbar">
        <img src="res/61073.svg" height="35" width="35">
    </div>

    <div class="row">
        <div class="column" style="background-color:#aaa;">
            <input type="text" value="Search"><br/>
            <div class="table">
            </div>

        </div>
        <div class="column" style="background-color:#bbb;">
            <div class="row">
                <div class="column" style="background-color:#bbb;">
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
                <div class="column" style="background-color:#bbb;">
                    <label for="email">Email Address</label><br/>
                    <input type="text" id="email"><br/>
                    <label for="address">Address</label><br/>
                    <input type="text" id="address"><br/>
                    <label for="phone">Phone Number</label><br/>
                    <input type="text" id="phone"><br/>
                </div>


            </div>
            Comments:
            <textarea rows="4" cols="30">
                 hi
            </textarea>

            <input type="button" value="Save" name="save" class="btn">
            <input type="button" value="Clear" name="clear" class="btn">
            <input type="button" value="Delete" name="delete" class="btn">
        </div>
    </div>
</div>

</body>

</html>
