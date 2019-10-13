document.write("<script language=javascript src='bankground/js/common/header.js'><\/script>");
document.write("<script language=javascript src='bankground/js/common/MD5.js'><\/script>");

function cleanPage() {
    document.getElementById("id").value = "";
    document.getElementById("name").value = "";
    document.getElementById("email").value = "";
    document.getElementById("phone").value = "";
    document.getElementById("password").value = "";
}

function register() {
    //用户编号
    var v_user_id = document.getElementById("id").value;
    //用户名称
    var v_user_name = document.getElementById("name").value;
    //联系方式
    var v_phone = document.getElementById("name").value;
    //邮箱
    var v_email = document.getElementById("email").value;
    //密码
    var v_pass = document.getElementById("password").value;
    //同意条款和政策
    var v_tiaokuan = document.getElementById("tiaokuan").checked;
    //密码进行MD5加密
    password.value = hex_md5(v_pass);
    //BootStarpValidate校验
    $('#registerform').bootstrapValidator('validate');
    //如果有失败的则返回
    if(!$('#registerform').data('bootstrapValidator').isValid()){
        document.getElementById("password").value = "";
        return ;
    }

    //调用AJAX提交表单
    //获取序列化后的数组
    var arry = $("#registerform").serializeArray();
    var o = {};
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
    //转化为字符串
    var body =  JSON.stringify(o);
    //从公共方法中获取系统头
    var head = getHeader();
    //初始化请求报文
    var initJson = {};
    //添加头信息
    initJson.head = eval('(' + head + ')');
    //添加体信息
    initJson.body = eval('(' + body + ')');

    var v_retCode = null;
    var v_retMsg = null;

    //alert(JSON.stringify(initJson));
    //AJAX提交数据
    $.ajax({
        async:false,
        url: "/registerUser",
        type:"post",
        dataType:"json",
        contentType: "application/json;charset=UTF-8",
        data: JSON.stringify(initJson),
        success: function (result) {
            //alert(result.head);
            //将对象转换成字符串
            var data = JSON.stringify(result);
            //alert(data);
            //将字符串转换成JSON对象
            var obj1 = eval('(' + data + ')');
            //var v_head = obj1.head;
            v_retCode = obj1.head.ret.retCode;
            v_retMsg = obj1.head.ret.retMsg;
            //alert(v_ret.retCode);
            //alert(obj1.head.ret.retCode);
            //alert(obj1.head.ret.retCode);
            //处理后台返回的结果:如果查询结果失败
            if ("000000" != v_retCode){
                alert(v_retCode + ":" + v_retMsg);
                return false;
            }else{
                alert(window.location.href);
                //alert(v_retCode);
                //返回成功跳转到主页面
                window.location.href="login.html";
                window.event.returnValue=false;
            }
        },
        error:function (result) {
            //将实体对象转换成字符串
            var v_error = JSON.stringify(result);
            //将字符串转换成JSON对象
            var v_json_obj = eval('(' + v_error + ')');
            v_retCode = v_json_obj.head.ret.retCode;
            v_retMsg = v_json_obj.head.ret.retMsg;
            alert(v_retCode + v_retCode);
            return false;
        }

    })
 }