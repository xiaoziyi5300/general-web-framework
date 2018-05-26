<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2018/5/5
  Time: 11:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="../../css/reset.css"/>
    <link rel="stylesheet" href="../../css/index.css"/>
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
    <title>M商城后台</title>
</head>
<body>
<div class="warper">
    <header>
        <div class="pull-left header">
            <span class="glyphicon glyphicon-th-large "></span>
        </div>
        <p class="pull-left header-text">后台管理页面</p>
        <div class='pull-right right50'>
            <div class="dropdown">
                <span class="settings dropdown-toggle" id="dropdownMenu1"
                      data-toggle="dropdown">
                  settings
                  <span class="caret"></span>
            </span>
                <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
                    <li role="presentation">
                        <a role="menuitem" tabindex="-1" href="#">关于我们</a>
                    </li>
                    <li role="presentation">
                        <a role="menuitem" tabindex="-1" href="#">项目仓库</a>
                    </li>
                    <li role="presentation">
                        <a role="menuitem" tabindex="-1" href="#">退出登录</a>
                    </li>
                </ul>
            </div>
        </div>
    </header>
    <aside>
        <ul class="orderList">
            <li class="noneList" data-text="新增类目" data-url="catepage.html">
                <div class="noneicon">
                    <span class="glyphicon glyphicon-home"></span><span>新增类目</span>
                </div>
            </li>
            <li class="noneList" data-text="产品列表" data-url="productlist.html">
                <div class="noneicon">
                    <span class="glyphicon glyphicon-home"></span><span>产品列表</span>
                </div>
            </li>
            <li class="hasList">
                <div class="hasicon">
                    <span class="glyphicon glyphicon-home"></span><span>菜单1</span><span
                        class="glyphicon pull-right glyphicon-chevron-down cur"></span>
                </div>
                <ul class="menu">
                    <li class="active" data-text="菜单2" data-url="http://www.baidu.com">菜单2</li>
                    <li data-text="菜单3" data-url="http://www.sina.com">菜单3</li>
                    <li data-text="菜单4" data-url="http://www.zuma.com">菜单4</li>
                    <li data-text="菜单5" data-url="http://www.baidu.com">菜单5</li>
                    <li data-text="菜单6" data-url="http://www.baidu.com">菜单6</li>
                </ul>
            </li>
            <li class="hasList">
                <div class="hasicon">
                    <span class="glyphicon glyphicon-home"></span><span>菜单1</span><span
                        class="glyphicon pull-right glyphicon-chevron-down cur"></span>
                </div>
                <ul class="menu">
                    <li data-text="菜单7" data-url="http://www.sina.com">菜单7</li>
                    <li data-text="菜单8" data-url="http://www.zuma.com">菜单8</li>
                </ul>
            </li>
            <li class="noneList" data-text="测试" data-url="http://www.baidu.com">
                <div class="noneicon">
                    <span class="glyphicon glyphicon-home"></span><span>测试</span>
                </div>
            </li>
        </ul>

    </aside>
    <main>
        <!-- 选项卡 -->
        <div style="width:100%;padding-right:50px;">
            <ul class="tabslist clear">
                <li class="tags-li active"><a href="#" class="tags-li-title">新增类目</a><span
                        class="glyphicon glyphicon-remove iconClose"></span></li>
            </ul>
            <!-- iframe容器 -->
            <div class="content">
                <h4>新增类目</h4>
                <div class="ifamecontainer">
                    <div class="show">
                        <iframe src="catepage.html" frameborder="0" border='0'></iframe>
                    </div>
                </div>
            </div>
        </div>

    </main>

</div>
<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="../../js/main.js"></script>
</body>
</html>