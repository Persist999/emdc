package com.briup.bigdata.bd1903.project.emdc.bean;

import java.io.Serializable;

/**
 * @program: emdc
 * @package: com.briup.bigdata.bd1903.project.emdc.bean
 * @filename: Environment.java
 * @create: 2019.09.17 09:06
 * @author: Kevin
 * @description: .用于封装原始数据的类
 **/
public class Environment implements Serializable {
    private String srcId;
    private String destId;
    private String devId;
    private String sensorId;
    private Integer sensorCounter;
    private String cmdType;
    private DataType dataType;
    private String data;
    private Integer status;
    private Long timestamp;

    public Environment(){
    }

    public Environment(String srcId,String destId,String devId,String sensorId,Integer sensorCounter,String cmdType,DataType dataType,String data,Integer status,Long timestamp){
        this.srcId=srcId;
        this.destId=destId;
        this.devId=devId;
        this.sensorId=sensorId;
        this.sensorCounter=sensorCounter;
        this.cmdType=cmdType;
        this.dataType=dataType;
        this.data=data;
        this.status=status;
        this.timestamp=timestamp;
    }

    public String getSrcId(){
        return srcId;
    }

    public void setSrcId(String srcId){
        this.srcId=srcId;
    }

    public String getDestId(){
        return destId;
    }

    public void setDestId(String destId){
        this.destId=destId;
    }

    public String getDevId(){
        return devId;
    }

    public void setDevId(String devId){
        this.devId=devId;
    }

    public String getSensorId(){
        return sensorId;
    }

    public void setSensorId(String sensorId){
        this.sensorId=sensorId;
    }

    public Integer getSensorCounter(){
        return sensorCounter;
    }

    public void setSensorCounter(Integer sensorCounter){
        this.sensorCounter=sensorCounter;
    }

    public String getCmdType(){
        return cmdType;
    }

    public void setCmdType(String cmdType){
        this.cmdType=cmdType;
    }

    public DataType getDataType(){
        return dataType;
    }

    public void setDataType(DataType dataType){
        this.dataType=dataType;
    }

    public String getData(){
        return data;
    }

    public void setData(String data){
        this.data=data;
    }

    public Integer getStatus(){
        return status;
    }

    public void setStatus(Integer status){
        this.status=status;
    }

    public Long getTimestamp(){
        return timestamp;
    }

    public void setTimestamp(Long timestamp){
        this.timestamp=timestamp;
    }

    @Override
    public String toString(){
        return "Environment{"+"srcId='"+srcId+'\''+", destId='"+destId+'\''+", devId='"+devId+'\''+", sensorId='"+sensorId+'\''+", sensorCounter="+sensorCounter+", cmdType='"+cmdType+'\''+", dataType='"+dataType.getName()+'\''+", data='"+data+'\''+", status="+status+", timestamp="+timestamp+'}';
    }
}
