package com.briup.bigdata.bd1903.project.emdc.TcpClientServer;

import com.briup.bigdata.bd1903.project.emdc.backup.Backup;
import com.briup.bigdata.bd1903.project.emdc.backup.BackupImpl;
import com.briup.bigdata.bd1903.project.emdc.bean.Environment;
import com.briup.bigdata.bd1903.project.emdc.config.ConfigFactory;
import com.briup.bigdata.bd1903.project.emdc.gather.Gather;
import com.briup.bigdata.bd1903.project.emdc.gather.GatherImpl;


import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.Properties;

import static com.briup.bigdata.bd1903.project.emdc.pro.Pro.getValue;

/**
 * @program:emdc
 * @package:com.briup.bigdata.bd1903.project.emdc.Tcp
 * @create:2019/9/17 0017 15:03
 * @author:DengXiaoQin
 * @description 客户端程序实现类
 **/
public class CilentImpl implements Client {
    private Socket cs;
    private Backup backup= ConfigFactory.getBackup();
    private static Properties prop=ConfigFactory.getProp();


    public CilentImpl() {}


    public static void main(String[] args) throws IOException {



        Client client=ConfigFactory.getClient();

        Gather gather =ConfigFactory.getGather();



        List<Environment> envs = gather.gather(prop.getProperty("radwtmp"));
        client.send(envs);
        client.close();


    }

    @Override
    public void send(List<Environment> envs) {
        OutputStream os=null;
        ObjectOutputStream oos=null;
        try {
//            Socket cs=new Socket(getValue("localhostIp"),Integer.parseInt(getValue("port")));

            this.cs=new Socket(prop.getProperty("ip"),Integer.parseInt(prop.getProperty("port")));
            //发送之前，需要从备份文件中读取备份的集合对象，
            // 和当前需要发送的集合对象合并到一起发送给服务器

//            Object o = this.backup.load(getValue("client_fileName"));
            System.out.println(this);
            System.out.println(prop.getProperty("clientSendEnvs"));
            Object o = this.backup.load(prop.getProperty("clientSendEnvs"));
            System.out.println(prop.getProperty("clientSendEnvs"));
            if (o!=null){
                if (o instanceof List){
                    List<Environment> backupedEnvs=(List<Environment>) o;
                    envs.addAll(backupedEnvs);
                }
            }

            //创建Socket对象的过程中已经在执行连接服务器的操作
            System.out.println("客户端与服务器连接成功");
                //获取输出流对象，给服务器发送消息,
                // 由于发送给服务器是List集合的对象所以要转换为对象输出流
                os = this.cs.getOutputStream();

               oos = new ObjectOutputStream(os);
                oos.writeObject(envs);

                oos.flush();

        } catch (IOException e) {
            e.printStackTrace();
            //需要备份当前这在发送的List集合
//            this.backup.backup(envs,getValue("client_fileName"));

            this.backup.backup(envs,prop.getProperty("clientSendEnvs"));

        }finally {
            if (os!=null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (oos!=null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void close() {

        try {
                if (cs!=null)
                this.cs.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
    }


}
