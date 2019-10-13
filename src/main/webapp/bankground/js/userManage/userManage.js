document.write("<script language=javascript src='../js/common/header.js'><\/script>");
//根据QueryString参数名称获取值
function getQueryStringByName(name) {
    var result = location.search.match(new RegExp("[/?/&;]" + name + "=([^/&;]+)", "i"));
    if (result == null || result.length < 1) {
        return "";
    }
    return result[1];
}

//初始化
function init() {
    var v_name  = getQueryStringByName("name");
    //alert(decodeURI(decodeURI(v_name)));
    //document.getElementById("userName").value = v_name;
    document.getElementById("userName").innerText=decodeURI(decodeURI(v_name));

}

//个人资料
function profile() {
    //传参：
    var v_name  = getQueryStringByName("name");
    //alert(v_name);
    //返回成功跳转到主页面
    window.location.href="../personalprofile/profile.html?name=" + v_name;
    window.event.returnValue=false;
}

//查询逻辑实现
function searchs() {
    //获取用户编号
    var v_user_id = document.getElementById("userIdSearch").value;
    //获取用户名称
    var v_user_name = document.getElementById("userNameSearch").value;

    var v_selected = document.getElementsByName("DataTables_Table_0_length")[0].value;
    var v_num = $('#bootStrapTableId').bootStrapTable('getOptions').pageNumber;
    alert(v_num);
    var o = {};
    var arry = $("#userManages").serializeArray();
    //将Object转化成数组
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
    //组织body
    var body = JSON.stringify(o);
    //从公共方法中获取系统头
    var head = getHeader();
    //初始化请求报文
    var initJson = {};
    //添加头信息
    initJson.head = eval('(' + head + ')');
    //添加体信息
    initJson.body = eval('(' + body + ')');
    alert(JSON.stringify(initJson));


}