<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  Date: 2020/4/12
  Time: 18:33
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
    <title>管理用户账号</title>
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
        <h3 class="text-center">管理用户账号</h3>
        <div class="pl-5 pr-5">
            <hr class="my-4">
            <c:choose>
                <c:when test="${users != null}">
            <div class="container">
                    <c:forEach var="user" items="${users}">
                        <div class="row text-center ">
                            <div class="col textStranded">
                                用户账号:
                            </div>
                            <div class="col textStranded">
                                    ${user.user_account}
                            </div>
                            <div class="col textStranded">
                                用户密码：
                            </div>
                            <div class="col textStranded">
                                    ${user.user_pwd}
                            </div>
                        </div>
                        <div class="row text-center ">
                            <div class="col textStranded">
                                用户类型
                            </div>
                            <div class="col textStranded">
                                <c:choose>
                                    <c:when test=" ${user.user_type ==0}">
                                        导游
                                    </c:when>
                                    <c:otherwise>
                                        游客
                                    </c:otherwise>
                                </c:choose>
                            </div>
                            <div class="col textStranded">
                                用户余额：
                            </div>
                            <div class="col textStranded">
                                    ${user.user_rest}
                            </div>
                        </div>
                        <hr class="my-4">
                        <div class="row text-center">
                            <div class="col">
                            </div>
                            <div class="col">
                            </div>
                            <div class="col">
                                <a href="user/${user.user_account}"><button class="btn btn-dark btn-block selectButton" type="button">修改用户</button></a>
                            </div>
                            <div class="col">
                                <a href="userDelete/${user.user_account}"><button class="btn btn-dark btn-block selectButton" type="button">删除用户</button></a><br/>
                            </div>
                        </div>
                    </c:forEach>
            </div>
                    <hr class="my-4">
            <div class="pl-5 pr-5 ">
                <div class="row">
                    <div class="col">
                        <a href="./main"><button class="btn btn-dark btn-block selectButton text-center" type="button">返回管理首页</button></a><br/>
                    </div>
                    <div class="col"></div>
                    <div class="col"></div>
                    <div class="col">
                        <a href="user/preNew"><button class="btn btn-dark btn-block selectButton text-center" type="button">新增用户</button></a><br/>
                    </div>
                </div>
            </div>
                </c:when>
                <c:when test="${userUpdate != null}">
            <div class="container pl-5 pr-5">
                <h3>修改</h3>
                <hr class="my-4">
                <form action="update" method="post">
                    <div class="row">
                        <div class="col"></div>
                        <div class="col text-right textStranded">用户账号：</div>
                        <div class="col text-left"><input class="form-control" type="text" name="user_account" value="${userUpdate.user_account}"/></div>
                        <div class="col"></div>
                    </div>
                    <div class="row">
                        <div class="col"></div>
                        <div class="col text-right textStranded"> 用户密码：</div>
                        <div class="col text-left"><input class="form-control" type="text" name="user_pwd" value="${userUpdate.user_pwd}"/></div>
                        <div class="col"></div>
                    </div>
                    <div class="row">
                        <div class="col"></div>
                        <div class="col text-right textStranded">用户类型：</div>
                        <div class="col text-left"><input class="form-control" type="text" name="user_type" value="${userUpdate.user_type}"/></div>
                        <div class="col"></div>
                    </div>
                    <div class="row">
                        <div class="col"></div>
                        <div class="col text-right textStranded">用户余额：</div>
                        <div class="col text-left"> <input class="form-control" type="text" name="user_rest" value="${userUpdate.user_rest}"/></div>
                        <div class="col"></div>
                    </div>
                    <hr class="my-4">
                    <div class="row">
                        <div class="col"></div>
                        <div class="col"><button class="btn btn-dark selectButton" type="submit">确认修改</button></div>
                        <div class="col"></div>
                    </div>
                    <div class="row">
                        <div class="col"></div>
                        <div class="col"><a href="../user"><button class="btn btn-dark selectButton" type="button">取消修改</button></a></div>
                        <div class="col"></div>
                    </div>
                </form>
            </div>
                </c:when>
                <c:otherwise>
            <div class="container pl-5 pr-5">
                <h3>新增用户</h3>
                <hr class="my-4">
                    <form action="new" method="post">
                        <div class="row">
                            <div class="col"></div>
                            <div class="col text-right textStranded">用户账号：</div>
                            <div class="col text-left"><input class="form-control" type="text" name="user_account"/></div>
                            <div class="col"></div>
                        </div>
                        <div class="row">
                            <div class="col"></div>
                            <div class="col text-right textStranded">用户密码：</div>
                            <div class="col text-left"><input class="form-control" type="text" name="user_pwd"/></div>
                            <div class="col"></div>
                        </div>
                        <div class="row">
                            <div class="col"></div>
                            <div class="col text-right textStranded">用户类型：</div>
                            <div class="col text-left"><input class="form-control" type="text" name="user_type"/></div>
                            <div class="col"></div>
                        </div>
                        <div class="row">
                            <div class="col"></div>
                            <div class="col text-right textStranded">用户余额：</div>
                            <div class="col text-left"><input class="form-control" type="text" name="user_rest"/></div>
                            <div class="col"></div>
                        </div>
                        <hr class="my-4">
                        <div class="row">
                            <div class="col"></div>
                            <div class="col"><button class="btn btn-dark selectButton" type="submit">确认修改</button></div>
                            <div class="col"></div>
                        </div>
                        <div class="row">
                            <div class="col"></div>
                            <div class="col"><hr class="my-4"></div>
                            <div class="col"></div>
                        </div>
                        <div class="row">
                            <div class="col"></div>
                            <div class="col"><a href="../user"><button class="btn btn-dark selectButton"  type="button">返回管理主页</button></a></div>
                            <div class="col"></div>
                        </div>
                    </form>
            </div>
                </c:otherwise>
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
