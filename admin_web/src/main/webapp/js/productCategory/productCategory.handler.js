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
            pageSize: 2,                     //每页的记录行数（*）
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
                    sortable: true
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
                }],
                //注册加载子表的事件。注意下这里的三个参数！
                onExpandRow: function (index, row, $detail) {
                    //oInit.InitSubTable(index, row, $detail);
                }
            }
        );
    },
        this.openDialog = function () {
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