$(function () {
    $(document).on('click', '#save-btn', function () {
        new memufun().save_open();
    });
    $(document).on('click', '#save', function () {
        new memufun().save();
    });

    $(":radio[name='isParent']").click(function () {
        if ($(this).val() == 1) {
            $('#selectId').attr('disabled', true);
            $('#menuUrl').attr('readonly', true)
        } else {
            $('#selectId').attr('disabled', false);
            $('#menuUrl').attr('readonly', false)
        }
    });
});
var memufun = function () {
    this.save_open = function () {
        $('#myModalLabel').html("新增菜单");
        new memufun().selectFristMenu();
        $('#selectId').prop('disabled', true);
        $('#menuUrl').attr('readonly', true);
        $('#myModal').modal({backdrop: 'static', keyboard: false});
        $('#myModal').modal('show')
    },
        this.save = function () {
            var obj = {};
            obj.id = $('#menuId').val();
            obj.menuName = $('#menuName').val();
            obj.menuUrl = $('#menuUrl').val();
            obj.isParent = $("input[name='isParent']:checked").val();
            obj.parentId = $('#selectId').selectpicker('val');
            obj.sort = $('#sort').val();
            obj.isEffect = $("input[name='isEffect']:checked").val();
            obj.memo = $('#memo').val();
            $.ajax({
                url: "/api/menu/saveMenu",
                cache: false,
                type: 'POST',
                dataType: 'json',
                data: obj,
                success: function (result) {
                    if (result.status == 1) {
                        alert('操作成功');
                        $('#myModal').modal('hide');
                    } else {
                        alert(result.message);
                    }
                }
            });
        },
        this.selectFristMenu = function () {
            $.ajax({
                url: "/api/menu/queryAllList",
                cache: false,
                type: 'POST',
                dataType: 'json',
                success: function (result) {
                    if (result.status == 1) {
                        $("div[name='parentMenu']").empty();
                        var menuList = result.list;
                        var _html = '<select class="selectpicker" id="selectId" ><option value="">请选择</option>';
                        if (menuList) {
                            for (var i = 0; i < menuList.length; i++) {
                                _html += '<option value=' + menuList[i].id + '>' + menuList[i].menuName + '</option>';
                            }
                            _html += '</select>';
                        }
                        $("div[name='parentMenu']").append(_html);
                        $(".selectpicker").selectpicker('refresh');
                    }
                }
            });
        }
}