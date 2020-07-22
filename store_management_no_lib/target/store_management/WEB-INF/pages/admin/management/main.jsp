<%--
  Created by IntelliJ IDEA.
  Date: 2020/4/12
  Time: 18:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link href="/upload/pageResource/bootstrap4/css/bootstrap.min.css" rel="stylesheet"/>
    <title>运营管理</title>
    <style>
        body {
            align-items: center;
            padding-top: 40px;
            padding-bottom: 40px;
            background-color: #f5f5f5;
        }
        .selectButton {
            width: 100%;
            max-width: 300px;
            padding: 15px;
            margin: auto;
            height: auto;
            font-size: 20px;
        }
    </style>
</head>
<body>
<div class="pl-5 pr-5 pb-5 pt-4 ">
            <div class="pt-5 pl-3 pr-3 pb-3 bg-light">
                <h3 class="text-center">运营管理</h3>
                <div class="pl-5 pr-5">
                    <hr class="my-4">
            <div class="container">
                <div class="row">
                    <div class="col text-center">
                        <a href="admin"><button class="btn btn-dark selectButton" type="button">管理员账号管理</button></a>
                    </div>
                    <div class="col text-center">
                        <a href="photo"><button class="btn btn-dark selectButton" type="button">照片管理</button></a>
                    </div>
                </div>
                <hr class="my-4">
                <div class="row">
                    <div class="col text-center">
                        <a href="user"><button class="btn btn-dark selectButton" type="button">用户账号管理</button></a>
                    </div>
                    <div class="col text-center">
                        <a href="spot"><button class="btn btn-dark selectButton" type="button">景区信息管理</button></a>
                    </div>
                </div>
                <hr class="my-4">
                <div class="row">
                    <div class="col text-center">
                        <a href="print_style"><button class="btn btn-dark selectButton" type="button">打印风格管理</button></a>
                    </div>
                    <div class="col text-center">
                        <a href="team"><button class="btn btn-dark selectButton" type="button">导游队伍管理</button></a>
                    </div>
                </div>
                <hr class="my-4">
            </div>
            <div class="text-center">
                <a href="../main"><button class="btn btn-dark selectButton" type="button">返回管理员首页</button> </a>
            </div>
        </div>
    </div>
</div>
<script src="/upload/pageResource/bootstrap4/jquery-3.2.1.slim.min.js" type="text/javascript"></script>
<script src="/upload/pageResource/bootstrap4/js/bootstrap.min.js" type="text/javascript"></script>
<script src="/upload/pageResource/bootstrap4/popper.min.js" type="text/javascript"></script>
<script src="/upload/pageResource/bootstrap4/holder.min.js" type="text/javascript"></script>
</body>
</html>
