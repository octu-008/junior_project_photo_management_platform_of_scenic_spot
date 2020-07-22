<%--
  Created by IntelliJ IDEA.
  User: y
  Date: 2020/4/16
  Time: 15:50
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
    <title>打印列表</title>
    <style>
        body {
            align-items: center;
            padding-top: 40px;
            padding-bottom: 40px;
            background-color: #f5f5f5;
        }
    </style>
</head>
<body>
<div class="pl-5 pr-5 pb-5 pt-4 ">
    <div class="pt-5 pl-3 pr-3 pb-3 bg-light">
        <h3 class="text-center">准备打印</h3>
        <div class="pl-5 pr-5">
            <hr class="my-4">
            <div class="text-center">
                ${user_online.user_account}
            </div>
            <hr class="my-4">
            <div class="container">
                <c:forEach var="photo" items="${photos}">
                    <div class="row">
                        <div class="col text-center">
                            <img src="/${photo.pho_path}" class="img-thumbnail"/>
                        </div>
                        <div class="col">
                            <div class="container">
                                <div class="row">
                                    <div class="col">
                                        <p class="text-light bg-dark text-center"> 名称:</p>
                                    </div>
                                    <div class="col">
                                        <p>${photo.pho_name}</p>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col">
                                        <p class="text-light bg-dark text-center"> 拍摄时间:</p>
                                    </div>
                                    <div class="col">
                                        <p>${photo.pho_date}</p>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col">
                                        <p class="text-light bg-dark text-center"> 景区编号:</p>
                                    </div>
                                    <div class="col">
                                        <p>${photo.pho_spot}</p>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col">
                                        <p class="text-light bg-dark text-center"> 拍摄用户账号:</p>
                                    </div>
                                    <div class="col">
                                        <p>${photo.pho_user}</p>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col">
                                        <p class="text-light bg-dark text-center"> 上传类型:</p>
                                    </div>
                                    <div class="col">
                                        <p>上传至导游</p>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col">
                                        <p class="text-light bg-dark text-center"> 上传用户:</p>
                                    </div>
                                    <div class="col">
                                        <p>${photo.pho_user}</p>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col">
                                        <p class="text-light bg-dark text-center"> 风格类型:</p>
                                    </div>
                                    <div class="col">
                                        <p>${photo.pho_style}</p>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col">
                                        <p class="text-light bg-dark text-center"> 打印状态:</p>
                                    </div>
                                    <div class="col">
                                        <c:choose>
                                            <c:when test=" ${photo.pho_mark == 0}">
                                                未打印
                                            </c:when>
                                            <c:otherwise>
                                                已打印
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <div class="text-center">
                <a href=""><button class="btn btn-dark btn-block" type="button">全部打印</button></a>
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
