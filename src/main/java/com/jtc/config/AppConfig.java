package com.jtc.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "com.jtc")

public class AppConfig {
	
	  @Bean
	    public DataSource dataSource() {
	        BasicDataSource dataSource = new BasicDataSource();
	        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
	        dataSource.setUrl("jdbc:mysql://localhost:3306/project1");
	        dataSource.setUsername("root");
	        dataSource.setPassword("root");
	        return dataSource;
	    }
	  
	  private Properties hibernateProperties() {
	        Properties properties = new Properties();
	        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
	        properties.put("hibernate.show_sql", "true");
	        properties.put("hibernate.hbm2ddl.auto", "update");
	        return properties;
	    }

	    @Bean
	    public LocalSessionFactoryBean sessionFactory() {
	        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
	        sessionFactory.setDataSource(dataSource());
	        sessionFactory.setPackagesToScan("com.jtc.entity"); // Update to your package
	        sessionFactory.setHibernateProperties(hibernateProperties());

	        return sessionFactory;
	    }

	    @Bean
	    public HibernateTemplate hibernateTemplate(SessionFactory sessionFactory ) {
	    	
	    	HibernateTemplate hibernateTemplate=new HibernateTemplate();
	    	hibernateTemplate.setSessionFactory(sessionFactory);
			return hibernateTemplate;
	    	
	    	
	    }
	    
	    @Bean
	    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory ) {
	        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
	       // transactionManager.setSessionFactory(sessionFactory().getObject());
	       transactionManager.setSessionFactory(sessionFactory);
	        return transactionManager;
	    }

}
