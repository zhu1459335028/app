package com.secusoft.ssw.common.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.secusoft.ssw.model.global.Monitor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement(order = 2)
public class DataSourceConfigurer {
	
	private DataSource initDataSource(Monitor monitor){
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://"+ monitor.getDbip()+":"+ monitor.getDbport()+"/"+ monitor.getDbname()+"?characterEncoding=utf-8");
		dataSource.setUsername(monitor.getDbuser());
		dataSource.setPassword(monitor.getDbpswd());
		return dataSource;
	}

    /**
     * Dynamic data source.
     *
     * @return the data source
     */
    @Bean("dynamicDataSource")
    public DataSource dynamicDataSource() {
    	DynamicRoutingDataSource dynamicRoutingDataSource = new DynamicRoutingDataSource();
    	Map<Object, Object> dataSourceMap = new HashMap<Object, Object>();
		//已通过主数据库查询到其他数据源的配置
		for(Monitor monitor : DynamicDataSourceContextHolder.monitors){
			dataSourceMap.put(String.valueOf(monitor.getMonitorid()), initDataSource(monitor));
		}
        dynamicRoutingDataSource.setDefaultTargetDataSource(dataSourceMap.get("0"));
        dynamicRoutingDataSource.setTargetDataSources(dataSourceMap);
        return dynamicRoutingDataSource;
    }

    /**
     * Sql session factory bean.
     * Here to config datasource for SqlSessionFactory
     * <p>
     * You need to add @{@code @ConfigurationProperties(prefix = "mybatis")}, if you are using *.xml file,
     * the {@code 'mybatis.type-aliases-package'} and {@code 'mybatis.mapper-locations'} should be set in
     * {@code 'application.properties'} file, or there will appear invalid bond statement exception
     *
     * @return the sql session factory bean
     */
    @Bean
    @ConfigurationProperties(prefix = "mybatis")
    public SqlSessionFactoryBean sqlSessionFactoryBean() {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        // Here is very important, if don't config this, will can't switch datasource
        // put all datasource into SqlSessionFactoryBean, then will autoconfig SqlSessionFactory
        sqlSessionFactoryBean.setDataSource(dynamicDataSource());
        return sqlSessionFactoryBean;
    }

    /**
     * Transaction manager platform transaction manager.
     *
     * @return the platform transaction manager
     */
    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dynamicDataSource());
    }
}

