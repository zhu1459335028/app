package com.secusoft.ssw.common;


import com.secusoft.ssw.model.global.Monitor;

import java.io.*;
import java.util.Properties;

/**
 * Global数据库参数配置
 * Created by ChenYongHeng on 2017/3/31.
 */
public class Config {

    private Monitor monitor;

    /**
     * 从配置文件加载
     */
    public Config() {
        String separator = System.getProperty("file.separator");
        String dbConfigPath = System.getProperty("user.dir")+separator+"config"+separator+"db.properties";

        Properties properties = new Properties();
        try {
            InputStream in = new BufferedInputStream(new FileInputStream(
                    new File(dbConfigPath)));
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Monitor monitor = new Monitor();
        monitor.setMonitorid(0);
        monitor.setDbip(properties.getProperty("globaldatabase.server_ip"));
        monitor.setDbport(Integer.valueOf(properties.getProperty("globaldatabase.port")));
        monitor.setDbname(properties.getProperty("globaldatabase.dbname"));
        monitor.setDbuser(properties.getProperty("globaldatabase.username"));
        monitor.setDbpswd(properties.getProperty("globaldatabase.password"));
        this.monitor = monitor;
    }

    public Monitor getMonitor() {
        return monitor;
    }

    public void setMonitor(Monitor monitor) {
        this.monitor = monitor;
    }

}
