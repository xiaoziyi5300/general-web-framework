$(function () {
    $(document).on('click', '#save-btn', function () {
        new memufun().save_open();
    });
    $(document).on('click', '#save', function () {
        new memufun().save();
    });

    $(":radio[name='isParent']").click(function () {
        if ($(this).val() == 1) {
            $('#selectId').prop('disabled', true);
        } else {
            $('#selectId').prop('disabled', false);
        }
    });
});
var memufun = function () {
    this.save_open = function () {
        $('#myModalLabel').html("新增菜单");
        $('#selectId').prop('disabled', true);
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
        }
}