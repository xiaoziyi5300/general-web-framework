(function () {
    //   var menu=[
    //     {
    //         icon: 'el-icon-setting',
    //         index: 'dashboard',
    //         title: '系统首页'
    //     },
    //     {
    //         icon: 'el-icon-tickets',
    //         index: 'table',
    //         title: '基础表格'
    //     },
    //     {
    //         icon: 'el-icon-message',
    //         index: 'tabs',
    //         title: 'tab选项卡'
    //     },
    //     {
    //         icon: 'el-icon-date',
    //         index: '3',
    //         title: '表单相关',
    //         subs: [
    //             {
    //                 index: 'form',
    //                 title: '基本表单'
    //             },
    //             {
    //                 index: 'editor',
    //                 title: '富文本编辑器'
    //             },
    //             {
    //                 index: 'markdown',
    //                 title: 'markdown编辑器'
    //             },
    //             {
    //                 index: 'upload',
    //                 title: '文件上传'
    //             }
    //         ]
    //     },
    //     {
    //         icon: 'el-icon-star-on',
    //         index: 'charts',
    //         title: 'schart图表'
    //     },
    //     {
    //         icon: 'el-icon-rank',
    //         index: 'drag',
    //         title: '拖拽列表'
    //     },
    //     {
    //         icon: 'el-icon-warning',
    //         index: 'permission',
    //         title: '权限测试'
    //     },
    //     {
    //         icon: 'el-icon-error',
    //         index: '404',
    //         title: '404页面'
    //     }
    // ]
    //显示菜单
    $.ajax({
        url: "/api/menu/queryMenuList",
        cache: false,
        type: 'POST',
        dataType: 'json',
        success: function (result) {
            $("ul[class='orderList']").empty();
            if (result) {
                var fristHtml = '<li class="noneList" data-text="主页" data-url="/home/welcome"> <div class="noneicon"><span class="glyphicon glyphicon-home"></span><span>主页</span></div> </li>';
                var secondHeml = '';
                for (var i = 0; i < result.length; i++) {
                    var _frist = result[i];
                    fristHtml += '\'<li class="hasList"> <div class="hasicon"><span class="glyphicon glyphicon-home"></span><span>' + _frist.menuName + '</span><span class="glyphicon pull-right glyphicon-chevron-down cur"></span> </div> <ul class="menu">'
                    var secondList = _frist.childList;
                    if (secondList) {
                        for (var j = 0; j < secondList.length; j++) {
                            var second = secondList[j];
                            secondHeml += '<li class="active" data-text="' + second.menuName + '" data-url="' + second.menuUrl + '">' + second.menuName + '</li>';
                        }
                    }
                    fristHtml += secondHeml;
                    fristHtml += '</ul></li>'
                }
                $("ul[class='orderList']").append(fristHtml);
            }
        }
    });


    var home = {
        tabIndex: null
    }
    $(document).on('click', '.orderList li', function (e) {
        $(this).toggleClass('active').siblings().removeClass('active').find('.menu').slideUp(300);
        $(this).find('.menu').slideToggle(300);
        e.stopPropagation();
        var texts = $(this).data('text');
        var open = false;
        var url = $(this).data('url');
        var li = $('<li class="tags-li"><a href="#" class="tags-li-title">' + texts + '</a><span class="glyphicon glyphicon-remove iconClose"></span></li>');
        var div = $('<div><iframe src="' + url + '" frameborder="0"></iframe></div>');
        if (texts != undefined) {
            $('.tabslist>li a').each(function (i, v) {
                if (texts == $(this).text()) {
                    open = true;
                    $('.content h4').text(texts);
                    $(".tabslist>li").eq(i).addClass('active').siblings().removeClass('active');
                    $(".ifamecontainer>div").eq(i).show().siblings().hide();
                    return false;
                }
            })
            if (!open) {
                $(".tabslist").append(li);
                $(".ifamecontainer").append(div);
                $('.content h4').text(texts);
                $(".tabslist>li:last-child").addClass('active').siblings().removeClass('active');
                $(".ifamecontainer>div:last-child").show().siblings().hide();
            }


        }
    })

    $(document).on('click', ".tabslist>li a", function (e) {
        e.stopPropagation();
        var index = $(this).parent().index();
        var texts = $(this).text();
        $(this).parent().addClass('active').siblings().removeClass('active');
        $('.orderList li:not(".hasList")').each(function (i, v) {
            var _this = $(this);
            if (texts == _this.data('text')) {
                $('.orderList li.active').removeClass('active');
                if (_this.closest('.hasList')) {
                    _this.closest('.hasList').addClass('active').siblings().removeClass('active').find('.menu').slideUp(300);
                    _this.closest('.hasList').find('.menu').slideDown(300);
                }
                if (_this.hasClass('noneList')) {
                    $('.hasList').removeClass('active').find('.menu').slideUp(300);
                }
                $('.orderList li:not(".hasList")').eq(i).addClass('active');
            }
        })

        $(".ifamecontainer>div").eq(index).show().siblings().hide();
        $('.content h4').text(texts);

    })
    $(document).on('click', ".iconClose", function (e) {
        e.stopPropagation();
        var len, texts;
        var li = $(this).parent('li');
        var index = li.index();
        li.remove();
        $(".ifamecontainer>div").eq(index).remove();
        texts = $(".tabslist>li:last-child").find('a').text();
        $('.content h4').text(texts);
        $(".tabslist>li:last-child").addClass('active').siblings().removeClass('active');
        $(".ifamecontainer>div:last-child").show().siblings().hide();
    })


})()