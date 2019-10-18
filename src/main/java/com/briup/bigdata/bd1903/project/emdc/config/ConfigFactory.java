package com.briup.bigdata.bd1903.project.emdc.config;

import com.briup.bigdata.bd1903.project.emdc.TcpClientServer.Client;
import com.briup.bigdata.bd1903.project.emdc.TcpClientServer.Server;
import com.briup.bigdata.bd1903.project.emdc.backup.Backup;
import com.briup.bigdata.bd1903.project.emdc.gather.Gather;
import com.briup.bigdata.bd1903.project.emdc.store.Store;

import org.dom4j.Attribute;
import org.dom4j.Document;

import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @program:emdc
 * @package:com.briup.bigdata.bd1903.project.emdc.config
 * @filename:${CLASS_NAME}.java
 * @create:2019/9/20 0020 9:15
 * @author:DXQ
 * @description:使用工厂模式创建各个类的对象
 **/
public class ConfigFactory {
    private static Properties prop=new Properties();
    private static Map<String,Object> map=new HashMap<>();

    public static void main(String[] args) {

    }

    static {

        try {
            SAXReader reader=new SAXReader();
            Document doc = reader.read(ConfigFactory.class.getResourceAsStream("/emdc.xml"));
            List<Element> es = doc.getRootElement().elements();
           for(Element e:es){
               List<Element> els = e.elements();
               for (Element e1:els){
                   String propName=e1.getName();
                   String propValue=e1.getStringValue();
                   prop.setProperty(propName,propValue);
               }

               Attribute attr = e.attribute("class");
               String value=attr.getStringValue();
               Object o = Class.forName(value).newInstance();
               map.put(e.getName(),o);
           }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static Properties getProp(){
        return prop;
    }
    public static Client getClient(){
        return (Client) map.get("client");
    }
    public static Server getServer(){
        return (Server) map.get("server");
    }
    public static Backup getBackup(){
        return (Backup) map.get("backup");
    }
    public static Gather getGather(){
        return (Gather) map.get("gather");
    }
    public static Store getStore(){
        return (Store) map.get("store");
    }

}
