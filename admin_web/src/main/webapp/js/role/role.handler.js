var zTreeObj;
// zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）
var setting = {
    check: {
        enable: true,
        autoCheckTrigger: true,
        chkStyle: "checkbox",
        chkboxType: {"Y": "ps", "N": "ps"}
    }
};
$(function () {
    new roleFun().initData();
    //初始化菜单树
    new roleFun().selectRoleList();
    //新增
    $(document).on('click', '#save-btn', function () {
        new roleFun().openDialog();
    });
    //修改
    $(document).on('click', '#update-btn', function () {
        var _select = $("#role_table").bootstrapTable('getSelections');
        if (_select.length == 0) {
            layer.alert('未选中数据');
        } else {
            new roleFun().selectDataById(_select[0].id);
        }
    });
    //删除
    $(document).on('click', '#delete-btn', function () {
        var _select = $("#role_table").bootstrapTable('getSelections');
        if (_select.length == 0) {
            layer.alert('未选中数据');
        } else {
            deleteByIds(_select[0].id);
        }
    });

    $(document).on('click', '#save', function () {
        new roleFun().save();
    });
});
var roleFun = function () {
    //加载数据
    this.initData = function () {
        var $table;
        var queryUrl = '/api/role/queryListByPage';
        $table = $('#role_table').bootstrapTable({
                url: queryUrl,
                method: 'post',
                pagination: true,                   //是否显示分页（*）
                sortable: true,                     //是否启用排序
                sortOrder: "desc",
                sidePagination: "server",
                sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
                contentType: "application/x-www-form-urlencoded",
                pageNumber: 1,                      //初始化加载第一页，默认第一页,并记录
                pageSize: 10,                     //每页的记录行数（*）
                pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
                clickToSelect: true,                //是否启用点击选中行
            singleSelect: true,
                uniqueId: "id",                     //每一行的唯一标识，一般为主键列
                smartDisplay: false,
                queryParams: function (params) {
                    var temp = {
                        rows: params.limit,                         //页面大小
                        page: (params.offset / params.limit) + 1,   //页码
                        sort: params.sort,      //排序列名
                        sortOrder: params.order //排位命令（desc，asc）
                    };
                    return temp;
                },
                columns: [
                    {
                        checkbox: true,
                        visible: true                  //是否显示复选框
                    }, {
                        field: 'roleName',
                        title: '角色名称'
                    }, {
                        field: 'memo',
                        title: '角色备注'
                    }, {
                        field: 'deleteMark',
                        title: '状态',
                        formatter: function (value) {
                            if (parseInt(value) == 1) {
                                return "正常";
                            }
                            return "禁用";
                        }
                    }, {
                        field: 'createDate',
                        title: '创建时间',
                        formatter: function (value) {
                            if (value != null) {
                                var re = /-?\d+/;
                                var m = re.exec(value);
                                var d = new Date(parseInt(m[0]));
                                return d.Format("yyyy-MM-dd hh:mm:ss");
                            }
                        }
                    }
                ]
            }
        );
    },
        this.openDialog = function () {
            this.clearForm();
            $('#myModalLabel').html("新增角色");
            $('#myModal').modal({backdrop: 'static', keyboard: false});
            $('#myModal').modal('show')
        },
        this.save = function () {
            var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
            var nodes = treeObj.getCheckedNodes(true);
            var obj = {};
            obj.id = $('#cId').val();
            obj.roleName = $('#RoleName').val();
            obj.memo = $('#memo').val();
            var arr = new Array();
            if (nodes) {
                for (var i = 0; i < nodes.length; i++) {
                    arr.push(nodes[i].id);
                }
            }
            obj.menuIds = arr;
            if (!obj.roleName || obj.menuIds.length == 0) return;
             $.ajax({
                 url: "/api/role/save",
                 cache: false,
                 type: 'POST',
                 dataType: 'json',
                 data: {
                     strData: JSON.stringify(obj)
                 },
                 success: function (result) {
                     if (result.status == 1) {
                         alert('操作成功');
                         $("#role_table").bootstrapTable('refresh', {url: '/api/role/queryListByPage'});
                         $('#myModal').modal('hide');
                     } else {
                         alert(result.message);
                     }
                 }
             });
        },
        this.selectDataById = function (_value) {
            $.ajax({
                url: "/api/role/queryById",
                cache: false,
                type: 'POST',
                dataType: 'json',
                data: {rId: _value},
                success: function (result) {
                    if (result.status == 1) {
                        var obj = result.role;
                        $("#RoleName").val(obj.roleName);
                        $("#memo").val(obj.memo);
                        $("#cId").val(obj.id);
                        var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
                        var nodes = treeObj.transformToArray(treeObj.getNodes());
                        var menuList = obj.menuIds;
                        for (var i = 0; i < nodes.length; i++) {
                            for (var j = 0; j < menuList.length; j++) {
                                if (nodes[i].id == menuList[j]) {
                                    nodes[i].checked = true;
                                    treeObj.updateNode(nodes[i], true);
                                }
                            }
                        }
                        $('#myModal').modal({backdrop: 'static', keyboard: false});
                        $('#myModal').modal('show')
                    } else {
                        alert(result.message);
                    }
                }
            });
        },
        this.selectRoleList = function () {
            $.ajax({
                url: "/api/role/ZtreeReponseDto",
                cache: false,
                type: 'POST',
                dataType: 'json',
                success: function (result) {
                    var zNodes = result;
                    zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, zNodes);
                }
            });
        }
    this.clearForm = function () {
        $("#cId").val("");
        $("#RoleName").val("");
        $("#memo").val("");
        var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
        treeObj.checkAllNodes(false);
    }
}
Date.prototype.Format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
};

function ckeckForm(obj) {
    var flag = true;
    if (!obj.categoryName) {
        flag = false;
    }
    if (!obj.level) {
        flag = false;
    }
    return flag;
}

//删除
function deleteByIds(id) {
    layer.confirm('您确定要删除这条数据吗？', {btn: ['确定', '取消']}, function (index) {
        layer.close(index);
        $.ajax({
            type: "post",
            url: "/api/role/delete",
            data: {
                "rId": id
            },
            success: function (data) {
                $("#role_table").bootstrapTable('refresh', {url: '/api/role/queryListByPage'});
                layer.alert('删除成功');
            }
        });
    });
}