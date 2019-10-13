(function ($) {

    window.Ewin = function () {
        var html = '<div id="[Id]" class="modal fade" role="dialog" aria-labelledby="modalLabel">' +
            '<div class="modal-dialog modal-sm">' +
            '<div class="modal-content">' +
            '<div class="modal-header">' +
            '<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>' +
            '<h4 class="modal-title" id="modalLabel">[Title]</h4>' +
            '</div>' +
            '<div class="modal-body">' +
            '<p>[Message]</p>' +
            '</div>' +
            '<div class="modal-footer">' +
            '<button type="button" class="btn btn-default cancel" data-dismiss="modal">[BtnCancel]</button>' +
            '<button type="button" class="btn btn-primary ok" data-dismiss="modal">[BtnOk]</button>' +
            '</div>' +
            '</div>' +
            '</div>' +
            '</div>';


        var dialogdHtml = '<div id="[Id]" class="modal fade" role="dialog" aria-labelledby="modalLabel">' +
            '<div class="modal-dialog">' +
            '<div class="modal-content">' +
            '<div class="modal-header">' +
            '<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>' +
            '<h4 class="modal-title" id="modalLabel">[Title]</h4>' +
            '</div>' +
            '<div class="modal-body">' +
            '</div>' +
            '</div>' +
            '</div>' +
            '</div>';
        var reg = new RegExp("\\[([^\\[\\]]*?)\\]", 'igm');
        var generateId = function () {
            var date = new Date();
            return 'mdl' + date.valueOf();
        }
        var init = function (options) {
            options = $.extend({}, {
                title: "提示！",
                message: "提示内容",
                btnok: "确定",
                btncl: "取消",
                width: 200,
                auto: false
            }, options || {});
            var modalId = generateId();
            var content = html.replace(reg, function (node, key) {
                return {
                    Id: modalId,
                    Title: options.title,
                    Message: options.message,
                    BtnOk: options.btnok,
                    BtnCancel: options.btncl
                }[key];
            });
            $('body').append(content);
            $('#' + modalId).modal({
                width: options.width,
                backdrop: 'static'
            });
            $('#' + modalId).on('hide.bs.modal', function (e) {
                $('body').find('#' + modalId).remove();
            });
            return modalId;
        }

        return {
            alert: function (options) {
                if (typeof options == 'string') {
                    options = {
                        message: options
                    };
                }
                var id = init(options);
                var modal = $('#' + id);
                modal.find('.ok').removeClass('btn-success').addClass('btn-primary');
                modal.find('.cancel').hide();

                return {
                    id: id,
                    on: function (callback) {
                        if (callback && callback instanceof Function) {
                            modal.find('.ok').click(function () { callback(true); });
                        }
                    },
                    hide: function (callback) {
                        if (callback && callback instanceof Function) {
                            modal.on('hide.bs.modal', function (e) {
                                callback(e);
                            });
                        }
                    }
                };
            },
            confirm: function (options) {
                var id = init(options);
                var modal = $('#' + id);
                modal.find('.ok').removeClass('btn-primary').addClass('btn-success');
                modal.find('.cancel').show();
                return {
                    id: id,
                    on: function (callback) {
                        if (callback && callback instanceof Function) {
                            modal.find('.ok').click(function () { callback(true); });
                            modal.find('.cancel').click(function () { callback(false); });
                        }
                    },
                    hide: function (callback) {
                        if (callback && callback instanceof Function) {
                            modal.on('hide.bs.modal', function (e) {
                                callback(e);
                            });
                        }
                    }
                };
            },
            dialog: function (options) {
                options = $.extend({}, {
                    title: 'title',
                    url: '',
                    width: 800,
                    height: 550,
                    onReady: function () { },
                    onShown: function (e) { }
                }, options || {});
                var modalId = generateId();

                var content = dialogdHtml.replace(reg, function (node, key) {
                    return {
                        Id: modalId,
                        Title: options.title
                    }[key];
                });
                $('body').append(content);
                var target = $('#' + modalId);
                target.find('.modal-body').load(options.url);
                if (options.onReady())
                    options.onReady.call(target);
                target.modal();
                target.on('shown.bs.modal', function (e) {
                    if (options.onReady(e))
                        options.onReady.call(target, e);
                });
                target.on('hide.bs.modal', function (e) {
                    $('body').find(target).remove();
                });
            }
        }
    }();
})(jQuery);

