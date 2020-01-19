package com.khpc.cn.core.util;

import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Vinne
 * @date 2020/1/18 22:55
 * @description  md5密码加密工具类
 **/
public class Md5SecurityUtil<T> {
    /**
     * 利用MD5进行加密
     * @param str  待加密字段
     * @return
     */
    public static String EncoderByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        //确定计算方法
        MessageDigest md5=MessageDigest.getInstance("MD5");
        BASE64Encoder base64en = new BASE64Encoder();
        //加密后的字符串
        String newstr=base64en.encode(md5.digest(str.getBytes("utf-8")));
        return newstr;
    }

    /**
     *  密码校验
     * @param newpasswd  待校验密码
     * @param oldpasswd  已加密密码
     * @return
     */
    public static boolean checkpassword(String newpasswd,String oldpasswd) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        if(EncoderByMd5(newpasswd).equals(oldpasswd)){
            return true;
        }
        else{
            return false;
        }
    }
}
