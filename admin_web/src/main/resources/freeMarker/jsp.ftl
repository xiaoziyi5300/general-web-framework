<%--
  Created by IntelliJ IDEA.
  User: ${author}
  Date: ${date}
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <jsp:include page="../shared.jsp"></jsp:include>
    <title>xx列表</title>
</head>
<body>
<div>
    <h4>
        <button id="save-btn" data-loading-text="loading..." class="btn btn-primary">新增xx</button>
        <button id="update-btn" data-loading-text="loading..." class="btn btn-primary">修改xx</button>
        <button id="delete-btn" data-loading-text="loading..." class="btn btn-primary">删除xx</button>
    </h4>
    <div class="testClass">
        <table id="${beanName}_table">
        </table>
    </div>

</div>
<!-- 模态框（Modal） -->
<input type="hidden" id="cId"/>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">模态框（Modal）标题</h4>
            </div>
            <form class="form-horizontal" role="form">
                <div class="form-group" style="margin-top: 16px;">
                    <label for="RoleName" class="col-sm-3 control-label">xx名称:</label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control" id="RoleName"
                               placeholder="请输入类目名称">
                    </div>
                </div>
                <div class="form-group">
                    <label for="RoleName" class="col-sm-3 control-label">xx:</label>
                    <div class="col-sm-5">
                        <textarea class="form-control" rows="3" id="memo"></textarea>
                    </div>
                </div>
            </form>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="save">提交更改</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<script src="../../../js/${beanName}/${beanName}.handler.js"></script>
</body>
</html>