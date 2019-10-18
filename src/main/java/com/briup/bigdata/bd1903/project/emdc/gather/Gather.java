package com.briup.bigdata.bd1903.project.emdc.gather;



import com.briup.bigdata.bd1903.project.emdc.bean.Environment;

import java.util.List;

/**
 * ClassName: Gather
 * Description
 *
 * @author DXQ
 * @date: 2019/9/17 0017 9:34
 * @since JDK 1.8
 */
public interface Gather {
    public List<Environment> gather(String path);
}
