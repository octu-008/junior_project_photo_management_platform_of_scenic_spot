<%--
  Created by IntelliJ IDEA.
  Date: 2020/4/12
  Time: 0:48
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
    <meta charset="utf-8">
    <title>景区门店后台照片管理系统</title>
    <style>
        .bd-placeholder-img {
            font-size: 1.125rem;
            text-anchor: middle;
            -webkit-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            user-select: none;
        }

        @media (min-width: 768px) {
            .bd-placeholder-img-lg {
                font-size: 3.5rem;
            }
        }
    </style>
    <link href="/upload/pageResource/css/signin.css" rel="stylesheet">
</head>
<body class="text-center">
<form class="form-signin" action="admin/main" method="post">
    <h1 class="h3 mb-3 font-weight-normal">景区门店后台照片管理</h1>
    <label for="admin_account" class="sr-only">管理员账号</label>
    <input type="text" name="admin_account" id="admin_account" class="form-control" placeholder="管理员账号" required
           autofocus/>
    <label for="admin_pwd" class="sr-only">管理员密码</label>
    <input type="password" name="admin_pwd" id="admin_pwd" class="form-control" placeholder="管理员密码" required/>
    <label for="spot_id" class="sr-only">景区编号</label>
    <input type="text" name="spot_id" id="spot_id" class="form-control" placeholder="景区编号" required/>
    <label for="store_id" class="sr-only">景区编号</label>
    <input type="text" name="store_id" id="store_id" class="form-control" placeholder="门店编号" required/>
    <hr class="my-4">
    <button class="btn btn-lg btn-secondary btn-block" type="submit">登录</button>
</form>
<script src="/upload/pageResource/bootstrap4/jquery-3.2.1.slim.min.js" type="text/javascript"></script>
<script src="/upload/pageResource/bootstrap4/js/bootstrap.min.js" type="text/javascript"></script>
<script src="/upload/pageResource/bootstrap4/popper.min.js" type="text/javascript"></script>
<script src="/upload/pageResource/bootstrap4/holder.min.js" type="text/javascript"></script>
</body>
</html>