$(function () {
    //1.初始化Table
    var oTable = new TableInit();
    oTable.Init();

    //2.初始化Button的点击事件
    var oButtonInit = new ButtonInit();
    oButtonInit.Init();
});
var TableInit = function () {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#tb_departments').bootstrapTable({
            url: '/selectUserBydOrName',                       //请求后台的URL（*）
            method: 'post',                                      //请求方式（*）
            toolbar: '#toolbar',                                //工具按钮用哪个容器
            striped: true,                                       //是否显示行间隔色
            cache: false,                                        //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                                   //是否显示分页（*）
            sortable: false,                                    //是否启用排序
            sortOrder: "asc",                                   //排序方式
            queryParams: oTableInit.queryParams,                //传递参数（*）
            sidePagination: "server",                          //分页方式：client客户端分页，server服务端分页（*）
            pageNumber:1,                                       //初始化加载第一页，默认第一页
            pageSize: 10,                                       //每页的记录行数（*）
            pageList: [10, 25, 50, 100],                        //可供选择的每页的行数（*）
            search: false,                                     //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            strictSearch: true,
            showColumns: true,                                //是否显示所有的列
            showRefresh: true,                                //是否显示刷新按钮
            showExport: true,
            //exportTypes: ['csv','txt','xml'],
            minimumCountColumns: 2,                         //最少允许的列数
            clickToSelect: true,                            //是否启用点击选中行
            height: 450,                                      //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            //showToggle:true,                                 //是否显示详细视图和列表视图的切换按钮
            cardView: false,                                 //是否显示详细视图
            detailView: false,                               //是否显示父子表
            //dataType:"json",
            columns: [{
                checkbox: true
            }, {
                field: 'userId',
                align: "center",
                title: '编号'
            }, {
                field: 'userName',
                align: "center",
                title: '名称'
            }, {
                field: 'userEmail',
                align: "center",
                title: '邮箱'
            }, {
                field: 'userPhone',
                align: "center",
                title: '联系电话'
            }, {
                field: 'userAddr',
                align: "center",
                title: '住址'
            },{
                field:'userRemark',
                align: "center",
                title:'备注'
            }],
            onPageChange: function (size, number) {
            },
            formatNoMatches: function(){
                return '无符合条件的记录';
            }
        });
        $(window).resize(function () {
            $('#tb_departments').bootstrapTable('resetView');
        });
    };

    //组织查询参数
    oTableInit.queryParams = function (params) {
        //获取查询表中的数据
        var param = {};
        $('#formSearch').find('[name]').each(function () {
            var value = $(this).val();
            if (value != '') {
                param[$(this).attr('name')] = value;
            }
        });
        param['pageSize'] = params.limit;   //页面大小
        param['pageNumber'] = params.offset;   //页码
        return param;
    };
    return oTableInit;
};

//初始化
var ButtonInit = function () {
    var oInit = new Object();
    var postdata = {};

    oInit.Init = function () {
        //初始化页面上面的按钮事件
    };
    return oInit;
};

//查询后台提交
function userQuery() {
    $('#tb_departments').bootstrapTable('refresh');//刷新Table，Bootstrap Table 会自动执行重新查询
}

//查询重置实现逻辑
function userReset() {
    document.getElementById("userId").value = "";
    document.getElementById("userName").value = "";
}

//新增数据实现逻辑
function userAdd() {
    //如果后台返回成功则关闭模态框。
    var flag = $('#userAddModal').modal('hide');
    var o = {};
    //如果返回失败则显示失败原因
    var arry = $("#registerform").serializeArray();
    //console.log("+++++arry+++-->" + arry);
    //转化为数组
    $.each(arry, function () {
        if (o[this.name]) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    alert(JSON.stringify(o));

}

//修改数据实现逻辑
function userEdit() {
    //获取选中行数
    var row = $.map($("#tb_departments").bootstrapTable('getSelections'),function(row){
        return row ;
    });
    //基础校验
    if (row.length > 1){
        alert("选中的行数大于1！");
    }else if (row.length == 0){
        alert("请勾选需要修改的数据！");

    }
    //获取userId
    var userId = null;

     for(var i=0;i<row.length;i++){
         userId = row[i].userId;
         console.log("userId:" + userId);
     }
    //提交数据库处理

}

//删除数据实现逻辑
function userDelete() {
    console.log("+++++userDelete++-->")
    //获取选中行数
    var row = $.map($("#tb_departments").bootstrapTable('getSelections'),function(row){
        return row ;
    });
    //基础校验
    if (row.length == 0){
        //Ewin.alert("请勾选需要删除的数据！");
        alert("请勾选需要删除的数据！");
        return false;
    }
    var deleteFlag =  confirm("确定要删除选中的这【"+ row.length +"】条数据？");
    //判断
    if (deleteFlag){
    //做业务逻辑处理

    }else{
        return deleteFlag;
    }
    console.log("----->" + deleteFlag)
}