package com.secusoft.ssw.common.datasource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DynamicDataSourceAspect implements Ordered {
	
    /**
     * Dao aspect.
     */
    @Pointcut("execution( * com.secusoft.ssw.service.*.*(..))")
    public void daoAspect() {
    }

    /**
     * Switch DataSource
     *
     * @param point the point
     */
	@Before("daoAspect()")
    public void switchDataSource(JoinPoint point) {
    	if(point.getArgs().length>0){
			DynamicDataSourceContextHolder.setDataSourceKey(point.getArgs()[0].toString());
		}

    }

    /**
     * Restore DataSource
     *
     * @param point the point
     */
    @After("daoAspect())")
    public void restoreDataSource(JoinPoint point) {
        DynamicDataSourceContextHolder.clearDataSourceKey();
    }

	@Override
	public int getOrder() {
		return 1;
	}

}
