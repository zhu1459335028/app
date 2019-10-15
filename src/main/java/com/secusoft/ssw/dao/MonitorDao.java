package com.secusoft.ssw.dao;

import com.secusoft.ssw.common.Config;
import com.secusoft.ssw.common.datasource.DynamicDataSourceContextHolder;
import com.secusoft.ssw.model.global.Monitor;

import java.util.List;

public class MonitorDao extends BaseDAO<Monitor>{
	
	/**
	 * 通过jdbc查询主数据库中配置的所有数据源
	 * @return
	 */
	public List<Monitor> selectMonitors(){
		Monitor globalMonitor = new Config().getMonitor();
		DynamicDataSourceContextHolder.monitors.add(globalMonitor);
        String sql = "select * from monitor";
        List<Monitor> monitors = super.executeQuery(globalMonitor,sql,new Object[]{});
        return monitors;
    }

}
