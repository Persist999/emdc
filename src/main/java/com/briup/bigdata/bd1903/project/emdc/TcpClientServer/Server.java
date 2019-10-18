package com.briup.bigdata.bd1903.project.emdc.TcpClientServer;

import com.briup.bigdata.bd1903.project.emdc.bean.Environment;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

public interface Server{
    List<Environment>  reciver(ObjectInputStream ois) throws IOException, ClassNotFoundException;
    void close();
}
