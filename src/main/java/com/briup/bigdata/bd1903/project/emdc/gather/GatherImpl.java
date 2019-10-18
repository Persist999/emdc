package com.briup.bigdata.bd1903.project.emdc.gather;

import com.briup.bigdata.bd1903.project.emdc.backup.Backup;
import com.briup.bigdata.bd1903.project.emdc.backup.BackupImpl;
import com.briup.bigdata.bd1903.project.emdc.bean.DataType;
import com.briup.bigdata.bd1903.project.emdc.bean.Environment;
import com.briup.bigdata.bd1903.project.emdc.config.ConfigFactory;
import com.briup.bigdata.bd1903.project.emdc.pro.Pro;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.CopyOnWriteArrayList;

import static com.briup.bigdata.bd1903.project.emdc.pro.Pro.getValue;

/**
 * @program: emdc
 * @package: com.briup.bigdata.bd1903.project.emdc.gather
 * @filename: GatherImpl.java
 * @create: 2019.09.17 09:30
 * @author: Kevin
 * @description: .采集程序的实现类
 **/
public class GatherImpl implements Gather{
    private Backup backup=ConfigFactory.getBackup();
    private Properties prop=ConfigFactory.getProp();


    public GatherImpl() {

    }

    @Override
    public List<Environment> gather(String filePath){
        try{
            BufferedReader br=new BufferedReader(new FileReader(filePath));
            List<Environment> envs=new ArrayList<>();
            String line;
            //在采集之前读取备份文件
            //转化成行数，然后跳过之后再做采集

//            Object o = this.backup.load(getValue("gathered_fileName"));
            String gatheredLinesfile=prop.getProperty("gatheredLines");

            System.out.println(gatheredLinesfile);

            Object o = this.backup.load(gatheredLinesfile);

            int gatheredLines=0;
            if (o!=null){
                if (o instanceof Integer) gatheredLines=(int)o;
            }
            int count=gatheredLines;
            int currentGatheredLines=0;

            while((line=br.readLine())!=null){
                //跳过已经采集过的数据
                if (count-->0) continue;

                //记录本次采集过的行数
                currentGatheredLines++;

                String[] strs=line.split("[|]");

                if("16".equals(strs[3])){
                    // 温度
                    Environment env=new Environment();
                    this.setData(env,strs);
                    env.setDataType(DataType.TMP);
                    int value=Integer.parseInt(strs[6].substring(0,4),16);
                    float f=(float)(value*0.00268127-46.85);
                    String data=String.format("%.2f",f);
                    env.setData(data);
                    envs.add(env);

                    // 湿度
                    Environment env1=new Environment();
                    this.setData(env1,strs);
                    env1.setDataType(DataType.HUM);
                    value=Integer.parseInt(strs[6].substring(4,8),16);
                    f=(float)(value*0.00190735-6);
                    data=String.format("%.2f",f);
                    env1.setData(data);
                    envs.add(env1);
                }

                if("256".equals(strs[3])){
                    // 光照强度
                    Environment env=new Environment();
                    this.setData(env,strs);
                    env.setDataType(DataType.ILL);
                    env.setData(String.valueOf(Integer.parseInt(strs[6],16)));
                    envs.add(env);
                }

                if("1280".equals(strs[3])){
                    // 二氧化碳浓度
                    Environment env=new Environment();
                    this.setData(env,strs);
                    env.setDataType(DataType.CO2);
                    env.setData(String.valueOf(Integer.parseInt(strs[6],16)));
                    envs.add(env);
                }
            }
            //备份行数：gatheredLines+currentGatheredLines
            int allLines=gatheredLines+currentGatheredLines;
//            this.backup.backup(allLines,getValue("gathered_fileName"));
            this.backup.backup(allLines,gatheredLinesfile);
            return envs;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }


    public void setData(Environment env,String[] strs){
        env.setSrcId(strs[0]);
        env.setDestId(strs[1]);
        env.setDevId(strs[2]);
        env.setSensorId(strs[3]);
        env.setSensorCounter(Integer.parseInt(strs[4]));
        env.setCmdType(strs[5]);
        env.setStatus(Integer.parseInt(strs[7]));
        env.setTimestamp(Long.parseLong(strs[8]));
    }
}
