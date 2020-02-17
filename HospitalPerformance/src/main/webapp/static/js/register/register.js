/**
 * 跳转到登录页面
 */
function returnLogin() {
    window.location.href = "/common/returnLogin";
}

/**
 * 注册用户，注册成功跳转到登录页面
 */
function registerUser() {

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
               alert("注册成功！");
               window.location.href="/register/returnLogin";
           }else{
               alert("注册失败！");
           }
        },
        error: function() {
            alert("数据异常！");
        }
    });

}
