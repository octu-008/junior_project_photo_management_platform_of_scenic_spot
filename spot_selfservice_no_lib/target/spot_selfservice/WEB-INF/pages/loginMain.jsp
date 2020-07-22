<%--
  Created by IntelliJ IDEA.
  User: y
  Date: 2020/4/16
  Time: 15:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link href="/upload/pageResource/bootstrap4/css/bootstrap.min.css" rel="stylesheet"/>
    <title>自助登录页面</title>
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
<form  class="form-signin" action="user/login" method="post">
    <h1 class="h3 mb-3 font-weight-normal">请登录</h1>
    <input type="text" name="user_account" class="form-control" placeholder="游客账号" required
                autofocus>
    <input type="password" name="user_pwd" class="form-control" placeholder="游客密码" required
                >
    <button class="btn btn-lg btn-secondary btn-block" type="submit">登录</button>
    <div class="text-center">
               <h3>当前景区</h3>
               <hr class="my-4">
        ${spot_local.spot_id}
${spot_local.spot_name}
    </div>
</form>
<script src="/upload/pageResource/bootstrap4/jquery-3.2.1.slim.min.js" type="text/javascript"></script>
<script src="/upload/pageResource/bootstrap4/js/bootstrap.min.js" type="text/javascript"></script>
<script src="/upload/pageResource/bootstrap4/popper.min.js" type="text/javascript"></script>
<script src="/upload/pageResource/bootstrap4/holder.min.js" type="text/javascript"></script>
</body>
</html>
