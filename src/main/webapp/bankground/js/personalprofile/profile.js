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
    //document.getElementById("personProfile").innerText=decodeURI(decodeURI(v_name));



}

//个人资料
function index() {
    //传参：
    var label=document.getElementById("userName");
    var v_name=label.innerText;
    //alert(v_name);
    //返回成功跳转到主页面
    window.location.href="../../index.html?name=" + encodeURI(encodeURI(v_name));
    window.event.returnValue=false;
}

//个人资料
function userManage() {
    //传参：
    var label=document.getElementById("userName");
    var v_name=label.innerText;
    //alert(v_name);
    //返回成功跳转到主页面
    window.location.href="../usermanage/userManage.html?name=" + encodeURI(encodeURI(v_name));
    window.event.returnValue=false;
}