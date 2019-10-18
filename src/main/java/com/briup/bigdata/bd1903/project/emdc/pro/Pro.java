package com.briup.bigdata.bd1903.project.emdc.pro;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @program:emdc
 * @package:com.briup.bigdata.bd1903.project.emdc.pro
 * @filename:${CLASS_NAME}.java
 * @create:2019/9/19 0019 18:14
 * @author:DXQ
 * @description:${description}
 **/
public class Pro {
    private static Properties pro;
    static {
        pro=new Properties();
        try {
            pro.load(new FileInputStream(Pro.class.getResource("/pro.properties").getPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String getValue(String key){
        String value=pro.getProperty(key);
        return value;
    }

}
