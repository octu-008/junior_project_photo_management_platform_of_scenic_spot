<%--
  Created by IntelliJ IDEA.
  Date: 2020/4/15
  Time: 0:03
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
    <title>管理打印风格页面</title>
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
        <h3 class="text-center">管理打印风格页面</h3>
        <div class="pl-5 pr-5">
            <hr class="my-4">
            <c:choose>
                <c:when test="${print_styles != null}">
            <div class="container">
                    <c:forEach var="print_style" items="${print_styles}">
                        <div class="row text-center ">
                            <div class="col textStranded">
                                打印风格编号：
                            </div>
                            <div class="col textStranded">
                                    ${print_style.style_id}
                            </div>
                            <div class="col textStranded">
                                打印风格名称：
                            </div>
                            <div class="col textStranded">
                                    ${print_style.style_name}
                            </div>
                        </div>
                        <div class="row text-center ">
                            <div class="col textStranded">
                                打印风格说明：
                            </div>
                            <div class="col textStranded">
                                    ${print_style.style_state}
                            </div>
                        </div>
                        <div class="row text-center ">
                            <div class="col textStranded">
                                所属景区：
                            </div>
                            <div class="col textStranded">
                                    ${print_style.style_spot}
                            </div>
                            <div class="col textStranded">
                                花费：
                            </div>
                            <div class="col textStranded">
                                    ${print_style.style_cost}
                            </div>
                        </div>
                        <hr class="my-4">
                        <div class="row text-center">
                            <div class="col">
                            </div>
                            <div class="col">
                            </div>
                            <div class="col">
                                <a href="print_style/${print_style.style_id}"><button class="btn btn-dark btn-block selectButton" type="button">修改打印风格</button> </a>
                            </div>
                            <div class="col">
                                <a href="print_styleDelete/${print_style.style_id}"><button class="btn btn-dark btn-block selectButton" type="button">删除该打印风格</button></a>
                            </div>
                        </div>
                    </c:forEach>
                <hr class="my-4">
                <div class="pl-5 pr-5 ">
                    <div class="row">
                        <div class="col"><a class="btn btn-dark btn-block selectButton text-center" href="print_style/preNew"><button type="button">新增打印风格</button></a></div>
                        <div class="col"></div>
                        <div class="col"></div>
                        <div class="col"><a href="main"><button class="btn btn-dark btn-block selectButton text-center"  type="button">返回管理首页</button></a></div>
                    </div>
                </div>
                </c:when>
                <c:when test="${print_styleUpdate != null}">
                <div class="container pl-5 pr-5">
                    <h3>修改</h3>
                    <hr class="my-4">
                    <form action="update" method="post">
                        <div class="row">
                            <div class="col"></div>
                            <div class="col text-right textStranded">打印风格编号：</div>
                            <div class="col text-left"><input class="form-control" type="text" name="style_id" value="${print_styleUpdate.style_id}"/><br/></div>
                            <div class="col"></div>
                        </div>
                        <div class="row">
                            <div class="col"></div>
                            <div class="col text-right textStranded">打印风格名称：</div>
                            <div class="col text-left"><input class="form-control" type="text" name="style_name" value="${print_styleUpdate.style_name}"/></div>
                            <div class="col"></div>
                        </div>
                        <div class="row">
                            <div class="col"></div>
                            <div class="col text-right textStranded">打印风格描述：</div>
                            <div class="col text-left"><input class="form-control" type="text" name="style_state" value="${print_styleUpdate.style_state}"/></div>
                            <div class="col"></div>
                        </div>
                        <div class="row">
                            <div class="col"></div>
                            <div class="col text-right textStranded">打印风格景点：</div>
                            <div class="col text-left"><input class="form-control" type="text" name="style_spot" value="${print_styleUpdate.style_spot}"/></div>
                            <div class="col"></div>
                        </div>
                        <div class="row">
                            <div class="col"></div>
                            <div class="col text-right textStranded">打印风格花费：</div>
                            <div class="col text-left"><input class="form-control" type="text" name="style_cost" value="${print_styleUpdate.style_cost}"></div>
                            <div class="col"></div>
                        </div>
                        <div class="row">
                            <div class="col"></div>
                            <div class="col"> <button class="btn btn-dark selectButton" type="submit">提交修改</button></div>
                            <div class="col"></div>
                        </div>
                        <hr class="my-4">
                        <div class="col"></div>
                        <div class="col"><a href="../print_style"><button class="btn btn-dark selectButton" type="button">取消修改</button> </a></div>
                        <div class="col"></div>
                        <div class="row">
                        </div>
                    </form>
                </div>
                </c:when>
                <c:otherwise>
                <div class="container pl-5 pr-5">
                    <h3>新增打印风格</h3>
                    <hr class="my-4">
                    <form action="new" method="post">
                        <div class="row">
                            <div class="col"></div>
                            <div class="col text-right textStranded">打印风格编号：</div>
                            <div class="col text-left"><input class="form-control" type="text" name="style_id"/></div>
                            <div class="col"></div>
                        </div>
                        <div class="row">
                            <div class="col"></div>
                            <div class="col text-right textStranded">打印风格名称：</div>
                            <div class="col text-left"><input class="form-control" type="text" name="style_name"/></div>
                            <div class="col"></div>
                        </div>
                        <div class="row">
                            <div class="col"></div>
                            <div class="col text-right textStranded">打印风格描述：</div>
                            <div class="col text-left"> <input class="form-control" type="text" name="style_state"/></div>
                            <div class="col"></div>
                        </div>
                        <div class="row">
                            <div class="col"></div>
                            <div class="col text-right textStranded">打印风格景点：</div>
                            <div class="col text-left"><input class="form-control" type="text" name="style_spot" /></div>
                            <div class="col"></div>
                        </div>
                        <div class="row">
                            <div class="col"></div>
                            <div class="col text-right textStranded">打印风格花费：</div>
                            <div class="col text-left"> <input class="form-control" type="text" name="style_cost"/></div>
                            <div class="col"></div>
                        </div>
                        <hr class="my-4">
                        <div class="row">
                            <div class="col"></div>
                            <div class="col"><button class="btn btn-dark selectButton" type="submit">提交新增</button></div>
                            <div class="col"></div>
                        </div>
                        <div class="row">
                            <div class="col"></div>
                            <div class="col"><hr class="my-4"></div>
                            <div class="col"></div>
                        </div>
                        <div class="row">
                            <div class="col"></div>
                            <div class="col"> <a href="../print_style"><button class="btn btn-dark selectButton" type="button">取消修改</button> </a></div>
                            <div class="col"></div>
                        </div>

                    </form>
                </div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>
</div>
</body>
</html>
