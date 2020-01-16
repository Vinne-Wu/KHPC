package com.khpc.cn.core.util;

import org.jsoup.Jsoup;

import java.io.File;
import java.io.IOException;

/**
 * @author Vinne
 * @date 2020/1/16 14:46
 * @description  把lib目录下的jar转化成pom依赖进行输出
 **/
public class GetDependicesByLibUtil {

    /**
     * @param libPath lib在本地的绝对路径
     */
    public static void consoleDependicesStr(String libPath){
        File dir = new File(libPath); //lib目录
        for (File jar : dir.listFiles()) {
            String jarname  = jar.getName();
            int index = jarname.lastIndexOf("-");
            int jarIndex = jarname.lastIndexOf(".");

            String  bundleName = jarname.substring(0,index);
            String bundleVersion = jarname.substring(index +1 ,jarIndex );

            if (bundleName ==null || bundleVersion == null){
                continue;
            }

            System.out.println("<!--"+jar.getName()+"-->");
            System.out.println(getDependices(bundleName,bundleVersion));
            System.out.println();

        }
    }

    public static String getDependices(String key, String ver) {

        String url ="https://mvnrepository.com/search?q="+key;

        org.jsoup.nodes.Document doc = null;

        try {
            doc = Jsoup.connect(url).ignoreContentType(true).timeout(30000).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        org.jsoup.nodes.Element elem = doc.body();

        String href = elem.childNodes().get(1).childNodes().get(2).childNodes().get(2).childNodes().get(0).attributes().get("href");

        String[] jarinfo = href.split("/");

        String result = "<dependency>\n" +
                "    <groupId>"+jarinfo[2]+"</groupId>\n" +
                "    <artifactId>"+key+"</artifactId>\n" +
                "    <version>"+ver+"</version>\n" +
                "</dependency>";
        return result;
    }
}
