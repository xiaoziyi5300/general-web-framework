<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2018/5/5
  Time: 11:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>登录</title>
    <link rel="stylesheet" href="css/login.css"/>
</head>
<body>
<div class="main">
    <div>
        <h1>welcome</h1>
    </div>
    <div>
        <form>
            <input type="text" placeholder="请输入用户名" id="userName"/><br/>
            <input type="password" placeholder="请输入用密码" style="margin-top: 25px;" id="passWord"/>
        </form>
    </div>
    <div>
        <button type="button" id="login">登录</button>
    </div>
</div>
<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script type="text/javascript">
    $(function () {
        $(document).on("click", "#login", function () {
            login();
        })
    })

    function login() {
        var userName = $.trim($("#userName").val());
        var passWord = $.trim($("#passWord").val());
        if (!userName || !passWord) {
            alert("用户名密码必填");
        }
        $.ajax({
            url: "/api/home/login",
            cache: false,
            type: 'POST',
            dataType: 'json',
            data: {
                "userName": userName,
                "passWord": passWord
            },
            success: function (result) {
                if (result.status == 1) {
                    window.location.href = "/home/index";
                } else {
                    alert(result.message);
                }
            }
        });
    }
</script>
</body>
</html>

