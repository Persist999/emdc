package com.briup.bigdata.bd1903.project.emdc.store;/**
 * @program:emdc
 * @package:com.briup.bigdata.bd1903.project.emdc.store
 * @date: 2019/9/17 0017 11:06
 * @author:DengXiaoQin
 * @description
 **/

import com.briup.bigdata.bd1903.project.emdc.backup.Backup;
import com.briup.bigdata.bd1903.project.emdc.backup.BackupImpl;
import com.briup.bigdata.bd1903.project.emdc.bean.Environment;
import com.briup.bigdata.bd1903.project.emdc.config.ConfigFactory;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static com.briup.bigdata.bd1903.project.emdc.pro.Pro.getValue;

/**
 *@program:emdc
 *@package:com.briup.bigdata.bd1903.project.emdc.store
 *@create:17 11:06
 *@author:DengXiaoQin
 *@description
 **/
public class StoreImpl implements Store {
    private Backup backup= ConfigFactory.getBackup();
    private Properties prop=ConfigFactory.getProp();


    public StoreImpl() {

    }

    @Override
    public void store(List<Environment> envs) {
        Connection conn=null;
        Object o=null;
        int count=1;
        List<Environment> list1=new ArrayList<>();
        int listcount=0;

        String store_fileName=prop.getProperty("store_envs.bak");
        int batchSize=Integer.parseInt(prop.getProperty("batchSize"));

        try {
//            Class.forName(getValue("Driver"));
//            String url=getValue("url");
//            String user=getValue("user");
//            String password=getValue("password");
            Class.forName(prop.getProperty("driver"));
            String url=prop.getProperty("url");
            String user=prop.getProperty("user");
            String password=prop.getProperty("password");
            conn = DriverManager.getConnection(url, user, password);

            String sql="insert into emdc.envs value(?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pstat = conn.prepareStatement(sql);


            //粗腰在JDBC中设置手动提交事务
            conn.setAutoCommit(false);

            //保存至数据库之前，需要从备份文件中加载上次未保存成功的集合对象
            //然后将该集合对象和本次需要保存的集合对象合并到一起，然后保存至数据库

//            o = this.backup.load(getValue("store_fileName"));
            System.out.println(store_fileName);
            o = this.backup.load(store_fileName);
            if (o!=null){
                if (o instanceof List) {
                    List<Environment> backupedEnvs = (List<Environment>) o;
                    envs.addAll(backupedEnvs);
                }
            }
            for (Environment env:envs){
                listcount++;
                pstat.setString(1,env.getSrcId());
                pstat.setString(2,env.getDestId());
                pstat.setString(3,env.getDevId());
                pstat.setString(4,env.getSensorId());
                pstat.setInt(5,env.getSensorCounter());
                pstat.setString(6,env.getCmdType());
                pstat.setString(7,env.getDataType().toString());
                pstat.setString(8,env.getData());
                pstat.setInt(9,env.getStatus());
                pstat.setLong(10,env.getTimestamp());

                pstat.addBatch();

//                if (count++%(Integer.parseInt(getValue("batchSize")))==0) {
//                    pstat.executeBatch();
//                    conn.commit();
//                }

                if (count++%batchSize==0) {
                    pstat.executeBatch();
                    conn.commit();
                }

            }
            pstat.executeBatch();
            pstat.close();
            conn.commit();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
            //备份
//            for (int i=(listcount/(Integer.parseInt(getValue("batchSize"))))
//                    *(Integer.parseInt(getValue("batchSize")));i<envs.size();i++){
//                list1.add(envs.get(i));
//            }

            for (int i=(listcount/batchSize) *batchSize;i<envs.size();i++){
                list1.add(envs.get(i));
            }

//            this.backup.backup(list1,getValue("store_fileName"));

            this.backup.backup(list1,store_fileName);


            //事务回滚,已经提交的事务不能进行回滚
            if (conn!=null){
                try {
                    conn.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}
