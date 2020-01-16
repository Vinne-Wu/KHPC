package com.khpc.cn.core.entity.exception;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Vinne
 * @date 2020/1/15 15:46
 * @description 自定义异常 - 数据处理异常，发生与数据处理过程
 **/
@Data
@Getter
@Setter
public class HandleDataExecption extends Exception {

    private  String  message;

    public HandleDataExecption(String message) {
        this.message = message;
    }
}
