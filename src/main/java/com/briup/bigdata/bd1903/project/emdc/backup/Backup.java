package com.briup.bigdata.bd1903.project.emdc.backup;

/**
 * @program:emdc
 * @package:com.briup.bigdata.bd1903.project.emdc.backup
 * @filename:${CLASS_NAME}.java
 * @create:2019/9/19 0019 9:09
 * @author:DXQ
 * @description:.备份程序接口
 **/
public interface Backup {
    //将对象o备份至文件fileName中
    void backup(Object o,String fileName);

    //从fileName中读取备份的数据o
    Object load(String fileName);
}
