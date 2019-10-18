package com.briup.bigdata.bd1903.project.emdc.TcpClientServer;

import com.briup.bigdata.bd1903.project.emdc.bean.Environment;
import com.briup.bigdata.bd1903.project.emdc.config.ConfigFactory;
import com.briup.bigdata.bd1903.project.emdc.pro.Pro;
import com.briup.bigdata.bd1903.project.emdc.store.Store;
import com.briup.bigdata.bd1903.project.emdc.store.StoreImpl;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Properties;

import static com.briup.bigdata.bd1903.project.emdc.pro.Pro.getValue;

/**
 * @program:emdc
 * @package:com.briup.bigdata.bd1903.project.emdc.Tcp
 * @create:2019/9/17 0017 15:13
 * @author:DengXiaoQin
 * @description 服务器程序接口实现类
 **/


public class ServiceImpl implements Server {
    private static Properties prop= ConfigFactory.getProp();


    public static void main(String[] args) throws IOException {
//        Pro p=new Pro();
//        ServerSocket ss=new ServerSocket(Integer.parseInt(getValue("port")));

        ServerSocket ss=new ServerSocket(Integer.parseInt(prop.getProperty("port")));

        Socket cs = ss.accept();
        String ip=cs.getInetAddress().getHostAddress();
        System.out.println(":::"+ip+"连接到了服务器");
        new Thread(()->{
            try {
                InputStream is = cs.getInputStream();
                ObjectInputStream ois = new ObjectInputStream(is);
                List<Environment> envs =ConfigFactory.getServer().reciver(ois);
                Store store =ConfigFactory.getStore();
                store.store(envs);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

    }
    @Override
    public List<Environment> reciver(ObjectInputStream ois) {

        try {
            Object  o = ois.readObject();
            if (o instanceof List){
                List<Environment> envs=(List<Environment>) o;
                return envs;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }





//        ss=new ServerSocket(12345);
//        System.out.println("服务器启动了");
//
//        socket=ss.accept();
//
//        String ip=socket.getInetAddress().getHostAddress();
//        System.out.println(":::"+ip+"连接到了服务器");
//
//        is = socket.getInputStream();
//        bis = new BufferedInputStream(is);
//        ois = new ObjectInputStream(bis);

            return null;
    }

    @Override
    public void close() {

    }
}
