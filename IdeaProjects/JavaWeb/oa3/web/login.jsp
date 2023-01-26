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

<!--        <div class="header">-->
<!--&lt;!&ndash;            &lt;!&ndash; Mail text &ndash;&gt;&ndash;&gt;-->
<!--&lt;!&ndash;            <div class="mail_text">&ndash;&gt;-->
<!--&lt;!&ndash;                <span class="m_text" id="m_text">&ndash;&gt;-->
<!--&lt;!&ndash;                    <em>Mail</em>&ndash;&gt;-->
<!--&lt;!&ndash;                </span>&ndash;&gt;-->
<!--&lt;!&ndash;            </div>&ndash;&gt;-->

<!--&lt;!&ndash;            &lt;!&ndash;  Mail input  &ndash;&gt;&ndash;&gt;-->
<!--&lt;!&ndash;            <div class="mail_input">&ndash;&gt;-->
<!--&lt;!&ndash;                <span class="m_input">&ndash;&gt;-->
<!--&lt;!&ndash;                    <input type="text" class="m_in_box" id="m_in_box" name="userMail">&ndash;&gt;-->
<!--&lt;!&ndash;                </span>&ndash;&gt;-->
<!--&lt;!&ndash;            </div>&ndash;&gt;-->
<!--&lt;!&ndash;            <span class="tips" id="illMail">&ndash;&gt;-->

<!--&lt;!&ndash;            </span>&ndash;&gt;-->
<!--        </div>-->

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
<!--           Repeat the password -->
<!--            <div class="pwd_text">-->
<!--                <span class="p_text" id="rp_text">-->
<!--                    <em>Repeat Password</em>-->
<!--                </span>-->
<!--            </div>-->

<!--            &lt;!&ndash;  RePassword input  &ndash;&gt;-->
<!--            <div class="pwd_input">-->
<!--                <input type="password" class="p_in_box" id="rp_in_box"/>-->
<!--            </div>-->
<!--            <span class="tips" id="illPwd">-->
<!--            </span>-->
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