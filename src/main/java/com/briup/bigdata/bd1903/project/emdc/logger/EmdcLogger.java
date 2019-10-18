package com.briup.bigdata.bd1903.project.emdc.logger;


import org.apache.log4j.Logger;

/**
 * @program:emdc
 * @package:com.briup.bigdata.bd1903.project.emdc.logger
 * @filename:${CLASS_NAME}.java
 * @create:2019/9/20 0020 16:39
 * @author:DXQ
 * @description:${description}
 **/
public class EmdcLogger {
    public static void main(String[] args) {
        Logger logger = Logger.getRootLogger();

        System.out.println(logger);
        logger.debug("debug");
        logger.info("info");
        logger.warn("warn");
        logger.error("error");
        logger.fatal("fatal");

    }
}
