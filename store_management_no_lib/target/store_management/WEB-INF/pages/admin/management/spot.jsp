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
    <title>管理景区</title>
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
        <h3 class="text-center">管理景区</h3>
        <div class="pl-5 pr-5">
            <hr class="my-4">
            <c:choose>
            <div class="container">
                <c:when test="${spot_local != null}">
                    <form action="spot/update" method="post">
                    <div class="row text-center ">
                        <div class="col textStranded">
                            景区编号：
                        </div>
                        <div class="col textStranded">
                            <input type="text" name="spot_id" value="${spot_local.spot_id}">
                        </div>
                        <div class="col textStranded">
                            景区名称：
                        </div>
                        <div class="col textStranded">
                            <input type="text" name="spot_name" value="${spot_local.spot_name}">
                        </div>
                    </div>
                        <div class="row text-center">
                            <div class="col textStranded">
                                景区描述：
                            </div>
                            <div class="col textStranded">
                                <input type="text" name="spot_state" value="${spot_local.spot_state}"><br/>
                            </div>
                        </div>
                        <div class="row text-center">
                            <div class="col"></div>
                            <div class="col"></div>
                            <div class="col"></div>
                            <div class="col"><button class="btn btn-dark btn-block selectButton" type="submit">提交修改</button></div>
                        </div>
                    </form>
                    <hr class="my-4">
                    <div class="row text-center">
                        <div class="col"></div>
                        <div class="col"><a href="spot/preNew"><button class="btn btn-dark btn-block selectButton" type="button">新增景区</button></a></div>
                        <div class="col"></div>
                    </div>
                    <div class="row text-center">
                        <div class="col"></div>
                        <div class="col"><hr class="my-4"></div>
                        <div class="col"></div>
                    </div>
                    <div class="row text-center">
                        <div class="col"></div>
                        <div class="col"><a href="main"><button class="btn btn-dark btn-block selectButton" type="button">返回管理主页</button> </a></div>
                        <div class="col"></div>
                    </div>
                </c:when>
                <c:otherwise>
                <div class="container pl-5 pr-5">
                    <h3>新增景区</h3>
                    <hr class="my-4">
                    <form action="new" method="post">
                        <div class="row">
                            <div class="col"></div>
                            <div class="col text-right textStranded"> 景区编号：</div>
                            <div class="col text-left"> <input class="form-control" type="text" name="spot_id" value="${spot_local.spot_id}"></div>
                            <div class="col"></div>
                        </div>
                        <div class="row">
                            <div class="col"></div>
                            <div class="col text-right textStranded">景区名称：</div>
                            <div class="col text-left"><input class="form-control" type="text" name="spot_name" value="${spot_local.spot_name}"></div>
                            <div class="col"></div>
                        </div>
                        <div class="row">
                            <div class="col"></div>
                            <div class="col text-right textStranded">景区描述：</div>
                            <div class="col text-left"> <input class="form-control" type="text" name="spot_state" value="${spot_local.spot_state}"></div>
                            <div class="col"></div>
                        </div>
                        <hr class="my-4">
                        <div class="row">
                            <div class="col"></div>
                            <div class="col"> <button class="btn btn-dark selectButton" type="submit">提交修改</button></div>
                            <div class="col"></div>
                        </div>
                        <div class="row">
                            <div class="col"></div>
                            <div class="col"><hr class="my-4"></div>
                            <div class="col"></div>
                        </div>
                        <div class="row">
                            <div class="col"></div>
                            <div class="col"><a href="../spot"><button class="btn btn-dark selectButton" type="button">取消新增</button> </a></div>
                            <div class="col"></div>
                        </div>
                    </form>
                </div>
                </c:otherwise>
            </div>
            </c:choose>
        </div>
    </div>
</div>
<script src="/upload/pageResource/bootstrap4/jquery-3.2.1.slim.min.js" type="text/javascript"></script>
<script src="/upload/pageResource/bootstrap4/js/bootstrap.min.js" type="text/javascript"></script>
<script src="/upload/pageResource/bootstrap4/popper.min.js" type="text/javascript"></script>
<script src="/upload/pageResource/bootstrap4/holder.min.js" type="text/javascript"></script>
</body>
</html>
