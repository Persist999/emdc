package com.briup.bigdata.bd1903.project.emdc.Test;/**
 * @program:emdc
 * @package:com.briup.bigdata.bd1903.project.emdc.Test
 * @date: 2019/9/17 0017 14:23
 * @author:DengXiaoQin
 * @description
 **/

import com.briup.bigdata.bd1903.project.emdc.bean.Environment;
import com.briup.bigdata.bd1903.project.emdc.gather.Gather;
import com.briup.bigdata.bd1903.project.emdc.gather.GatherImpl;
import com.briup.bigdata.bd1903.project.emdc.store.Store;
import com.briup.bigdata.bd1903.project.emdc.store.StoreImpl;

import java.util.List;

/**
 *@program:emdc
 *@package:com.briup.bigdata.bd1903.project.emdc.Test
 *@create:17 14:23
 *@author:DengXiaoQin
 *@description
 **/
public class Test {



    @org.junit.Test
    public void testGather(){
        Gather gather=new GatherImpl();
        List<Environment> list = gather.gather("E:\\Study\\BD1903\\smart\\smarthome-day1\\radwtmp");
        list.forEach(System.out::println);
    }



    @org.junit.Test
    public void testStore(){
        Gather gather=new GatherImpl();
        List<Environment> envs = gather.gather("E:\\Study\\BD1903\\smart\\smarthome-day1\\radwtmp");
        Store store = new StoreImpl();
        store.store(envs);
    }
}
