/**
 * 初始化
 */
$(function () {
    getUerInfo();
});

/**
 *  退出登录
 */
function loginout() {
    $.ajax({
        type: "GET",
        url:"/common/logout",
        dataType:'json',
        async: true,  // 注：ajax 同步情况下会阻塞一些js效果，比如页面跳转等等，所有注意同异步使用
        success: function(data) {
            if(data.code == 200){
                window.location.href = "/common/returnLogin";
            }
        },
        error: function() {
            alert("数据异常！");
        }
    });
}

/**
 * 获取登录账户信息
 */
function getUerInfo() {
    $.ajax({
        type: "POST",
        url:"/common/getUserInfo",
        dataType:'json',
        async: true,  // 注：ajax 同步情况下会阻塞一些js效果，比如页面跳转等等，所有注意同异步使用
        success: function(data) {
            if(data.code == 200){
                // 获取用户信息
                let user = data.result;
                $("#userName").text(user.name);

                // 系统备用字段设置（日后完善）
                $("#msgNum").text(3);
                $("#optMsgNum").text("("+3+")");
            }
        },
        error: function() {
            alert("数据异常！");
        }
    });
}

