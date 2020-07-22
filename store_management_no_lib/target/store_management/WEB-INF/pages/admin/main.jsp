<%--
  Created by IntelliJ IDEA.
  Date: 2020/4/12
  Time: 15:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<% pageContext.setAttribute("APP_PATH", request.getContextPath()); %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link href="/upload/pageResource/bootstrap4/css/bootstrap.min.css" rel="stylesheet"/>
    <title>管理员主页</title>
    <style>
        body {
            align-items: center;
            padding-top: 40px;
            padding-bottom: 40px;
            background-color: #f5f5f5;
        }

        #adminInfo {
            font-size: 25px;
        }

        .headMessage {
            font-size: 30px;
        }
    </style>
</head>
<body>

<div class="pl-5 pr-5 pb-5 pt-4 ">
    <div class="pt-5 pl-3 pr-3 pb-3 bg-light">
        <h3 class="text-center">管理员主页</h3>
        <div class="pl-5 pr-5">
            <hr class="my-4">
        </div>
        <div class="pl-5 pr-5">
            <div class="pl-1">
                <div><p class="headMessage">管理员信息</p></div>
                <hr class="my-4">
                <div id="adminInfo" class="container pl-2 pr-2">
                    <div class="row">
                        <div class="col-3 pr-2 border-right"><p class="text-right">账户:</p></div>
                        <div class="col-3 border-right"><p>${admin_online.admin_account}</p></div>
                        <div class="col-3"></div>
                        <div class="col-3"></div>
                    </div>
                    <div class="row">
                        <div class="col-3 pr-2 border-right"><p class="text-right">隶属景区编号:</p></div>
                        <div class="col-3 border-right"><p>${admin_online.spot_id}</p></div>
                        <div class="col-3 pr-2 border-right"><p class="text-right">隶属门店编号:</p></div>
                        <div class="col-3"><p>${admin_online.store_id}</p></div>
                    </div>
                </div>
                <hr class="my-4">
                <div class="container pl-2 pr-2">
                    <div><p class="headMessage">功能</p></div>
                    <hr class="my-4">
                    <div class="row">
                        <div class="col text-right"></div>
                        <div class="col text-center border-left">
                            <a href="photoPrint/leaderSearchStart">
                                <button class="btn-lg btn-dark" type="button">搜索导游账号</button>
                            </a></div>
                        <div class="col text-center border-right">
                            <a href="management/main">
                                <button class="btn-lg btn-dark" type="button">前往管理主页</button>
                            </a></div>
                        <div class="col text-right"></div>
                    </div>
                </div>
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
