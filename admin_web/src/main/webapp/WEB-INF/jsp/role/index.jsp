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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/ztree/css/zTreeStyle/zTreeStyle.css"
          type="text/css">
    <title>角色列表</title>
</head>
<body>
<div>
    <h4>
        <button id="save-btn" data-loading-text="loading..." class="btn btn-primary">新增角色</button>
    </h4>
    <div class="testClass">
        <table id="role_table">
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
                    <label for="CategoryName" class="col-sm-3 control-label">角色名称:</label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control" id="CategoryName"
                               placeholder="请输入类目名称">
                    </div>
                </div>
                <div class="form-group">
                    <label for="CategoryName" class="col-sm-3 control-label">备注:</label>
                    <div class="col-sm-5">
                        <textarea class="form-control" rows="3" id="memo"></textarea>
                    </div>
                </div>
                <div class="form-group">
                    <label for="CategoryName" class="col-sm-3 control-label">选择菜单:</label>
                    <div style=" height:231px;width:252px; overflow-y:auto">
                        <div class="col-sm-5">
                            <ul id="treeDemo" class="ztree"></ul>
                        </div>
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
<script type="text/javascript" src="${pageContext.request.contextPath}/ztree/js/jquery.ztree.core.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/ztree/js/jquery.ztree.excheck.js"></script>
<script src="../../../js/role/role.handler.js"></script>
<script type="text/javascript">
    /*var zTreeObj;
    // zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）
    var setting = {
        check:{
            enable: true,
            autoCheckTrigger: true,
            chkStyle: "checkbox",
            chkboxType: { "Y": "ps", "N": "ps" }
        }
    };
    // zTree 的数据属性，深入使用请参考 API 文档（zTreeNode 节点数据详解）
    var zNodes = [
        {name:"test1", open:true, id:"1",isParent:true,pId:0, children:[
                {name:"test1_1",id:"2",isParent:false,pId:1}, {name:"test1_2",id:"3",isParent:false,pId:1}]},
        {name:"test2", open:true,  id:"4",isParent:true,pId:0, children:[
                {name:"test2_1"}, {name:"test2_2"}]}, {name:"test2", open:true, children:[
                {name:"test2_1"}, {name:"test2_2"}]}, {name:"test2", open:true, children:[
                {name:"test2_1"}, {name:"test2_2"}]}, {name:"test3", open:true, children:[
                {name:"test2_1"}, {name:"test2_2"}]}
    ];
    $(function(){
        zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, zNodes);
    })*/
</script>
</body>
</html>
