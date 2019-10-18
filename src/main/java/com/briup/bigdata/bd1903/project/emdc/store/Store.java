package com.briup.bigdata.bd1903.project.emdc.store;

import com.briup.bigdata.bd1903.project.emdc.bean.Environment;

import java.util.List;

/**
 * ClassName: Store
 * Description
 *
 * @author DXQ
 * @date: 2019/9/17 0017 11:04
 * @since JDK 1.8
 */
public interface Store {
    public void store(List<Environment> envs);
}
