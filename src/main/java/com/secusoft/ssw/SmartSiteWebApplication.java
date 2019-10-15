package com.secusoft.ssw;

import com.secusoft.ssw.common.datasource.DynamicDataSourceContextHolder;
import com.secusoft.ssw.dao.MonitorDao;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
import java.util.Properties;

@SpringBootApplication
public class SmartSiteWebApplication {


	public static void main(String[] args) {
		DynamicDataSourceContextHolder.monitors.addAll(new MonitorDao().selectMonitors());
		//读取配置文件
		String separator = System.getProperty("file.separator");
		String dbConfigPath = System.getProperty("user.dir")+separator+"config"+separator+"application.properties";
		Properties properties = new Properties();
		try {
			InputStream in = new BufferedInputStream(new FileInputStream(
					new File(dbConfigPath)));
			properties.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		SpringApplication app = new SpringApplication(SmartSiteWebApplication.class);
		app.setDefaultProperties(properties);
		app.run(args);
	}

}
