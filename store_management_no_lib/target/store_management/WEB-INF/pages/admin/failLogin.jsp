<%--
  Created by IntelliJ IDEA.
  Date: 2020/4/12
  Time: 15:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link href="/upload/pageResource/bootstrap4/css/bootstrap.min.css" rel="stylesheet"/>
    <title>登录失败</title>
    <style>
        body {
            align-items: center;
            padding-top: 40px;
            padding-bottom: 40px;
            background-color: #f5f5f5;
        }

        #failMessage {
            font-size: 30px;
        }

        #failButton {
            font-size: 20px;
        }
    </style>
</head>
<body class="text-center">

<div class="pl-5 pr-5 pb-5 pt-5 ">
    <div class="pt-5 pl-1 pr-1 pb-3 bg-light">
        <hr class="my-4">
        <div class="pl-5 pr-5 pb-2 ">
            <div class="pl-5 pr-5 pt-1 pb-2 text-base border">
                <p class="text-center display-4">登录失败</p>
                <hr class="my-4">
                <p id="failMessage" class="pt-3 pb-1 ">*${fail_msg}*</p>
                <a href="../">
                    <button id="failButton" class="btn btn-outline-secondary">返回登录页面</button>
                </a>
            </div>
        </div>
        <hr class="my-4">
    </div>
</div>
<script src="/upload/pageResource/bootstrap4/jquery-3.2.1.slim.min.js" type="text/javascript"></script>
<script src="/upload/pageResource/bootstrap4/js/bootstrap.min.js" type="text/javascript"></script>
<script src="/upload/pageResource/bootstrap4/popper.min.js" type="text/javascript"></script>
<script src="/upload/pageResource/bootstrap4/holder.min.js" type="text/javascript"></script>
</body>
</html>
