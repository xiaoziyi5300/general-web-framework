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
    $(document).on('click', '#save-btn', function () {
        new roleFun().openDialog();
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
                        title: '状态'
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
                    }/*, {
                    field: 'id',
                    title: '操作',
                    width: 120,
                    align: 'center',
                    valign: 'middle',
                    formatter: actionFormatter
                },*/
                ]
            }
        );
    },
        this.openDialog = function () {
            this.selectRoleList();
            //this.clearForm();
            $('#myModalLabel').html("新增角色");
            $('#myModal').modal({backdrop: 'static', keyboard: false});
            $('#myModal').modal('show')
        },
        this.save = function () {
            debugger;
            var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
            var nodes = treeObj.getCheckedNodes(true);
            console.log(nodes);


            /* var obj = {};
             obj.id = $("#cId").val();
             obj.categoryName = $("#CategoryName").val();
             if (!$("#cId").attr("attrValue")) {
                 obj.parentId = $('#selectId').selectpicker('val');
             }
             obj.level = $("#categoryLevel").val();
             obj.sort = $("#sort").val();
             if (!ckeckForm(obj)) return;
             $.ajax({
                 url: "/api/productCategory/save",
                 cache: false,
                 type: 'POST',
                 dataType: 'json',
                 data: obj,
                 success: function (result) {
                     if (result.status == 1) {
                         alert('操作成功');
                         $("#role_table").bootstrapTable('refresh', {url: '/api/productCategory/queryListByPage'});
                         $('#myModal').modal('hide');
                     } else {
                         alert(result.message);
                     }
                 }
             });*/
        },
        this.selectDataById = function (_value) {
            $.ajax({
                url: "/api/productCategory/queryById",
                cache: false,
                type: 'POST',
                dataType: 'json',
                data: {cId: _value},
                success: function (result) {
                    if (result.status == 1) {
                        var obj = result.productCategory;
                        $('#myModalLabel').html("修改类目");
                        $("#cId").val(obj.id);
                        $("#CategoryName").val(obj.categoryName);
                        if (obj.parentId) {
                            new roleFun().selectCategory(null);
                            $('#selectId').selectpicker('val', obj.parentId);
                        } else {
                            $("#cId").attr("attrValue", "1");
                            $('#pc').hide();
                        }
                        $("#categoryLevel").val(obj.level);
                        $("#sort").val(obj.sort);
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
                    console.log(result);
                    var zNodes = result;
                    zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, zNodes);
                }
            });
        }
    this.clearForm = function () {
        $("#cId").val("");
        $("#CategoryName").val("");
        $('#selectId').selectpicker('val', "");
        $("#categoryLevel").val("");
        $("#sort").val("");
        $("#cId").attr("attrValue", "");
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

//操作栏的格式化
function actionFormatter(value, row, index) {
    var id = value;
    var result = "";
    result += "<a href='javascript:;' class='btn btn-xs blue' onclick=\"new roleFun().selectDataById('" + id + "')\" title='编辑'><span class='glyphicon glyphicon-pencil'></span></a>";
    result += "<a href='javascript:;' class='btn btn-xs red' onclick=\"deleteByIds('" + id + "')\" title='删除'><span class='glyphicon glyphicon-remove'></span></a>";
    return result;
}

//删除
function deleteByIds(id) {
    layer.confirm('您确定要删除这条数据吗？', {btn: ['确定', '取消']}, function (index) {
        layer.close(index);
        $.ajax({
            type: "post",
            url: "/api/productCategory/delete",
            data: {
                "cId": id
            },
            success: function (data) {
                $("#table").bootstrapTable('refresh', {url: '/api/productCategory/queryListByPage'});
                layer.alert('删除成功');
            }
        });
    });
}