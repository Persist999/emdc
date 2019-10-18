package com.briup.bigdata.bd1903.project.emdc.TcpClientServer;

import com.briup.bigdata.bd1903.project.emdc.bean.Environment;

import java.util.List;

/**
 * @program:emdc
 * @package:com.briup.bigdata.bd1903.project.emdc.Tcp
 * @create:2019/9/17 0017 14:48
 * @author:DengXiaoQin
 * @description
 **/
public interface Client {
   void send(List<Environment> envs);
   void close();
}
