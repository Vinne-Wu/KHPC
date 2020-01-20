package com.khpc.cn.core.util;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Vinne
 * @date 2020/1/18 22:55
 * @description  md5密码加密工具类
 **/
public class Md5SecurityUtil<T> {

    private static final String hexDigits[] = {"0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    /**
     * 利用MD5进行加密
     * @param origin  待加密字段
     * @param charsetname  编码格式
     * @return
     */
    public static String EncoderByMd5(String origin, String charsetname) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        String resultString;
        try {
            resultString = origin;
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (charsetname == null || "".equals(charsetname))
                resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
            else
                resultString = byteArrayToHexString(md.digest(resultString.getBytes(charsetname)));
        } catch (Exception exception) {
            throw exception;
        }
        return resultString.toUpperCase();
    }


    /**
     *  密码校验
     * @param newpasswd  待校验密码
     * @param oldpasswd  已加密密码
     * @param charsetname  编码方式
     * @return
     */
    public static boolean checkpassword(String newpasswd,String oldpasswd,String charsetname) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        if(EncoderByMd5(newpasswd,charsetname).equals(oldpasswd)){
            return true;
        }
        else{
            return false;
        }
    }


    /**
     * 这里主要是遍历8个byte，转化为16位进制的字符，即0-F
     */
    private static String byteArrayToHexString(byte b[]) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++)
            resultSb.append(byteToHexString(b[i]));

        return resultSb.toString();
    }

    /**
     * 这里是针对单个byte，256的byte通过16拆分为d1和d2
     * @param b
     * @return
     */
    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n += 256;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

}
