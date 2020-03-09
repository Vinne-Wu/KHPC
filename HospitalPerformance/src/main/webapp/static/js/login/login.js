var repeatCode = 0;

/**
 * 跳转到登录页面
 */
function returnLogin() {
    window.location.href = "/common/returnLogin";
}

/**
 *   登录
 */
function login() {
    // 验证码
    let checkCode = ""+show_num[0]+show_num[1]+show_num[2]+show_num[3];

    if($("#checkCode").val().toLowerCase() == "" || $("#checkCode").val().toLowerCase() == null){
        comResultMsg("1","警告！","验证码不能为空！");
        return;
    }else{
        if($("#checkCode").val().toLowerCase() != checkCode.toLowerCase()){
            comResultMsg("2","错误！","验证码输入有误！");
            return;
        }
    }

    let rememerStatus = false;

    if($('input[type=checkbox]:checked').length>0){
        rememerStatus = true;
    }

    let user = {
        "password":$("#password").val(),
        "email":$("#email").val(),
        "rememberMe":rememerStatus
    }

    $.ajax({
        type: "POST",
        url:"/common/checkLogin",
        data:JSON.stringify(user),
        contentType: 'application/json;charset=utf-8',
        dataType:'json',
        async: true,  // 注：ajax 同步情况下会阻塞一些js效果，比如页面跳转等等，所有注意同异步使用
        success: function(data) {
            if(data.code == 200){
                // 获取角色类型id
                let role = data.result.user.role;
                returnPageByRole(role);
            }else{
                comResultMsg("2","失败！",data.message);
            }
        },
        error: function() {
            comResultMsg("3","系统异常！",data.message);
        }
    });
}

/**
 *  根据用户角色进行页面跳转
 * @param role   1:普通用户 2：注册员 3.系统管理员
 */
function returnPageByRole(role) {
    if(role == "admin"){
        window.location.href="/admin/adminIndex";
    }
    else if(role == "person"){
        window.location.href="/person/personIndex";
    }
    else if(role == "register"){
        window.location.href="/register/registerIndex";
    }
}

/**
 *  跳转注册页面
 */
function registerPagep() {
    window.location.href = "/common/registerPage";
}

/**
 * 注册用户，注册成功跳转到登录页面
 */
function registerUser() {

    if(checkValue()== false) return;

    let user = {
        "name":$("#name").val(),
        "password":$("#password").val(),
        "phoneNum":$("#phoneNum").val(),
        "email":$("#email").val(),
        "role":"person"
    }

    $.ajax({
        type: "POST",
        url:"/common/registerUser",
        data:JSON.stringify(user),
        contentType: 'application/json;charset=utf-8',
        dataType:'json',
        async: true,  // 注：ajax 同步情况下会阻塞一些js效果，比如页面跳转等等，所有注意同异步使用
        success: function(data) {
            if(data.code == 200){
                comResultMsg("3","成功！","即将跳转登录页面，请稍等...");
                setTimeout(function (){
                    window.location.href="/common/returnLogin";
                }, 1500);
            }else{
                comResultMsg("2","错误！","系统异常！");
            }
        },
        error: function() {
            alert("数据异常！");
        }
    });

}

/**
 *  校验注册数据字段
 */
function checkValue() {

    let email = $("#email").val();
    let password = $("#password").val();
    let name = $("#name").val();
    let phoneNum = $("#phoneNum").val();

    // 邮箱校验
    let checkEmailCode = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
    if(email.replace(/\s+/g,"") == "" || email.replace(/\s+/g,"") == null){
        comResultMsg("1","异常！","邮箱不能为空！");
        return  false;
    }
    else {

        checkRepeatEmail(email);

        if(repeatCode == 0){
            if(checkEmailCode.test(email) == false){
                comResultMsg("2","错误！","邮箱格式错误！");
                return  false;
            }
        }else{
            return false;
        }
    }

    // 密码校验（非空校验）
    if(password.replace(/\s+/g,"") == "" || password.replace(/\s+/g,"") == null){
        comResultMsg("1","异常！","密码不能为空！");
        return  false;
    }

    // 名称（昵称）校验（非空校验）
    if(name.replace(/\s+/g,"") == "" || name.replace(/\s+/g,"") == null){
        comResultMsg("1","异常！","姓名不能为空！");
        $("#warningModel").modal('show');
        return  false;
    }

    // 手机号码校验
    let checkPhoneCode = /^1(3|4|5|6|7|8|9)\d{9}$/;
    if(phoneNum.replace(/\s+/g,"") == "" || phoneNum.replace(/\s+/g,"") == null){
        comResultMsg("1","异常！","手机号码不能为空！");
        $("#warningModel").modal('show');
        return  false;
    }
    else{
        if(checkPhoneCode.test(phoneNum) == false){
            comResultMsg("2","错误！","手机号码格式错误！");
            return  false;
        }
    }
    return true;
}

/**
 *  监测邮箱是否重复注册
 */
function checkRepeatEmail(email) {

    let user = {
        "email":email
    }

    $.ajax({
        type: "POST",
        url:"/common/checkRepeatEmail",
        data:JSON.stringify(user),
        contentType: 'application/json;charset=utf-8',
        dataType:'json',
        async: false,
        success: function(data) {
            if(data.result == 1){
                comResultMsg("2","错误！","该邮箱已经被注册！");
                repeatCode = 1;
                return;
            }else{
                repeatCode = 0;
                return;
            }
        },
        error: function() {
            alert("数据异常！");
        }
    });
}
/**
 *  通用回调结果集展示模态框
 * @param type   类型 1.警告框 2.错误框 3.成功框
 * @param msg  自定义模态框简要类型说明文本
 * @param msgInfo  自定义模态框详细信息说明文本
 */
function comResultMsg(type,msg,msgInfo) {
    if(type == "1"){
        $("#wMsg").text(msg);
        $("#wMsgInfo").text(msgInfo);
        $("#warningModel").modal("show");
    }
    else if (type == "2"){
        $("#eMsg").text(msg);
        $("#eMsgInfo").text(msgInfo);
        $("#errorModel").modal("show");
    }
    else if(type == "3"){
        $("#sMsg").text(msg);
        $("#sMsgInfo").text(msgInfo);
        $("#successModel").modal("show");
    }
}

/**
 *  关闭模态框
 * @param type
 */
function closeModel(type){
    $("#"+type+"").modal("hide");
}