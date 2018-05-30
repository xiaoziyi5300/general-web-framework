<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2018/5/26
  Time: 15:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <jsp:include page="../shared.jsp"></jsp:include>
    <title>菜单列表</title>
</head>
<body>
<div>
    <h4>
        <button id="save-btn" data-loading-text="loading..." class="btn btn-primary">新增类目</button>
    </h4>
    <div class="testClass">
        <table id="table">
        </table>
    </div>

</div>
<!-- 模态框（Modal） -->
<input type="hidden" id="cId" attrValue=""/>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">模态框（Modal）标题</h4>
            </div>
            <form class="form-horizontal" role="form">
                <div class="form-group" style="margin-top: 16px;">
                    <label for="CategoryName" class="col-sm-3 control-label">类目名称:</label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control" id="CategoryName"
                               placeholder="请输入类目名称">
                    </div>
                </div>
                <div class="form-group" id="pc">
                    <label for="CategoryName" class="col-sm-3 control-label">选择父节点:</label>
                    <div class="col-sm-6" name="parentMenu">
                        <%--  <select class="selectpicker" id="selectId">
                              <option value="">请选择</option>
                              <option value="1">Mustard</option>
                              <option value="2">Ketchup</option>
                              <option value="3">Relish</option>
                          </select>--%>
                    </div>
                </div>
                <div class="form-group">
                    <label for="sort" class="col-sm-3 control-label">类目Level:</label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control" id="categoryLevel"
                               placeholder="请输入">
                    </div>
                </div>
                <div class="form-group">
                    <label for="sort" class="col-sm-3 control-label">排序:</label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control" id="sort"
                               placeholder="请输入">
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
<script src="../../../js/productCategory/productCategory.handler.js"></script>
<script type="text/javascript">
</script>
</body>
</html>
