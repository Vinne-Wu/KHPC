/**
 *  跳转登录页面
 */
function registerPagep() {
    window.location.href = "/common/registerPage";
}

/**
 *   登录
 */
function login() {

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
                alert("登录失败！");
            }
        },
        error: function() {
            alert("数据异常！");
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