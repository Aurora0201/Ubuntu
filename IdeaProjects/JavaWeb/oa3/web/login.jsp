<%@page session="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link type="text/css" rel="stylesheet" href="login.css">

</head>
<body  class="body">
<form class="shader" id="form" method="post" action="<%=request.getContextPath()%>/dept/login">
    <!-- Box -->
    <div class="box">

        <script>

        </script>
        <div class="header">
            <!-- UserName text -->
            <div class="mail_text">
                <span class="m_text" id="u_text">
                    <em>UserName</em>
                </span>
            </div>

            <!--  UserName input  -->
            <div class="mail_input">
                <span class="m_input">
                    <input type="text" class="m_in_box" id="u_in_box" name="userName">
                </span>
            </div>
            <span class="tips" id="illName">

            </span>
        </div>

        <div class="foot">
            <!--  Password text  -->
            <div class="pwd_text">
                <span class="p_text" id="p_text">
                    <em>Password</em>
                </span>
            </div>
            <!--  Password input  -->
            <div class="pwd_input">
                <input type="password" class="p_in_box" id="p_in_box" name="password"/>
            </div>

            <div class="noLogin">
                <input type="checkbox" id="noLogin" value="true" name="noLogin">No Login for ten days
            </div>

        </div>
        <div class="butBox">
            <div class="but">
                <input type="button" class="button" value="Sign Up" id="signUp" onclick="window.location = '/oa/register.jsp'"/>
            </div>
            <div class="but" id="but2">
                <input type="submit" class="button" value="Sign in" id="signIn" />
            </div>
        </div>


    </div>
</form>
</body>
</html>