<%--
  Created by IntelliJ IDEA.
  Date: 2020/4/12
  Time: 15:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% pageContext.setAttribute("APP_PATH", request.getContextPath()); %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link href="/upload/pageResource/bootstrap4/css/bootstrap.min.css" rel="stylesheet"/>
    <title>搜索导游队伍</title>
    <style>
        body {
            align-items: center;
            padding-top: 40px;
            padding-bottom: 40px;
            background-color: #f5f5f5;
        }

        .leaderAccount {
            font-size: 20px;
        }

        .form-signin {
            width: 100%;
            max-width: 330px;
            padding: 15px;
            margin: auto;
        }

        .form-signin .form-control {
            position: relative;
            box-sizing: border-box;
            height: auto;
            padding: 10px;
            font-size: 20px;
        }

        #backButton {
            width: 100%;
            max-width: 300px;
            padding: 15px;
            margin: auto;
            height: auto;
        }

        .teamMessage {
            font-size: 20px;
            padding: 15px;
        }
    </style>
</head>
<body>
<div class="pl-5 pr-5 pb-5 pt-4 ">
    <div class="pt-5 pl-3 pr-3 pb-3 bg-light">
        <h3 class="text-center">搜寻队伍</h3>
        <div class="pl-5 pr-5">
            <hr class="my-4">
        </div>
        <div class="pl-5 pr-5">
            <div class="pl-1">
                <div class="container ">
                    <div class="row">
                        <div class="col">
                            <form class="form-signin text-center" action="leaderSearch" method="post">
                                <p id="leaderAccount">
                                    <input type="text" name="team_leader" class="form-control leaderAccount "
                                           placeholder="导游账号"/>
                                <hr class="my-4">
                                <button class="btn btn-dark btn-block" type="submit">搜索</button>
                                </p>
                            </form>
                            <div class="form-signin">
                                <hr class="my-4">
                                <a href="cancelPrint">
                                    <button id="backButton" class="btn btn-dark btn-block" type="button">返回管理员主页
                                    </button>
                                </a>
                            </div>
                        </div>
                        <div class="col teamMessage">
                            <c:choose>
                                <c:when test="${teamResult != null}">
                                    <h3>结果</h3>
                                    <hr class="my-4">
                                    <div class="container">
                                        <div class="row">
                                            <div class="col">
                                                队伍编号:
                                            </div>
                                            <div class="col">
                                                    ${teamResult.team_id}
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col">
                                                导游编号:
                                            </div>
                                            <div class="col">
                                                    ${teamResult.team_leader}
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col">
                                                队伍成员:
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col">
                                                    ${teamResult.team_member}
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col">
                                                打印情况:
                                            </div>
                                            <div class="col">
                                                <c:choose>
                                                    <c:when test="${teamResult.team_mark == 0}">
                                                        未打印
                                                    </c:when>
                                                    <c:otherwise>
                                                        已打印
                                                    </c:otherwise>
                                                </c:choose>
                                            </div>
                                        </div>
                                    </div>
                                    <hr class="my-4">
                                    <div class="form-signin">
                                        <a href="prepareToPrint">
                                            <button class="btn btn-dark btn-block" type="button">前往打印</button>
                                        </a>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <h3>无结果</h3>
                                    <hr class="my-4">
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </div>
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
