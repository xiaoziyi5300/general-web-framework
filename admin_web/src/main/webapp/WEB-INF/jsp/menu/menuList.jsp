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
    <link rel="stylesheet" href="../../css/reset.css"/>
    <link rel="stylesheet" href="../../css/index.css"/>
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.4/css/bootstrap-select.min.css">
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <title>菜单列表</title>
</head>
<body>
<div>
    <h4>
        <button id="save-btn" data-loading-text="loading..." class="btn btn-primary">新增菜单</button>
        <button id="update-btn" data-loading-text="loading..." class="btn btn-primary">修改菜单</button>
        <button id="delete-btn" data-loading-text="loading..." class="btn btn-primary">删除菜单</button>
    </h4>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>菜单名称</th>
            <th>菜单url</th>
            <th>菜单等级</th>
            <th>是否启用</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>1</td>
            <td>Anna</td>
            <td>1</td>
            <td>Anna</td>
            <td>Anna</td>
        </tr>
        <tr>
            <td>2</td>
            <td>Debbie</td>
            <td>1</td>
            <td>Anna</td>
            <td>Anna</td>
        </tr>
        <tr>
            <td>3</td>
            <td>John</td>
            <td>1</td>
            <td>Anna</td>
            <td>Anna</td>
        </tr>
        </tbody>
    </table>
    <div class="clear">
        <div class="pull-left">
            <span>共<span>20条信息</span></span>
            <span>本页显示第<span>1</span>-<span>20</span>条</span>
        </div>
        <ul class="pagination pull-right">
            <li><a href="#">&laquo;</a></li>
            <li><a href="#">1</a></li>
            <li><a href="#">2</a></li>
            <li><a href="#">3</a></li>
            <li><a href="#">4</a></li>
            <li><a href="#">5</a></li>
            <li><a href="#">&raquo;</a></li>
        </ul>
    </div>
</div>
<!-- 模态框（Modal） -->
<input type="hidden" id="menuId"/>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">模态框（Modal）标题</h4>
            </div>
            <form class="form-horizontal" role="form">
                <div class="form-group">
                    <label for="menuName" class="col-sm-3 control-label">菜单名称:</label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control" id="menuName"
                               placeholder="请输入类目名称">
                    </div>
                </div>
                <div class="form-group">
                    <label for="menuName" class="col-sm-3 control-label">是否为父节点:</label>
                    <div class="col-sm-5">
                        <label class="radio-inline">
                            <input type="radio" name="isParent" value="1" checked> 是
                        </label>
                        <label class="radio-inline">
                            <input type="radio" name="isParent" value="0"> 否
                        </label>
                    </div>
                </div>
                <div class="form-group">
                    <label for="menuName" class="col-sm-3 control-label">选择父节点:</label>
                    <div class="col-sm-6" name="parentMenu">
                        <%--<select class="selectpicker" id="selectId">
                            <option value="">请选择</option>
                            <option value="1">Mustard</option>
                            <option value="2">Ketchup</option>
                            <option value="3">Relish</option>
                        </select>--%>
                    </div>
                </div>
                <div class="form-group">
                    <label for="sort" class="col-sm-3 control-label">菜单url:</label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control" id="menuUrl"
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
                <div class="form-group">
                    <label for="menuName" class="col-sm-3 control-label">是否启用:</label>
                    <div class="col-sm-5">
                        <label class="radio-inline">
                            <input type="radio" name="isEffect" value="1" checked> 是
                        </label>
                        <label class="radio-inline">
                            <input type="radio" name="isEffect" value="0"> 否
                        </label>
                    </div>
                </div>
                <div class="form-group">
                    <label for="memo" class="col-sm-3 control-label">备注:</label>
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
<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.4/js/bootstrap-select.min.js"></script>
<script src="../../js/main.js"></script>
<script src="../../../js/menu/menu.handler.js"></script>
</body>
</html>
