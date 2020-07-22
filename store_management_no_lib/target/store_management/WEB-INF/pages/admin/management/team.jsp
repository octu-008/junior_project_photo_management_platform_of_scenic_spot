<%--
  Created by IntelliJ IDEA.
  Date: 2020/4/12
  Time: 18:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link href="/upload/pageResource/bootstrap4/css/bootstrap.min.css" rel="stylesheet"/>
    <title>管理队伍</title>
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
            margin: auto;
            height: auto;
            font-size: 15px;
        }
        .form-control {
            box-sizing: border-box;
            height: auto;
            padding: 10px;
            font-size: 20px;
        }
        .textStranded
        {
            font-size: 18px;
        }
    </style>
</head>
<body>
<div class="pl-5 pr-5 pb-5 pt-4 ">
    <div class="pt-5 pl-3 pr-3 pb-3 bg-light">
        <h3 class="text-center">管理队伍</h3>
        <div class="pl-5 pr-5">
            <hr class="my-4">
            <c:choose>
                <c:when test="${teams != null}">
            <div class="container">
                    <c:forEach var="team" items="${teams}">
                        <div class="row text-center ">
                            <div class="col textStranded">
                                队伍编号：
                            </div>
                            <div class="col textStranded">
                                    ${team.team_id}
                            </div>
                            <div class="col textStranded">
                                导游编号：
                            </div>
                            <div class="col textStranded">
                                    ${team.team_leader}
                            </div>
                        </div>
                        <div class="row text-center ">
                            <div class="col textStranded">
                                队员编号：
                            </div>
                            <div class="col textStranded">
                                    ${team.team_member}
                            </div>
                            <div class="col textStranded">
                                打印状态：
                            </div>
                            <div class="col textStranded">
                                    ${team.team_mark}
                            </div>
                        </div>
                        <hr class="my-4">
                        <div class="row text-center">
                            <div class="col">
                            </div>
                            <div class="col">
                            </div>
                            <div class="col">
                            </div>
                            <div class="col">
                                <a href="print_styleDelete/${team.team_id}"><button class="btn btn-dark btn-block selectButton"  type="button">删除该队伍</button></a>
                            </div>
                        </div>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <h3>暂无结果</h3>
                </c:otherwise>
            </c:choose>
                <div class="row text-center">
                    <div class="col"></div>
                    <div class="col"> <a href="./main"><button class="btn btn-dark btn-block selectButton" type="button">返回管理首页</button></a></div>
                    <div class="col"></div>
                </div>
        </div>
    </div>
</div>
</div>
</body>
</html>
