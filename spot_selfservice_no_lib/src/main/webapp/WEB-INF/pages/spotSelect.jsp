
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
    <title>景区选择页面</title>
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
<div class="pl-5 pr-5 pb-5 pt-5 ">
    <div class="pt-5 pl-1 pr-1 pb-3 bg-light">
        <hr class="my-4">
        <div class="pl-5 pr-5 pb-2 ">
            <div class="pl-5 pr-5 pt-1 pb-2 text-base border">
                <p class="text-center display-4">景区列表</p>
                <hr class="my-4">
                <div class="container">
                    <c:choose>
                        <c:when test="${spots != null }">
                            <c:forEach var="spot" items="${spots}">
                                <div class="row">
                                    <div class="col">
                                        景区编号：
                                    </div>
                                    <div class="col">
                                            ${spot.spot_id}
                                    </div>
                                    <div class="col">
                                        景区名称：
                                    </div>
                                    <div class="col">
                                            ${spot.spot_name}
                                    </div>
                                </div>
                                <div class="row pl-5 pr-5">
                                        ${spot.spot_state}
                                </div>
                                <hr class="my-4">
                                <div class="text-center">
                                    <a href="spot/${spot.spot_id}"><button class="btn btn-dark" type="button">选择此景区</button></a>
                                </div>
                                <hr class="my-4">
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <h3>暂无景区</h3>
                        </c:otherwise>
                    </c:choose>
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
