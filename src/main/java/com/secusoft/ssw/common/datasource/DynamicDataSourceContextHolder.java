package com.secusoft.ssw.common.datasource;


import com.secusoft.ssw.model.global.Monitor;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据源上下文
 * @author yaojiacheng
 * 2018年4月12日
 */
public class DynamicDataSourceContextHolder {
	
	/**
	 * 数据源
	 */
	public static List<Monitor> monitors = new ArrayList<Monitor>();

    /**
     * 存储每个线程所使用的数据源
     */
    private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<String>();


    /**
     * 设置当前线程要使用的数据源
     * @param key
     */
    public static void setDataSourceKey(String key) {
        CONTEXT_HOLDER.set(key);
    }

    /**
     * 获取当前线程要使用的数据源，当获取不到时dynamicDataSource会使用配置的默认数据源
     * @return
     */
    public static String getDataSourceKey() {
        return CONTEXT_HOLDER.get();
    }

    /**
     * 清空当前线程的数据源
     */
    public static void clearDataSourceKey() {
        CONTEXT_HOLDER.remove();
    }

}
