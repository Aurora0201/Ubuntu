<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link type="text/css" rel="stylesheet" href="register.css">

</head>
<body  class="body">
<form class="shader" id="form" method="post">
    <!-- Box -->
    <div class="box">

        <script>
            window.onload = function (){
                var uok = false, mok = false, pok = false;
                //Correct userName
                document.getElementById("u_in_box").onblur = function (){
                    var user = document.getElementById("u_in_box")
                    var userReg = /^[a-zA-Z0-9]{6,14}$/
                    var ok = userReg.test(user.value.trim())
                    var text = document.getElementById("illName")
                    if(!ok){
                        user.style.borderBottom = "2px solid red"
                        text.innerText = "Illegal UserName"
                    }else{
                        user.style.borderBottom = "2px solid green"
                        text.innerText = ""
                    }
                    document.getElementById("u_text").style.color = "#000000"
                    uok = ok
                    if(pok && uok && mok) {
                        document.getElementById("signUp").style.backgroundColor = "#4bec97"
                    }else{
                        document.getElementById("signUp").style.backgroundColor = "#8A8989"
                    }
                }

                //Correct mail
                document.getElementById("m_in_box").onblur = function (){
                    var email = document.getElementById("m_in_box")
                    var emailReg = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/
                    var ok = emailReg.test(email.value.trim())
                    var text = document.getElementById("illMail")
                    if(!ok){
                        email.style.borderBottom = "2px solid red"
                        text.innerText = "Illegal Mail Address"
                    }else{
                        email.style.borderBottom = "2px solid green"
                        text.innerText = ""
                    }
                    document.getElementById("m_text").style.color = "#000000"
                    mok = ok

                    if(pok && uok && mok) {
                        document.getElementById("signUp").style.backgroundColor = "#4bec97"
                    }else{
                        document.getElementById("signUp").style.backgroundColor = "#8A8989"
                    }
                }


                document.getElementById("u_in_box").onfocus = function(){
                    document.getElementById("u_text").style.color = "#f07b2d"
                    document.getElementById("u_in_box").style.borderBottom = "1px solid #ffffff"
                }

                //
                document.getElementById("m_in_box").onfocus = function (){
                    document.getElementById("m_in_box").style.border = "1px solid #ffffff"
                    document.getElementById("m_text").style.color = "#f07b2d"
                }

                document.getElementById("p_in_box").onfocus = function (){
                    document.getElementById("p_text").style.color = "#f07b2d"
                }

                document.getElementById("rp_in_box").onfocus = function (){
                    document.getElementById("rp_text").style.color = "#f07b2d"
                    document.getElementById("p_in_box").style.borderBottom = "1px solid #ffffff"
                    document.getElementById("rp_in_box").style.borderBottom = "1px solid #ffffff"
                }

                document.getElementById("p_in_box").onblur = function (){
                    document.getElementById("p_text").style.color = "#000000"
                }

                //
                document.getElementById("rp_in_box").onblur = function (){
                    document.getElementById("rp_text").style.color = "#000000"
                }

                document.getElementById("rp_in_box").onblur = function pwd(){
                    var pwd = document.getElementById("p_in_box").value
                    var rpwd = document.getElementById("rp_in_box").value
                    var ok = true
                    reg = /^.{6,}$/
                    ok = (reg.test(pwd) && pwd.length === rpwd.length)
                    for(i = 0; i < pwd.length; i++){
                        if(pwd[i] !== rpwd[i]){
                            ok = false
                            break
                        }
                    }
                    var text = document.getElementById("illPwd")
                    if(ok){
                        document.getElementById("p_in_box").style.borderBottom = "2px solid green"
                        document.getElementById("rp_in_box").style.borderBottom = "2px solid green"
                        text.innerText = ""
                    }else{
                        document.getElementById("p_in_box").style.borderBottom = "2px solid red"
                        document.getElementById("rp_in_box").style.borderBottom = "2px solid red"
                        if(!reg.test(pwd)){
                            text.innerText = "Illegal Password"
                        }else{
                            text.innerText = "Unmatchable Password"
                        }
                    }
                    pok = ok
                    document.getElementById("rp_text").style.color = "#000000"


                    if(pok && uok && mok) {
                        document.getElementById("signUp").style.backgroundColor = "#4bec97"
                    }else{
                        document.getElementById("signUp").style.backgroundColor = "#8A8989"
                    }

                }

                document.getElementById("signUp").onclick = function () {
                    if(pok && uok && mok){
                        var form = document.getElementById("form")
                        form.method = "post"
                        form.action = ""
                        form.submit()
                    }else{
                        alert("Illegal submit, Please modify your input")
                    }
                }


            }
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

        <div class="header">
            <!-- Mail text -->
            <div class="mail_text">
                <span class="m_text" id="m_text">
                    <em>Mail</em>
                </span>
            </div>

            <!--  Mail input  -->
            <div class="mail_input">
                <span class="m_input">
                    <input type="text" class="m_in_box" id="m_in_box" name="userMail">
                </span>
            </div>
            <span class="tips" id="illMail">

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
<!--           Repeat the password -->
            <div class="pwd_text">
                <span class="p_text" id="rp_text">
                    <em>Repeat Password</em>
                </span>
            </div>

            <!--  RePassword input  -->
            <div class="pwd_input">
                <input type="password" class="p_in_box" id="rp_in_box"/>
            </div>
            <span class="tips" id="illPwd">
            </span>
        </div>
        <div class="butBox">
            <div class="but">
                <input type="button" class="button" value="Sign Up" id="signUp"/>
            </div>
            <div class="but" id="but2">
                <input type="button" class="button" value="Sign in" id="signIn" onclick="window.location = '/oa/login.jsp'"/>
            </div>
        </div>


    </div>
</form>
</body>
</html>