$(function () {
    new pridcutCategoryFun().initData();
    $('div[class="fixed-table-body"]').css({'height': '81%'});
    $(document).on('click', '#save-btn', function () {
        new pridcutCategoryFun().openDialog();
    });
    $(document).on('click', '#save', function () {
        new pridcutCategoryFun().save();
    });
});
var pridcutCategoryFun = function () {
    //加载数据
    this.initData = function () {
        var $table;
        var queryUrl = '/api/productCategory/queryListByPage';
        $table = $('#table').bootstrapTable({
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
                detailView: true,//父子表
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
                columns: [/*{
                checkbox: true,
                visible: true                  //是否显示复选框
            },*/ {
                    field: 'categoryName',
                    title: '类目名称'
                }, {
                    field: 'level',
                    title: '类目等级',
                    formatter: function (_value) {
                        if (_value == 1) {
                            return "一级";
                        } else {
                            return "二级";
                        }
                    }
                }, {
                    field: 'createUserName',
                    title: '创建人'
                }, {
                    field: 'createDate',
                    title: '创建时间',
                    formatter: function (value) {
                        if (value != null) {
                            var re = /-?\d+/;
                            var m = re.exec(val);
                            var d = new Date(parseInt(m[0]));
                            return d.Format("yyyy-MM-dd hh:mm:ss");
                        }
                    }
                }, {
                    field: 'id',
                    title: '操作',
                    width: 120,
                    align: 'center',
                    valign: 'middle',
                    formatter: actionFormatter
                },
                ],
                //注册加载子表的事件。注意下这里的三个参数！
                onExpandRow: function (index, row, $detail) {
                    new pridcutCategoryFun().showSecondCategory(index, row, $detail);
                }
            }
        );
    },
        this.openDialog = function () {
            this.selectCategory(null);
            this.clearForm();
            $('#myModalLabel').html("新增类目");
            $('#pc').show();
            $('#myModal').modal({backdrop: 'static', keyboard: false});
            $('#myModal').modal('show')
        },
        this.save = function () {
            var obj = {};
            obj.id = $("#cId").val();
            obj.categoryName = $("#CategoryName").val();
            obj.parentId = $('#selectId').selectpicker('val');
            obj.level = $("#categoryLevel").val();
            obj.sort = $("#sort").val();
            $.ajax({
                url: "/api/productCategory/save",
                cache: false,
                type: 'POST',
                dataType: 'json',
                data: obj,
                success: function (result) {
                    if (result.status == 1) {
                        alert('操作成功');
                        $("#table").bootstrapTable('refresh', {url: '/api/productCategory/queryListByPage'});
                        $('#myModal').modal('hide');
                    } else {
                        alert(result.message);
                    }
                }
            });
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
                            new pridcutCategoryFun().selectCategory(null);
                            $('#selectId').selectpicker('val', obj.parentId);
                        } else {
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
        this.selectCategory = function (value) {
            $.ajax({
                url: "/api/productCategory/queryCategoryList",
                cache: false,
                type: 'POST',
                dataType: 'json',
                data: {parentId: value == null ? 0 : value},
                success: function (result) {
                    if (result.status == 1) {
                        $("div[name='parentMenu']").empty();
                        var cList = result.list;
                        var _html = '<select class="selectpicker" id="selectId" ><option value="">请选择</option>';
                        if (cList) {
                            for (var i = 0; i < cList.length; i++) {
                                _html += '<option value=' + cList[i].id + '>' + cList[i].categoryName + '</option>';
                            }
                            _html += '</select>';
                        }
                        $("div[name='parentMenu']").append(_html);
                        $(".selectpicker").selectpicker('refresh');
                    }
                }
            });
        },
        this.showSecondCategory = function (index, row, $detail) {
            var parentId = row.id;
            var cur_table = $detail.html('<table id="secondTable"></table>').find('table');
            $(cur_table).bootstrapTable({
                url: '/api/productCategory/queryListByPage',
                method: 'post',
                queryParams: {parentId: parentId, rows: 100, page: 1},
                ajaxOptions: {parentId: parentId},
                clickToSelect: true,
                detailView: false,//父子表
                contentType: "application/x-www-form-urlencoded",
                uniqueId: "id",
                pageSize: 100,
                pageList: [10, 25],
                columns: [{
                    checkbox: true,
                }, {
                    field: 'categoryName',
                    title: '类目名称'
                }, {
                    field: 'level',
                    title: '类目等级'
                }, {
                    field: 'createUserName',
                    title: '创建人'
                }, {
                    field: 'createDate',
                    title: '创建时间',
                    formatter: function (value) {
                        if (value != null) {
                            var re = /-?\d+/;
                            var m = re.exec(val);
                            var d = new Date(parseInt(m[0]));
                            return d.Format("yyyy-MM-dd hh:mm:ss");
                        }
                    }
                }]
            });
        },
        this.clearForm = function () {
            $("#cId").val("");
            $("#CategoryName").val("");
            $('#selectId').selectpicker('val', "");
            $("#categoryLevel").val("");
            $("#sort").val("");
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

//操作栏的格式化
function actionFormatter(value, row, index) {
    var id = value;
    var result = "";
    result += "<a href='javascript:;' class='btn btn-xs blue' onclick=\"new pridcutCategoryFun().selectDataById('" + id + "')\" title='编辑'><span class='glyphicon glyphicon-pencil'></span></a>";
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