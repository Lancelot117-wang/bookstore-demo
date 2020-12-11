<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>注册</title>
    <link rel="stylesheet" type="text/css" href="css/public.css"/>
    <link rel="stylesheet" type="text/css" href="css/login.css"/>
    <script src="js/jquery-1.12.4.min.js"></script>
    <script src="js/Calendar.js"></script>
    <script src="js/function.js"></script>
    
    <style>
        .reg p .error{
        	display:inline-block;
        	border:1px solid #ff855a;
        	background-color:#ffe8e0;
        	height:25px;
        	line-height:25px;
        	margin-left:20px;
        	padding:0 10px;
        }
    </style>
</head>
<body><!-------------------reg-------------------------->
<div class="reg">
    <form action="register" method="post" onsubmit="checkForm(this)"><h1><a href="index.html"><img src="img/temp/logo.png"></a></h1>
        <p>用户注册</p>
        <p><input type="text" name="username" value="" onfocus="FocusItem(this)" onblur="CheckItem(this)" placeholder="请输入用户名"><span></span></p>
        <p><input type="text" name="name" value="" onfocus="FocusItem(this)" onblur="CheckItem(this)" placeholder="请输入姓名"><span></span></p>
        <p><input type="text" name="password" value="" onfocus="FocusItem(this)" onblur="CheckItem(this)" placeholder="请输入密码"><span></span></p>
        <p><input type="text" name="rePassword" value="" onfocus="FocusItem(this)" onblur="CheckItem(this)" placeholder="请确认密码"><span></span></p>
        <p>
            <input style="width:25px;height:15px;" type="radio" name="sex" value="T" checked="checked">男
            <input style="width:25px;height:15px;" type="radio" name="sex" value="F">女
        </p>
        <p><input type="text" name="birthday" value="" onclick="new Calendar().show(this)" placeholder="请输入出生日期" readonly><span></span></p>
        <p><input type="text" name="email" value="" placeholder="请输入邮箱"><span></span></p>
        <p><input type="text" name="mobile" value="" placeholder="请输入手机号码"><span></span></p>
        <p><input type="text" name="address" value="" placeholder="请确认送货地址"><span></span></p>
        <p><input class="code" type="text" name="verifyCode" value="" onfocus="FocusItem(this)" onblur="CheckItem(this)" placeholder="验证码"><img style="margin-left:10px;height:25px;"
                src="getCode" alt="看不清，换一张" onclick="change(this)"><span></span></p>
        <p><input type="submit" name="" value="注册"></p>
        <p class="txtL txt">完成此注册，即表明您同意了我们的<a href="#">
            <使用条款和隐私策略>
        </a></p>
        <p class="txt"><a href="#"><span></span>已有账号登录</a></p>
        <!--<a href="#" class="off"><img src="img/temp/off.png"></a>--></form>
</div>
</body>
</html>