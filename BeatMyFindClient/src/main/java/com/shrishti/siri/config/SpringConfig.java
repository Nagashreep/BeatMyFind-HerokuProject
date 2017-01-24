package com.shrishti.siri.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.shrishti.siri.dataAccessDelegate.dao.DataAccessDelegate;
import com.shrishti.siri.dataAccessDelegate.dao.impl.DataAccessDelegateImpl;

@Configuration
public class SpringConfig {

	@Bean(name="dataAccessDelegate")
	public DataAccessDelegate getDataAccessDelegate(){
		DataAccessDelegate dataAccess = new DataAccessDelegateImpl();
		return dataAccess;
	}
}
