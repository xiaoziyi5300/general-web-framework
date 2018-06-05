<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/06/05
  Time: 16:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>代码生成</title>
    <jsp:include page="shared.jsp"></jsp:include>
</head>
<body>
<div class="testClass">
    <form class="form-horizontal" role="form">
        <div class="form-group" style="margin-top: 16px;">
            <label for="driaveName" class="col-sm-1 control-label">JDBCurl:</label>
            <div class="col-sm-2">
                <input type="text" class="form-control" id="jdbcUrl"
                       placeholder="请输入url">
            </div>
            <label for="driaveName" class="col-sm-1 control-label">driaveName:</label>
            <div class="col-sm-2">
                <input type="text" class="form-control" id="driaveName"
                       placeholder="请输入driaveName">
            </div>
            <label for="driaveName" class="col-sm-1 control-label">taleName:</label>
            <div class="col-sm-2">
                <input type="text" class="form-control" id="taleName"
                       placeholder="数据库表名">
            </div>
        </div>
        <div class="form-group" style="margin-top: 16px;">
            <label for="driaveName" class="col-sm-1 control-label">name:</label>
            <div class="col-sm-2">
                <input type="text" class="form-control" id="name"
                       placeholder="数据库账号">
            </div>
            <label for="driaveName" class="col-sm-1 control-label">passWord:</label>
            <div class="col-sm-2">
                <input type="text" class="form-control" id="passWord"
                       placeholder="数据库密码">
            </div>
            <label for="driaveName" class="col-sm-1 control-label">author:</label>
            <div class="col-sm-2">
                <input type="text" class="form-control" id="author"
                       placeholder="author">
            </div>
        </div>
        <div class="form-group" style="margin-top: 16px;">
            <label for="driaveName" class="col-sm-1 control-label">filePath:</label>
            <div class="col-sm-2">
                <input type="text" class="form-control" id="filePath"
                       placeholder="F:/Freemarker/src/">
            </div>
            <label for="driaveName" class="col-sm-1 control-label">beanName:</label>
            <div class="col-sm-2">
                <input type="text" class="form-control" id="beanName"
                       placeholder="bean的名称(首字母小写例如:order)">
            </div>
            <label for="driaveName" class="col-sm-1 control-label">packageName:</label>
            <div class="col-sm-2">
                <input type="text" class="form-control" id="packageName"
                       placeholder="包名">
            </div>
        </div>
    </form>
</div>
</body>
</html>
