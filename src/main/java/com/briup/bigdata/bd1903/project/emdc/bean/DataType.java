package com.briup.bigdata.bd1903.project.emdc.bean;

/**
 * ClassName: enu
 * Description
 *
 * @author DXQ
 * @date: 2019/9/17 0017 9:46
 * @since JDK 1.8
 */
public enum DataType{
    CO2("二氧化碳"),TMP("温度"),HUM("湿度"),ILL("光照强度");

    private String name;

    DataType(String name){
        this.name=name;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name=name;
    }

}
