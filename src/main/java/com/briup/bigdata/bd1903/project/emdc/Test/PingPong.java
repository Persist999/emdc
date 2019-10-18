package com.briup.bigdata.bd1903.project.emdc.Test;

/**
 * @program:emdc
 * @package:com.briup.bigdata.bd1903.project.emdc.Test
 * @filename:${CLASS_NAME}.java
 * @create:2019/9/24 0024 9:35
 * @author:DXQ
 * @description:${description}
 **/
public class PingPong {
    public static void main(String[] arr) throws Exception {
        String b="|DF|A";
       String[] arr1=b.split("[|]");
       for (int i=0;i<arr.length;i++){
           System.out.println(arr1[i].toString());
       }

    }
}